package cn.itcast.shop.adminuser.action;

import java.util.List;

import javax.sql.rowset.serial.SerialArray;

import org.apache.struts2.ServletActionContext;
import org.omg.PortableServer.Servant;

import cn.itcast.shop.adminuser.service.AdminUserService;
import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台用户action
 * @author Hasee
 *
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {
	private AdminUser adminUser = new AdminUser();
	public AdminUser getModel() {
		return adminUser;
	}
	//注入server
	private AdminUserService adminUserService ;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	public String login(){
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if(existAdminUser == null){
			this.addActionError("用户名或者密码错误！");
			return "loginFail";
		}else{
			ServletActionContext.getRequest().getSession().
			setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess" ;
			
		}
	}
	
	public String findAll(){
		List<AdminUser> cList = adminUserService.findAll();
		ActionContext.getContext().getValueStack().set("aList", cList);
		return "findAll" ;
	}
	
	public String save(){
		adminUserService.save(adminUser);
		return "saveSuccess" ;
		
	}
	
	public String delete(){
		adminUser = adminUserService.findByUid(adminUser.getUid());
		adminUserService.delete(adminUser);
		return "deleteSuccess" ;
		
	}
	
	public String edit(){
		adminUser = adminUserService.findByUid(adminUser.getUid());
		return "editSuccess" ;
		
	}
	
	public String update(){
		adminUserService.update(adminUser);
		return "updateSuccess" ;
		
	}
}
