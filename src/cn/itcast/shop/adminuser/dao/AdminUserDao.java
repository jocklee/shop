package cn.itcast.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.adminuser.vo.AdminUser;
import cn.itcast.shop.category.vo.Category;

public class AdminUserDao extends HibernateDaoSupport{

	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username =? and password = ?";
		List<AdminUser> adminUsers =  this.getHibernateTemplate().find(hql,adminUser.getUsername(),adminUser.getPassword());
		if(adminUsers!=null&&adminUsers.size()>0){
			return adminUsers.get(0);
		}
		return null;
		
	}
	public List<AdminUser> findAll() {
		String hql = "from AdminUser";
		List<AdminUser> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public void save(AdminUser adminUser) {
		this.getHibernateTemplate().save(adminUser);
	}

	public AdminUser findByUid(Integer uid) {
		return this.getHibernateTemplate().get(AdminUser.class, uid);
	}

	public void delete(AdminUser adminUser) {
		this.getHibernateTemplate().delete(adminUser);
	}

	public void update(AdminUser adminUser) {
		this.getHibernateTemplate().update(adminUser);
	}

}
