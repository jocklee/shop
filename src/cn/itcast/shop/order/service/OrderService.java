package cn.itcast.shop.order.service;

import java.util.List;
import java.util.Set;

import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.product.dao.ProductDao;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

/**
 * 订单业务层
 * 
 * @author caopan
 *
 */

@Transactional
public class OrderService {

	private OrderDao orderDao;

	private ProductDao productDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}


	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// 保存订单业务层
	public void save(Order order) {
		Set<OrderItem> orderItemSet= order.getOrderItems();
		for(OrderItem item : orderItemSet){
			Product product = item.getProduct();
			Product productResult = productDao.findByPid(product.getPid());
			Integer inventory = productResult.getInventory()-item.getCount();
			productResult.setInventory(inventory);
			productDao.save(productResult);
		}

		orderDao.save(order);
	}

	// 我的订单业务层代码
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页数
		pageBean.setPage(page);
		// 每页显示的记录数
		Integer limit = 5;
		pageBean.setLimit(limit);
		// 设置总的记录数
		Integer totalCount = null;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总的页数
		Integer totalPage = null;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合
		Integer begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPageUid(uid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Order> findAllByState(Integer status, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);
		int limit = 10;
		pageBean.setLimit(limit);
		// 如果 state 为null，则查询所有订单
		int totalCount = orderDao.findCountByState(status);
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findAllByState(status, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 发货状态
	 * @param order
	 */
	public void deliveryOrder(Integer orderId) {
		// TODO Auto-generated method stub
		Order o = orderDao.findOrderByOid(orderId);
		o.setState(3);
		orderDao.update(o);
	}

	//根据订单id查询订单
	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findByOid(oid);
	}

	public void update(Order currOrder) {
		// TODO Auto-generated method stub
		orderDao.update(currOrder);
	}

}
