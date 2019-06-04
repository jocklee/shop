package cn.itcast.shop.category.adminaction;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	private Category category = new Category();
	public Category getModel() {
		return category;
	}
	private CategoryService CategoryService;
	
	public void setCategory(Category category) {
		this.category = category;
	}

	public void setCategoryService(CategoryService categoryService) {
		CategoryService = categoryService;
	}


	public String findAll(){
		List<Category> cList = CategoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll" ;
	}
	
	public String save(){
		CategoryService.save(category);
		return "saveSuccess" ;
		
	}
	
	public String delete(){
		category = CategoryService.findByCid(category.getCid());
		CategoryService.delete(category);
		return "deleteSuccess" ;
		
	}
	
	public String edit(){
		category = CategoryService.findByCid(category.getCid());
		return "editSuccess" ;
	}
	
	public String update(){
		CategoryService.update(category);
		return "updateSuccess" ;
		
	}
}
