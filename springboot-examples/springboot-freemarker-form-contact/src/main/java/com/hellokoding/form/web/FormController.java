package com.hellokoding.form.web;

import com.hellokoding.form.model.User;
import com.hellokoding.form.service.SendingMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class FormController {
    @Autowired
    SendingMailService sendingMailService;

    @GetMapping("/")
    public String index() {
        return "redirect:/form";
    }

    @GetMapping("/form")
    public String formGet(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/form")
    public String formPost(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        model.addAttribute("noErrors", true);
        model.addAttribute("user", user);
        String subject = user.getName() + " " + user.getEmail() + " sent you a message";
        sendingMailService.sendMail(subject, user.getMessage());
        return "form";
    }
}
