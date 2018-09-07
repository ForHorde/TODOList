package com.javamaster.controller;


import com.javamaster.domain.User;
import com.javamaster.exceptions.UserExistException;
import com.javamaster.service.UserService;
import com.javamaster.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    UserService userService = new UserServiceImpl();

    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String register(Model model, @RequestParam(value="name") String name, @RequestParam(value="password") String password) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(name);
        try {
            userService.addUser(user);
        } catch (UserExistException e) {
            model.addAttribute("user", user);
            return "registration";
        }

        return "login";
    }

    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String register() {
        return "registration";
    }
}
