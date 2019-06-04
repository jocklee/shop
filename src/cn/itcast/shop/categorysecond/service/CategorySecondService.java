package cn.itcast.shop.categorysecond.service;

import java.util.List;

import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.categorysecond.dao.CategorySecondDao;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageBean;

public class CategorySecondService {
	private CategorySecondDao categorySecondDao ;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		pageBean.setPage(page);
		int	limit = 10 ;
		pageBean.setLimit(limit);
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		int totalPage = 0 ;
		if(totalCount%10==0){
			totalPage = totalCount/10 ;
		}else{
			totalPage = totalCount/10 + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page-1)*limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	public CategorySecond findByCsid(Integer csid) {
		return	categorySecondDao.findByCsid(csid);
	}

	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}
	
}
