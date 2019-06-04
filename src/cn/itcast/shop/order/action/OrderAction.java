package cn.itcast.shop.order.action;

import java.util.Date;

import cn.itcast.shop.product.service.ProductService;
import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单管理 Acion
 * 
 * @author caopan
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	// 模型驱动使用的对象
	private Order order = new Order();
	// 注入Service
	private OrderService orderService;

	private ProductService productService;
	// 接受page参数
	private Integer page;
	// 删除商品 pid
	private Integer pid;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}

	/**
	 * 保存订单
	 * 
	 * @return
	 */
	public String save() {
		// 1. 保存数据 2. 显示订单对象
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (user == null) {
			this.addActionError("亲！你还没有登录，请先去登录！");
			return "login"; // 没有登录，跳转到登录界面
		}
		//
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			this.addActionError("亲!您还没有购物,请先去购物!");
			return "msg";
		}

		order.setState(1); // 1 未付款 2 已付款但是没有发货 3 已发货没有确认收货 4 交易完成
		order.setOrdertime(new Date());
		// 从购物车获得总价
		order.setTotal(cart.getTotal());
		// 订单项信息
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		// 订单所属用户
		order.setUser(user);

		orderService.save(order);
		// 通过值栈的方式显示订单信息

		// 清空购物车
		cart.clearCart();
		return "saveSuccess";
	}

	/**
	 * 我的订单查询
	 * 
	 * @return
	 */
	public String findByUid() {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (user == null) {
			this.addActionError("亲！你还没有登录，请先去登录！");
			return "login"; // 没有登录，跳转到登录界面
		}
		// 根据用户ID查询
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),
				page);
		// 将分页的数据显示到页面(值栈)
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}

	/**
	 * 根据订单ID查询订单
	 * 
	 * @return
	 */
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}

	/**
	 * 支付界面
	 * 
	 * @return
	 */
	public String payOrder() {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (user == null) {
			this.addActionError("亲！你还没有登录，请先去登录！");
			return "login";
		}
		// 修改订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		// 默认付款成功
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionError("提交成功!");
		return "msg";
	}

	/**
	 * 确认收货
	 * 
	 * @return
	 */
	public String confirmGoods() {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (user == null) {
			this.addActionError("亲！你还没有登录，请先去登录！");
			return "msg";
		}
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		// 根据用户ID查询
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),
				page);
		// 将分页的数据显示到页面(值栈)
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "confirmSuccess";
	}

	// 从购物车中移除购物项的方法:
	public String removeCart() {
		// 获得购物车对象
		Cart cart = getCart();
		// 调用购物车中移除的方法:
		cart.removeCart(pid);
		// 返回页面:
		return "removeOrder";
	}

	// 我的购物车:执行的方法
	public String myCart() {
		return "myCart";
	}

	/**
	 * 获得购物车的方法:从session中获得购物车.
	 * 
	 * @return
	 */
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession()
					.setAttribute("cart", cart);
		}
		return cart;
	}

}
