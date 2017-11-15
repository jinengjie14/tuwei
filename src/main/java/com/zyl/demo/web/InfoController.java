package com.zyl.demo.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyl.demo.model.Info;
import com.zyl.demo.service.InfoService;
import com.zyl.demo.utils.PageUtil;
import com.zyl.demo.utils.Result;
/**
 * info控制器
 * @author Zyl
 *
 */
@Controller
public class InfoController {
	@Autowired
	private InfoService infoService;
	/**
	 * 翻页显示
	 * @param model
	 * @param keyword
	 * @param pagesize
	 * @param page
	 * @return
	 */
	@GetMapping("/info")
	public String index(Model model, @RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "pagesize", defaultValue = "3", required = false) Integer pagesize,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
		PageUtil<Info> pages = infoService.findByAll(keyword, page, pagesize);
		model.addAttribute("pages", pages);
		return "index";
	}
	/**
	 * 新增和异常处理
	 * @param info
	 * @param bindingResult
	 * @return
	 */
	@ResponseBody
	@PostMapping("/info")
	public Result create(@Valid InfoForm infoForm,BindingResult bindingResult,HttpSession session){
		Result result = new Result();
		if (bindingResult.hasErrors()) {
			result.setMsg("字符数不得少于3位");
			return result;
        }
		infoService.create(infoForm);
		return new Result("/info");
	}
	
	/**
	 * 新建默认文档
	 * @return
	 */
	@GetMapping("/add")
	public String add(){
		infoService.add();
		return "redirect:/info";
	}
	
	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	@ResponseBody
	@GetMapping("/info/{id}/delete")
	public Result delete(@PathVariable String id){
		Info info = infoService.findById(id);
		infoService.delete(info);
		return new Result("/info");
	}
	
	/**
	 * 查找单条消息
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/info/{id}/edit")
	public String edit (@PathVariable String id ,Model model){
		Info info = infoService.findById(id);
		model.addAttribute("info", info);
		return "markdown";
	}
	
	/**
	 * 修改笔记
	 * @param infoForm
	 * @param bindingResult
	 * @param id
	 * @return
	 */
	@ResponseBody
	@PostMapping("/info/{id}/update")
	public Result update(@Valid InfoForm infoForm,BindingResult bindingResult,@PathVariable String id){
		Result result = new Result();
		if (bindingResult.hasErrors()) {
			result.setMsg(bindingResult.getFieldError().getDefaultMessage());
			return result;
        }
		infoService.update(infoForm,id);
		return new Result("/info");
	}
	
}
