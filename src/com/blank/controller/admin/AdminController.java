package com.blank.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.blank.entity.UserAdmin;
import com.blank.service.UserAdminService;
import com.blank.util.Constant;
import com.blank.util.enumerator.UserAdminStatusEnum;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
@RequestMapping("/admin")
public class AdminController  {
	
	@Autowired
	private UserAdminService userAdminService;
	
	@RequestMapping("login")
	public ModelAndView login(HttpSession session) throws Exception {
		ModelAndView mvc = new ModelAndView("admin/login");
		UserService userService = UserServiceFactory.getUserService();
		mvc.addObject("url",  userService.createLoginURL("/admin/login/callback"));
		
		
		return mvc;
	}
	
	@RequestMapping("login/callback")
	public String loginCallback(HttpSession session) throws Exception {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		UserAdmin userAdmin = null;
		if (user != null) {
			userAdmin = userAdminService.getByEmailStatus(user.getEmail(), UserAdminStatusEnum.ENABLED);	
		}
		if(userAdmin!=null){
			session.setAttribute(Constant.ADMIN_AUTH_USER,userAdmin);
			return "redirect:/admin";
		}else{
			return "redirect:/admin/login";
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest req) throws Exception {
		req.getSession().removeAttribute(Constant.ADMIN_AUTH_USER);
		return "redirect:/admin/login";
	}
	
	@RequestMapping("index")
	public ModelAndView index(){
		return new ModelAndView("admin/index");
	}
	
	@RequestMapping("startdb")
	public String startDb() throws Exception {
		userAdminService.startDb();
		return "redirect:/admin/login";
	}
}
