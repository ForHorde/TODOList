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

/**
 * Контроллер для управления пользователями
 */
@Controller
public class UserController {

    UserService userService = new UserServiceImpl();

    /**
     * Метод добавления нового пользователя в бд
     * @param model - модель для добавления сообщения об ошибке
     * @param name - имя пользователя
     * @param password - пароль
     * @return registration.jsp - страница регистрации в случае, если имя пользователя существует или поля имени и пароля пустые
     * @return login.jsp - страница авторизации
     */
    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String register(Model model, @RequestParam(value="name") String name, @RequestParam(value="password") String password) {
        User user = new User();
        if (password!=""&&name!="") {
            user.setPassword(password);
            user.setUsername(name);
        }
        else {
            model.addAttribute("error", "Поля имя и пароль не должны быть пустыми");
            return "registration";
        }
        try {
            userService.addUser(user);
        } catch (UserExistException e) {
            model.addAttribute("user", user);
            return "registration";
        }

        return "login";
    }

    /**
     * Метод регистрации пользователя
     * @return registration.jsp - страница регистрации
     */
    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String register() {
        return "registration";
    }
}
