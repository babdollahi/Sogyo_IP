package nl.sogyo.bankapp.controller;

import nl.sogyo.bankapp.model.BalanceModel;
import nl.sogyo.bankapp.model.DepositModel;
import nl.sogyo.bankapp.service.UsersService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @PostMapping("/transaction")
    public String processTransactionForm(@RequestParam("accountNumber") int accountNumber,
                                         @RequestParam("amount") double amount,
                                         @RequestParam("transactionType") String transactionType,
                                         Model model) {
        if(transactionType.equals("deposit")) {
            double newBalance = usersService.addDeposit(accountNumber, amount);
            model.addAttribute("accountNumber", accountNumber);
            model.addAttribute("amount", amount);
            model.addAttribute("newBalance", newBalance);
            return "deposit_success";
        } else if(transactionType.equals("withdrawal")) {
            double newBalance = usersService.withdrawal(accountNumber, amount);
            model.addAttribute("accountNumber", accountNumber);
            model.addAttribute("amount", amount);
            model.addAttribute("newBalance", newBalance);
            return "withdrawal_success";
        } else {
            return "error_page";
        }
    }


}

