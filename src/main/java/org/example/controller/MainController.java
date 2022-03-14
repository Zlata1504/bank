package org.example.controller;

import jakarta.validation.Valid;
import org.example.dao.DebitCardDao;
import org.example.dao.LoanDao;
import org.example.dao.UserDao;
import org.example.entity.DebitCard;
import org.example.entity.Loan;
import org.example.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Controller
public class MainController {
    private final UserDao userDao;
    private User user = null;
    private final DebitCardDao debitCardDao;
    private final LoanDao loanDao;

    public MainController(UserDao userDao, DebitCardDao debitCardDao, LoanDao loanDao) {
        this.userDao = userDao;
        this.debitCardDao = debitCardDao;
        this.loanDao = loanDao;
    }

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/cards")
    public String cards(Model model) {
        model.addAttribute("cards", user.getUsersCard());

        return "cards";
    }

    @GetMapping("/loans")
    public String loans(Model model) {
        model.addAttribute("loans", user.getUsersLoans());
        return "loans";
    }

    @PostMapping("/cards")
    public String addCard(@ModelAttribute @Valid DebitCard card) {
        Random rand = new Random();
        String card1 = "SE";

        for (int i = 0; i < 14; i++) {
            int n = rand.nextInt(10) + 0;
            card1 += Integer.toString(n);
        }

        card.setNumberOfCard(card1);
        int cvv = (int) (Math.random() * 900) + 100;
        card.setCvv(cvv);

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.YEAR, 2);
        Date newDate = instance.getTime();

        card.setExpirationDate(newDate);
        card.setUser(user);

        debitCardDao.addCard(card);
        user.getUsersCard().add(card);
        userDao.update(user);

        return "redirect:/cards";
    }

    @PostMapping("/loans")
    public String addLoans(@ModelAttribute @Valid Loan loan) {
        loan.setMonthlyPayment(generateMonthlyPayment(loan.getSum(), loan.getPercent(), loan.getCreditTerm()));
        loan.setDateOfIssue(generationDate());
        loan.setUser(user);

        loanDao.save(loan);
        user.getUsersLoans().add(loan);
        userDao.update(user);

        return "redirect:/loans";
    }

    @GetMapping("/newCard")
    public String newCard(Model model) {
        model.addAttribute("card", new DebitCard());

        return "addCards";
    }

    @GetMapping("/newLoan")
    public String newLoan(Model model) {
        model.addAttribute("loan", new Loan());

        return "addLoans";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new User());

        return "login";
    }

    @GetMapping("/registration")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute("user") User user) {
        User loginUser = userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (loginUser != null) {
            this.user = loginUser;

            return "redirect:/cards";
        }

        return "login";
    }

    @PostMapping("/registration")
    public String doRegister(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "registration";

        user.setRegistrationDate(generationDate());
        user.setUsersCard(new ArrayList<>());
        user.setUsersLoans(new ArrayList<>());
        userDao.save(user);

        return "login";
    }

    private Date generationDate() {
        return new Date();
    }

    private double generateMonthlyPayment(double sum, double percent, int creditTerm) {
        double total = sum * (1 + percent / 100);
        return total / creditTerm;
    }
}