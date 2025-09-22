package web.controller;

import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        User newUser = new User();
        newUser.setRoles(new HashSet<>());
        model.addAttribute("user", newUser);
        model.addAttribute("roles", roleService.getAllRoles()); // <-- добавляем все роли
        model.addAttribute("isNew", true);
        return "edit";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "roles", required = false) Set<Long> roleIds) {
        if (roleIds != null) {
            Set<Role> roles = roleService.getRolesByIds(roleIds);
            user.setRoles(roles);
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.get(id));
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("isNew", false);
        return "edit";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "roles", required = false) Set<Long> roleIds) {
        if (roleIds != null) {
            Set<Role> roles = roleService.getRolesByIds(roleIds);
            user.setRoles(roles);
        }
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
