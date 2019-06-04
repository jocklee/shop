package cn.itcast.shop.adminuser.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.adminuser.dao.AdminUserDao;
import cn.itcast.shop.adminuser.vo.AdminUser;
import cn.itcast.shop.category.vo.Category;

@Transactional
public class AdminUserService {
	private AdminUserDao adminUserDao ;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	public AdminUser login(AdminUser adminUser) {
		
		return adminUserDao.login(adminUser);
	}
	public List<AdminUser> findAll() {
		return adminUserDao.findAll();
	}

	public void save(AdminUser adminUser) {
		adminUserDao.save(adminUser);
	}

	public AdminUser findByUid(Integer uid) {
		
		return adminUserDao.findByUid(uid);
	}

	public void delete(AdminUser adminUser) {
		adminUserDao.delete(adminUser);
	}

	public void update(AdminUser adminUser) {
		adminUserDao.update(adminUser);
	}
	
}
