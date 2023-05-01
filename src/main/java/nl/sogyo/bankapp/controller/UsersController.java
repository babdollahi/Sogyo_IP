package nl.sogyo.bankapp.controller;

import nl.sogyo.bankapp.model.BalanceModel;
import nl.sogyo.bankapp.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UsersController {
    @Autowired
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new BalanceModel());
        return "login_page";
    }
    @PostMapping("/login")
    public String processLoginForm(@RequestParam int accountNumber, @RequestParam int pinNumber, Model model) {
        Optional<BalanceModel> balanceModel = usersService.checkLogin(accountNumber, pinNumber);
        if (balanceModel != null) {
            return "personal_page";
        } else {
            return "error_page";
        }
    }
}

