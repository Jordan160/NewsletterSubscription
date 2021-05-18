package com.jvetter2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "/demo")
public class SubscriptionController {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @GetMapping(path = "/subscribe")
    @ResponseBody
     void subscribe(@RequestParam String name, @RequestParam String email, HttpServletResponse response) throws IOException {
        Subscription s = new Subscription();
        s.setName(name);
        s.setEmail(email.toLowerCase());
        subscriptionRepository.save(s);
        response.sendRedirect("/thankyou");
    }

    @Transactional
    @GetMapping(path = "/unsubscribe")
    public @ResponseBody
    String unsubscribe(@RequestParam String email) {
        long emailsDeleted = subscriptionRepository.deleteByEmail(email.toLowerCase());
        if (emailsDeleted > 0) {
            return "You have successfully removed your email address";
        } else {
            return "This email is not currently subscribed: " + email;
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Subscription> getAllUsers() {
        return subscriptionRepository.findAll();
    }
}