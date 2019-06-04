package cn.itcast.shop.categorysecond.adminaction;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	CategorySecond categorySecond = new CategorySecond();
	public CategorySecond getModel() {
		return categorySecond;
	}
	CategorySecondService categorySecondService ;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	private Integer page ;
	
	public void setPage(Integer page) {
		this.page = page;
	}
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String findAll(){
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "findAll" ;
	}
	
	public String addPage(){
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList",cList);
		return "addPage" ;
	}
	
	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess" ;
	}
	public String delete(){
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess" ;
	}
	public String edit(){
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList",cList);
		return "edit" ;
	}
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess" ;
	}
}
