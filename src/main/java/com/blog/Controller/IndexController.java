package com.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blog.DTO.IndexResponseDTO;
import com.blog.Service.BlogService;

@Controller
public class IndexController {
	
	@Autowired
	private BlogService service;
	
	@GetMapping("/")
	public String getIndexPage(Model model) {
		
		List<IndexResponseDTO> allBlog = service.getAllBolgs();
		model.addAttribute("responseForm",allBlog);
		
		return "index";
	}

}
