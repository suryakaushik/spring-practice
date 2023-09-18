package com.kaushik.springbootmvn.restapi.mycontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MyController {
    
//	http://localhost:8080/getHello?name=kk  or  curl http://localhost:8080/getHello?name=kk
    @GetMapping("/getHello") //It is a special type of RequestMapping(type=GET)
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    	return String.format("Hello %s!", name);
    }
    
//	http://localhost:8080/postHello?name=kk
    @PostMapping("/postHello") //It is a special type of RequestMapping(type=POST)
    public @ResponseBody String postHello(@RequestParam(value = "name", defaultValue = "World") String name) {
    	return String.format("Hello %s!", name);
    }
  
//    http://localhost:8080/hello
    @RequestMapping("/hello")
    public String sayHello() {
    	return String.format("Hello!");
    }
    

	//  http://localhost:8080/hello
	  @RequestMapping("hello")
	  @ResponseBody  // This tells Spring that response is not a file, its a simple datatype(string)
	  public String printData() {
	  	return "home.jsp";
	  }
	  
	
	//http://localhost:8080/home?name=kaushik
	// NOTE: This only works if we have Tomcat Jasper as a Spring Dependency to convert JSP into a servlet
		// USING SERVLETS AND JSP
	@RequestMapping("home")
	public String openWebPage(HttpServletRequest req, HttpServletResponse resp) { // Since URL of the webpage does not change, Spring uses RequestDispatcher(otherwise it might use response.send). Here one servlet and jsp file share same RequestDispatcher 
		String name=req.getParameter("name");
		System.out.println(name);
		HttpSession session=req.getSession();
		session.setAttribute("name", name);
		
		return "home.jsp";  // Spring by default dispatches servlet, searches for home.jsp inside src/main/webapp/ folder and helps us to download that file
	}
	
	//http://localhost:8080/home?name1=kaushik
	// SHARING DATA BETWEEN VIEW AND CONTROLLER USING SPRING MVC
	@RequestMapping("home")
	public String openHomePage(@RequestParam("name1") String name, HttpSession session) {
		System.out.println(name);
		session.setAttribute("my_name", name); // PASSING DATA-->YOU CAN USE ${my_name} in .jsp files-->This is JSTL syntax
		
		return "home.jsp";  // PASSING VIEW
	}
	
	//http://localhost:8080/home?name1=kaushik
	// USING SPRING MVC
	@RequestMapping("home")
	public ModelAndView openMVCPage(@RequestParam("name1") String name) {
		ModelAndView mv=new ModelAndView();
		mv.addObject("my_name",name);
		mv.setViewName("home.jsp");
		return mv;  // PASSING MODEL AND VIEW
	}
	
	//NOTE: To accept and return JSON objects, we can use SPRING REST+JERSEY
}
