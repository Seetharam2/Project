package com.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/add")
public class AddServlet {
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView add(@RequestParam("num1") int i, @RequestParam("num2") int j, HttpServletRequest request, HttpServletResponse response)
	{
//		int i = Integer.parseInt(request.getParameter("num1"));
//		int j = Integer.parseInt(request.getParameter("num2"));
		int k = i+j;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("display");
		mv.addObject("result",k);
		return mv;
	}

}
