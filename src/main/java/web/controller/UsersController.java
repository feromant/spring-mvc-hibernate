package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceJpa;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserServiceJpa userServiceJpa;

    @Autowired
    public UsersController(UserServiceJpa userServiceJpa) {
        this.userServiceJpa = userServiceJpa;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userServiceJpa.getAllUsers());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id,  Model model) {
        model.addAttribute("user", userServiceJpa.getUserById(id));
        return "users/delete";
    }

    @GetMapping("/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userServiceJpa.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String updateUserById(@PathVariable("id") Long id,  Model model) {
        model.addAttribute("user", userServiceJpa.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userServiceJpa.updateUser(user);
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userServiceJpa.deleteUserById(id);
        return "redirect:/users";
    }
}
