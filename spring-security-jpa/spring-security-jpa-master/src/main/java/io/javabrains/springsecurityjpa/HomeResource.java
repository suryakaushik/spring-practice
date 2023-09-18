package io.javabrains.springsecurityjpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

    
	// @GetMapping("/common")
	// public String showCommon(Principal p, Model model, HttpSession ses) {
	// 	model.addAttribute("currentUser", p.getName());
	// 	ses.setAttribute("fullUser", service.findByEmai(p.getName()).get());
	// 	return "CommonPage";
	// }
}

