package com.blank.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blank.service.UserAdminService;
import com.blank.util.enumerator.JsonSerializerEnum;
import com.blank.util.exception.ServiceException;
import com.blank.util.json.JsonView;

@Controller
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private UserAdminService userAdminService;
	
	@RequestMapping("1")
	public void um(HttpServletResponse response) throws Exception {
		JsonView view = new JsonView(response, JsonSerializerEnum.TESTE1);
		try{
			view.addObject("userAdmin",userAdminService.find(null, null, 5, 1).get(0));
			view.setResult(true);	
		}catch(ServiceException se){
			view.setResult(false);
		}
	}
	
	@RequestMapping("2")
	public void dois(HttpServletResponse response) throws Exception {
		JsonView view = new JsonView(response, JsonSerializerEnum.TESTE2);
		try{
			view.setData(userAdminService.find(null, null, null, null));
			view.setResult(true);	
		}catch(ServiceException se){
			view.setResult(false);
		}
	}
	
	@RequestMapping("3")
	public void tres(HttpServletResponse response) throws Exception {
		JsonView view = new JsonView(response, JsonSerializerEnum.TESTE3);
		try{
			view.setData(userAdminService.find(null, null, 5, 1).get(0));
			view.setResult(true);	
		}catch(Exception se){
			view.setResult(false);
		}
	}
	
	@RequestMapping("4")
	public void quatro(HttpServletResponse response) throws Exception {
		JsonView view = new JsonView(response, JsonSerializerEnum.WITHOUT_DATA);
		try{
			view.setResult(true,"mensagem");	
		}catch(Exception se){
			view.setResult(false);
		}
	}
}
