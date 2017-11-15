package com.zyl.demo.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * 表单验证类
 * @author Zyl
 *
 */
public class InfoForm {
	@NotNull(message = "info.title.notnull")
	@Size(min = 3, message = "info.name.too_little")
	private String title;
	@NotNull(message = "info.markdown.notnull")
	private String markdown;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMarkdown() {
		return markdown;
	}
	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}
	
}
