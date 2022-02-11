package org.example.controller;

import dao.DebitCardDao;
import dao.LoanDao;
import jakarta.validation.Valid;
import org.example.entity.DebitCard;
import org.example.entity.Loan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class MainController {
//    private List<Loan> loans = new ArrayList<>();
//    private List<DebitCard> cards = new ArrayList<>();
    private final DebitCardDao debitCardDao;
    private final LoanDao loanDao;

    public MainController(DebitCardDao debitCardDao,LoanDao loanDao){
        this.debitCardDao=debitCardDao;
        this.loanDao=loanDao;
    }
    @GetMapping("/")
    public String mainPage(){
        return "main";
    }

    @GetMapping("/cards")
    public String cards(Model model){
        model.addAttribute("cards",debitCardDao.show());

        return "cards";
    }

    @GetMapping("/loans")
    public String loans(Model model){
        model.addAttribute("loans",loanDao.show());
        return "loans";
    }
    @PostMapping("/cards")
    public String addCard(@ModelAttribute @Valid DebitCard card){
        Random rand=new Random();
        String card1="SE";
        for (int i = 0; i < 14; i++)
        {
            int n = rand.nextInt(10) + 0;
            card1 += Integer.toString(n);
        }
        for (int i = 0; i < 16; i++)
        {
            if(i % 4 == 0)
                System.out.print(" ");
            System.out.print(card1.charAt(i));
        }
        card.setNumberOfCard(card1);
        Scanner scan=new Scanner(System.in);
        System.out.println("Come up with a three-digit code");
        int cvv= scan.nextInt();
        card.setCvv(cvv);
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.YEAR, 2);
        Date newDate = instance.getTime();
        card.setExpirationDate(newDate);
        debitCardDao.save(card);
        return "redirect:/cards";
    }
    @PostMapping("/loans")
    public String addLoans(@ModelAttribute @Valid Loan loan) {
        
        loan.setMonthlyPayment(genMonthlyPayment(loan.getSum(), loan.getPercent(), loan.getCreditTerm()));

        loanDao.save(loan);
            return "addLoans";

        }

    private double genMonthlyPayment(double sum, double percent, int creditTerm) {
        double total = sum * (1 + percent/100);
        double monthlyPayment = total / creditTerm;
        return monthlyPayment;
    }

        @GetMapping("/newCard")
        public String newCard (Model model){
            model.addAttribute("card", new DebitCard());
            return "cards";
        }
        @GetMapping("/newLoan")
        public String newLoan (Model model){
            model.addAttribute("loan", new Loan());
            return "loans";
        }





}
