package cn.itcast.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	Product product = new Product();
	public Product getModel() {
		return product;
	}
	ProductService productService ;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	private Integer page ;
	
	public void setPage(Integer page) {
		this.page = page;
	}
	private CategorySecondService categorySecondService;
	
	private File upload;
	private String uploadFileName;
	private String uploadFileType;
	
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadFileType(String uploadFileType) {
		this.uploadFileType = uploadFileType;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public String findAll(){
		PageBean<Product> pageBean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "findAll" ;
	}
	
	public String addPage(){
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList",csList);
		return "addPage" ;
	}
	
	public String save() throws IOException{
		//product.setPdate(new Date());
		if(upload != null){
			//获取文件上传的路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//创建文件
			File diskFile = new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		productService.save(product);
		return "saveSuccess" ;
	}
	
	public String delete(){
		product = productService.findByPid(product.getPid());
		String imagePath = product.getImage();
		if(imagePath!=null){
			String realPath = ServletActionContext.getServletContext().getRealPath("/"+imagePath);
			java.io.File file = new java.io.File(realPath);
			file.delete();
		}
		productService.delete(product);
		return "deleteSuccess" ;
	}
	public String edit(){
		product = productService.findByPid(product.getPid());
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList",csList);
		ActionContext.getContext().getValueStack().set("p",product);
		return "edit" ;
	}
	public String update(){
		Product productTemp = productService.findByPid(product.getPid());
		String imagePath =  productTemp.getImage();
		if(imagePath!=null){
			String realPath = ServletActionContext.getServletContext().getRealPath("/"+imagePath);
			java.io.File file = new java.io.File(realPath);
			file.delete();
		}
		
		if(upload != null){
			//获取文件上传的路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//创建文件
			File diskFile = new File(realPath+"//"+uploadFileName);
			try {
				FileUtils.copyFile(upload, diskFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			product.setImage("products/"+uploadFileName);
		}

		productService.update(product);
		return "updateSuccess" ;
	}
}
