package choi.web.springboot.controller;

import choi.web.springboot.domain.User;
import choi.web.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String signIn(Model model) {
        model.addAttribute("user", new User("", "", "", "", "Y"));
        return "signIn";
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("user", new User("", "", "", "", "Y"));
        return "signUp";
    }

    @PostMapping("/registUser")
    public String registUser(@Validated User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "signUp";
        }

        int result = userService.registUser(user);
        if (result == 0) {
            model.addAttribute("result", "등록에 실패하였습니다.");
            return "signUp";
        } else if (result == -1) {
            model.addAttribute("result", "이미 가입된 아이디입니다.");
            return "signUp";
        } else {
            model.addAttribute("result", "가입이 완료되었습니다.");
            return "signIn";
        }
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        model.addAttribute("user", new User("", "", "", "", "Y"));
        session.invalidate();

        return "signIn";
    }

    @GetMapping("/editUser")
    public String editUser(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("user", loginUser);

        return "user/edit";
    }

    @PostMapping("/editUser")
    public String editUser(@Validated User user, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }

        int result = userService.editUser(user);
        if (result == 0) {
            model.addAttribute("result", "수정에 실패하였습니다.");
        } else {
            model.addAttribute("result", "수정이 완료되었습니다.");
            session.setAttribute("loginUser", user);
        }
        return "user/edit";
    }

}
