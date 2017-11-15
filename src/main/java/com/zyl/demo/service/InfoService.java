package com.zyl.demo.service;

import java.sql.Timestamp;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zyl.demo.dao.InfoDao;
import com.zyl.demo.model.Info;
import com.zyl.demo.utils.PageUtil;
import com.zyl.demo.web.InfoForm;
/**
 * info服务层
 * @author Zyl
 *
 */
@Transactional
@Service
public class InfoService {
	@Autowired
	private InfoDao infoDao;
	
	/**
	 * 翻页和关键字查询
	 * @param keyword
	 * @param page
	 * @param pagesize
	 * @return
	 */
	public PageUtil<Info> findByAll(String keyword, int page, int pagesize) {
		try {
			PageUtil<Info> pages = infoDao.findByAll( keyword, pagesize, page);
			if(pages.getItems().size() == 0){
				Info info = new Info();
				info.init();
				create(info);
				pages.getItems().add(info);
			}
			return pages;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 创建笔记
	 * @param info
	 */
	public void create(Info info){
		try {
			info.save();
			infoDao.create(info);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 笔记创建
	 * @param infoForm
	 */
	public void create(InfoForm infoForm){
		try {
			Info info = new Info();
			BeanUtils.copyProperties(infoForm, info, Info.class);
			info.save();
			infoDao.create(info);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 创建默认笔记
	 */
	public void add(){
		try {
			Info info = new Info();
			info.init();
			infoDao.create(info);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 删除笔记
	 * @param info
	 */
	public void delete(Info info){
		try {
			infoDao.delete(info);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 通过ID查找对应数据
	 * @param id
	 * @return
	 */
	public Info findById(String id){
		try {
			 Info info = infoDao.findById(id);
			 return info;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 查找数据修改
	 * @param infoForm
	 * @param id
	 */
	public void update(InfoForm infoForm,String id){
		try {
			Info info = findById(id);
			BeanUtils.copyProperties(infoForm, info, Info.class);
			info.setCtime(new Timestamp(System.currentTimeMillis()));
			infoDao.meger(info);
		} catch (Exception e) {
			throw e;
		}
	}
}
