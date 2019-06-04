package cn.itcast.shop.order.adminaction;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.vo.AdminUser;
import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 管理员后台订单查询
 * 
 * @author caopan
 *
 */
public class AdminOrderAction extends ActionSupport implements
		ModelDriven<Order> {

	// 模型驱动使用的对象
	private Order order = new Order();
	// 接受page参数
	private Integer page;
	// 注入Service
	private OrderService orderService;
	// 订单状态
	private Integer status;
	//订单的id
	private Integer orderId;

	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	/**
	 * 根据状态查询所有订单
	 * 
	 * @return
	 */
	public String findAllByState() {
		AdminUser admin = (AdminUser) ServletActionContext.getRequest()
				.getSession().getAttribute("existAdminUser");
		if (admin == null) {
			// this.addActionError("亲！你还没有登录，请先去登录！");
			return "loginFail"; // 没有登录，跳转到登录界面
		}
		// 根据状态查询所有订单
		PageBean<Order> pageBean = orderService.findAllByState(status, page);
		// 将分页的数据显示到页面(值栈)
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		if (status != null) {
			ActionContext.getContext().getValueStack()
					.set("status", "&status=" + status);
			ActionContext.getContext().getValueStack().set("statusNum", status);
		}
		return "findAll";
	}

	/**
	 * 点击发货按钮
	 */
	public String deliveryOrder() {
		AdminUser admin = (AdminUser) ServletActionContext.getRequest()
				.getSession().getAttribute("existAdminUser");
		if (admin == null) {
			// this.addActionError("亲！你还没有登录，请先去登录！");
			return "loginFail"; // 没有登录，跳转到登录界面
		}
	
		//修改订单的状态
		orderService.deliveryOrder(orderId);
		// 根据状态查询所有订单
		PageBean<Order> pageBean = orderService.findAllByState(status, page);
		// 将分页的数据显示到页面(值栈)
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		if (status != null) {
			ActionContext.getContext().getValueStack()
					.set("status", "&status=" + status);
			ActionContext.getContext().getValueStack().set("statusNum", status);
		}
		return "deliverySuccess";
	}

}
