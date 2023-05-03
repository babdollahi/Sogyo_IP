package nl.sogyo.bankapp.controller;

import nl.sogyo.bankapp.model.BalanceModel;
import nl.sogyo.bankapp.model.DepositModel;
import nl.sogyo.bankapp.service.UsersService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        Optional<BalanceModel> balanceModelOptional = usersService.checkLogin(accountNumber, pinNumber);
        if (balanceModelOptional.isPresent() && balanceModelOptional.get() != null) {
            BalanceModel balanceModel = balanceModelOptional.get();
            model.addAttribute("accountNumber", balanceModel.getAccountNumber());
            model.addAttribute("userBalance", balanceModel.getBalance());
            return "personal_page";
        } else {
            return "error_page";
        }
    }
    @PostMapping("/deposit")
    public String processDepositForm(DepositModel depositModel, BalanceModel balanceModel, Model model) {
        double newBalance = usersService.addDeposit(balanceModel.getAccountNumber(), depositModel.getAmount());
        model.addAttribute("accountNumber", balanceModel.getAccountNumber());
        model.addAttribute("amount", depositModel.getAmount());
        model.addAttribute("newBalance", newBalance);
        return "deposit_success";



    }

}

