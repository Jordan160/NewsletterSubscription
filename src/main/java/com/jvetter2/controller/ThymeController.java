package com.jvetter2.controller;

import com.jvetter2.Subscription;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ThymeController {
    @GetMapping("/")
    public String index(Model model) {
        Subscription subscription = new Subscription();
        model.addAttribute("name");
        model.addAttribute("email");
        return "index";
    }

    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute Subscription subscription, BindingResult errors, Model model) {
        return "redirect:/demo/subscribe?name=" + subscription.getName() + "&email=" + subscription.getEmail();
    }

    @GetMapping("/thankyou")
    public String thankyou(Model model) {
        return "thankyou";
    }

    @GetMapping(path = "/unsubscribelanding")
    public String unsubscribe(Model model, HttpServletResponse response) throws IOException {
        Subscription subscription = new Subscription();
        model.addAttribute("email");
        return "unsubscribelanding";
        //response.sendRedirect("/unsubscribe?email=" + subscription.getEmail());
    }
}
