package web.controller;

import web.model.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController{
	private final UserService userService;

	@Autowired
	public UserController(UserService userService){
		this.userService = userService;
	}

	@GetMapping
	public String showUserPage(Principal principal, Model model){
		String username = principal.getName();
		User user = userService.findByUsername(username);

		boolean isAdmin = user.getRoles().stream()
				.anyMatch(role -> "ROLE_ADMIN".equals(role.getName()));

		model.addAttribute("user",user);
		model.addAttribute("isAdmin", isAdmin);
		return "user";
	}
}
