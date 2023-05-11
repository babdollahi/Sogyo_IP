package nl.sogyo.bankapp.controller;

import jakarta.servlet.http.HttpSession;
import nl.sogyo.bankapp.model.BalanceModel;
import nl.sogyo.bankapp.model.ChangePasswordModel;
import nl.sogyo.bankapp.service.InsufficientFundsException;
import nl.sogyo.bankapp.service.UsersService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Controller
public class UsersController {
    @Autowired
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }
    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new BalanceModel());
        return "register_page";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute BalanceModel registerRequest, Model model) {
        int accountNumber = registerRequest.getAccountNumber();
        int pinNumber = registerRequest.getPinNumber();
        String email = registerRequest.getEmail();

        if (accountNumber == 0 || pinNumber == 0) {
            model.addAttribute("error", "Please provide valid account number and pin number.");
            return "register_page";
        }

        try {
            usersService.saveRegistration(registerRequest); // Save the registerRequest object
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while registering the user.");
            return "register_page";
        }

        model.addAttribute("accountNumber", accountNumber);
        model.addAttribute("email", email);
        model.addAttribute("pinNumber", pinNumber);

        return "register_success";
    }


    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new BalanceModel());
        return "login_page";
    }

    @PostMapping("/login")
    public String processLoginForm(HttpSession session,
                                   @RequestParam int accountNumber,
                                   @RequestParam int pinNumber,
                                   @NotNull Model model) {
        Optional<BalanceModel> balanceModelOptional = usersService.checkLogin(accountNumber, pinNumber);
        session.setAttribute("accountNumber", accountNumber);
        model.addAttribute("accountNumber", accountNumber);
        if (balanceModelOptional.isPresent() && balanceModelOptional.get() != null) {
            BalanceModel balanceModel = balanceModelOptional.get();
            model.addAttribute("userBalance", balanceModel.getBalance());
            return "personal_page";
        } else {
            return "error_page";
        }
    }

    @PostMapping("/transaction")
    public String processTransactionForm(HttpSession session,
                                         @RequestParam("amount") double amount,
                                         @RequestParam("transactionType") String transactionType,
                                         Model model) {
        int accountNumber = (int) session.getAttribute("accountNumber");
        try {
            if(transactionType.equals("deposit")) {
                double newBalance = usersService.addDeposit(accountNumber, amount);
                model.addAttribute("amount", amount);
                model.addAttribute("newBalance", newBalance);
                return "deposit_success";
            } else if(transactionType.equals("withdrawal")) {
                double newBalance = usersService.withdrawal(accountNumber, amount);
                model.addAttribute("amount", amount);
                model.addAttribute("newBalance", newBalance);
                return "withdrawal_success";
            }
            else {
                return "error_page";
            }
        } catch (InsufficientFundsException e) {
            return "insufficient_funds";
        }
    }

    @GetMapping("/loanCalculation")
    public String getLoanPage(
                              HttpSession session,
                              Model model){
        int accountNumber = (int) session.getAttribute("accountNumber");
        model.addAttribute("accountNumber", accountNumber);
        return "loan_calculation";
    }

    @PostMapping("/loanCalculation")
    public String processLoanForm(HttpSession session,
                                  @RequestParam("loanAmount") double loanAmount,
                                  @RequestParam("repaymentYears") int repaymentYears,
                                  @RequestParam("interestRate") double interestRate,
                                  @NotNull Model model) {
        int accountNumber = (int) session.getAttribute("accountNumber");
        Map<String, Double> repayments = usersService.loanCalculation(accountNumber,
                                                             interestRate,
                                                             repaymentYears,
                                                             loanAmount);
        model.addAttribute("loanAmount", loanAmount);
        model.addAttribute("repaymentYears", repaymentYears);
        model.addAttribute("interestRate", interestRate);
        model.addAttribute("repayments", repayments);

        return "loan_repayment";
    }

    @GetMapping("/changePassword")
    public String getChangePasswordPage(Model model) {
        return "change_password";
    }

}

