package cn.itcast.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * 订单Dao层
 * 
 * @author caopan
 *
 */
public class OrderDao extends HibernateDaoSupport {

	// 保存订单
	public void save(Order order) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(order);
	}

	// 我的订单总数
	public Integer findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		} else {
			return null;
		}
	}

	// 我的订单查询
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		String hql = "from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[] { uid },
						begin, limit));
		return list;
	}

	// 查询状态订单总数
	public Integer findCountByState(Integer status) {
		String hql = "";
		List<Long> list;
		if (status == null) {
			hql = "select count(*) from Order";
			list = this.getHibernateTemplate().find(hql);
		} else {
			hql = "select count(*) from Order o where o.state = ?";
			list = this.getHibernateTemplate().find(hql, status);
		}
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		} else {
			return null;
		}
	}

	// 根据状态查询订单
	public List<Order> findAllByState(Integer status, Integer begin,
			Integer limit) {
		String hql = "";
		List<Order> list;
		if (status == null) {
			hql = "from Order o";
			list = this.getHibernateTemplate().execute(
					new PageHibernateCallback<Order>(hql, new Object[] {},
							begin, limit));
		} else {
			hql = "from Order o where o.state = ?";
			list = this.getHibernateTemplate().execute(
					new PageHibernateCallback<Order>(hql,
							new Object[] { status }, begin, limit));
		}
		return list;
	}

	public Order findOrderByOid(Integer orderId) {
		// TODO Auto-generated method stub
		String hql = "from Order o where o.oid = ?";
		List<Order> list = this.getHibernateTemplate().find(hql, orderId);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	//更新订单
	public void update(Order currOrder) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(currOrder);
	}

}
