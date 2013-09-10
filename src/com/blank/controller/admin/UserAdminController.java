package com.blank.controller.admin;

import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blank.entity.UserAdmin;
import com.blank.service.UserAdminService;
import com.blank.util.enumerator.UserAdminStatusEnum;
import com.blank.util.exception.ServiceException;
import com.blank.util.exception.ValidationException;
import com.blank.util.pagination.Pagination;

@Controller
@RequestMapping("/admin/user_admin")
public class UserAdminController {

	@Autowired
	private UserAdminService userAdminService;
	
	@RequestMapping("index")
	public ModelAndView index(String email,UserAdminStatusEnum status, Integer page) throws Exception {
		ModelAndView mvc = new ModelAndView("admin/user_admin/index");
		Pagination pagination = new Pagination("/admin/user_admin?page=$page&status="+(status!=null?status:""));
		pagination.setPage(page);
		pagination.setTotal(userAdminService.count(email, status));
		pagination.build();
		mvc.addObject("userAdmins", userAdminService.find(email, status, page,pagination.getPageSize() ));
		mvc.addObject("pagination", pagination);
		return mvc;
	}
	
	@RequestMapping("detail")
	public ModelAndView detail(Long id,RedirectAttributes redirectAttributes,HttpSession session) throws Exception {
		ModelAndView mvc = new ModelAndView("admin/user_admin/detail");
		UserAdmin userAdmin = (UserAdmin) session.getAttribute("userAdmin");
		session.removeAttribute("userAdmin");
		if(userAdmin!=null){
			mvc.addObject("userAdmin", userAdmin);
		}else{
			if(id!=null){
				mvc.addObject("userAdmin", userAdminService.get(id));
			}
		}
		return mvc;
	}
	
	@RequestMapping("save")
	public String save(UserAdmin userAdmin,RedirectAttributes redirectAttributes,HttpSession session) throws Exception {
		try{
			userAdminService.save(userAdmin);
			redirectAttributes.addFlashAttribute("flash_message", "Usuário salvo com sucesso");
			return "redirect:/admin/user_admin";
		}catch(ServiceException e){
			redirectAttributes.addFlashAttribute("flash_error", "Erro ao salvar usuário: "+e.getMessage());
			session.setAttribute("userAdmin", userAdmin);
			return "redirect:/admin/user_admin/detail/"+userAdmin.getKey().getId();
		}catch(ValidationException e){
			for (Iterator<String> iterator = e.getErrors().keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				redirectAttributes.addFlashAttribute(key,e.getErrors().get(key));
			}
			session.setAttribute("userAdmin", userAdmin);
			return "redirect:/admin/user_admin/detail/"+userAdmin.getKey().getId();
		}
	}
	
	@RequestMapping("delete")
	public String delete(@RequestParam(required=true) Long id,RedirectAttributes redirectAttributes) throws Exception {
		try{
			userAdminService.remove(id);
			redirectAttributes.addFlashAttribute("flash_message", "Usuário deletado com sucesso");
		}catch(ServiceException e){
			redirectAttributes.addFlashAttribute("flash_error", "Erro ao deletar usuário: "+e.getMessage());
		}
		
		return "redirect:/admin/user_admin";
	}
	
	
}
