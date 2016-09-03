package com.xyang.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyang.object.Admin;
import com.xyang.object.User;
import com.xyang.object.UserListForm;
import com.xyang.object.UserMapForm;
import com.xyang.object.UserSetForm;

/**
 * Created by geely on 2015/11/22.
 */
/**
 * 数据绑定测试
 */
@Controller
public class TestController {
	// 1.基本类型、包装类型
	// http://localhost:8080/baseType.do?age=10
	// 500 http://localhost:8080/baseType.do
	// 400 http://localhost:8080/baseType.do?age=abc
	@RequestMapping(value = "baseType.do")
	@ResponseBody
	public String baseType(int age) {
		return "age:" + age;
	}

	// http://localhost:8080/baseType2.do?age=10
	// http://localhost:8080/baseType2.do
	// 400 http://localhost:8080/baseType2.do?age=abc
	@RequestMapping(value = "baseType2.do")
	@ResponseBody
	public String baseType2(Integer age) {
		return "age:" + age;
	}

	// 2.数组，简单对象、复杂对象的绑定
	// todo http://localhost:8080/array.do?name=Tom&name=Lucy&name=Jim
	@RequestMapping(value = "array.do")
	@ResponseBody
	public String array(String[] name) {
		StringBuilder sbf = new StringBuilder();
		for (String item : name) {
			sbf.append(item).append(" ");
		}
		return sbf.toString();
	}

	// http://localhost:8080/object.do?name=Tom&age=10
	// http://localhost:8080/object.do?name=Tom&age=10&contactInfo.phone=10086
	// http://localhost:8080/object.do?user.name=Tom&admin.name=Lucy&age=10
	@RequestMapping(value = "object.do")
	@ResponseBody
	public String object(User user, Admin admin) {
		return user.toString() + " " + admin.toString();
	}
	/*
	 * @InitBinder 用于实现WebDataBinder的初始化操作。
	 * 
	 */
	@InitBinder("user")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("user.");
	}

	@InitBinder("admin")
	public void initAdmin(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("admin.");
	}

	// 3. List、Set、Map类型的绑定
	// http://localhost:8080/list.do?users[0].name=Tom&users[1].name=Lucy
	// http://localhost:8080/list.do?users[0].name=Tom&users[1].name=Lucy&users[20].name=Jim
	@RequestMapping(value = "list.do")
	@ResponseBody
	public String list(UserListForm userListForm) {
		return "listSize:" + userListForm.getUsers().size() + "  "
				+ userListForm.toString();
	}

	// http://localhost:8080/set.do?users[0].name=Tom&users[20].name=Lucy 本身set的size须初始化
	@RequestMapping(value = "set.do")
	@ResponseBody
	public String set(UserSetForm userSetForm) {
		return userSetForm.toString();
	}

	// http://localhost:8080/map.do?users['X'].name=Tom&users['X'].age=10&users['Y'].name=Lucy
	@RequestMapping(value = "map.do")
	@ResponseBody
	public String map(UserMapForm userMapForm) {
		return userMapForm.toString();
	}

	// {
	// "name": "Jim",
	// "age": 16,
	// "contactInfo": {
	// "address": "beijing",
	// "phone": "10010"
	// }
	// }
	// application/json
	@RequestMapping(value = "json.do")
	@ResponseBody
	public String json(@RequestBody User user) {
		return user.toString();
	}

	// <?xml version="1.0" encoding="UTF-8" ?>
	// <admin>
	// <name>Jim</name>
	// <age>16</age>
	// </admin>
	// application/xml
	@RequestMapping(value = "xml.do")
	@ResponseBody
	public String xml(@RequestBody Admin admin) {
		return admin.toString();
	}

	@RequestMapping(value = "date1.do")
	@ResponseBody
	public String date1(Date date1) {
		return date1.toString();
	}

	@InitBinder("date1")
	public void initDate1(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	@RequestMapping(value = "date2.do")
	@ResponseBody
	public String date2(Date date2) {
		return date2.toString();
	}

	@RequestMapping(value = "/book", method = RequestMethod.GET)
	@ResponseBody
	public String book(HttpServletRequest request) {
		String contentType = request.getContentType();
		if (contentType == null) {
			return "book.default";
		} else if (contentType.equals("txt")) {
			return "book.txt";
		} else if (contentType.equals("html")) {
			return "book.html";
		}
		return "book.default";
	}

	@RequestMapping(value = "/subject/{subjectId}", method = RequestMethod.GET)
	@ResponseBody
	public String subjectGet(@PathVariable("subjectId") String subjectId) {
		return "this is a get method,subjectId:" + subjectId;
	}

	@RequestMapping(value = "/subject/{subjectId}", method = RequestMethod.POST)
	@ResponseBody
	public String subjectPost(@PathVariable("subjectId") String subjectId) {
		return "this is a post method,subjectId:" + subjectId;
	}

	@RequestMapping(value = "/subject/{subjectId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String subjectDelete(@PathVariable("subjectId") String subjectId) {
		return "this is a delete method,subjectId:" + subjectId;
	}

	@RequestMapping(value = "/subject/{subjectId}", method = RequestMethod.PUT)
	@ResponseBody
	public String subjectPut(@PathVariable("subjectId") String subjectId) {
		return "this is a put method,subjectId:" + subjectId;
	}

	@RequestMapping(value = "converter.do")
	@ResponseBody
	public String converter(Boolean bool) {
		return bool.toString();
	}

}
