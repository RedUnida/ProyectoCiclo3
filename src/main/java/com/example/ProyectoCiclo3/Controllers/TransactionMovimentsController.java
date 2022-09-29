package com.example.ProyectoCiclo3.Controllers;


import com.example.ProyectoCiclo3.Entities.Employee;
import com.example.ProyectoCiclo3.Entities.Enterprise;
import com.example.ProyectoCiclo3.Entities.Transaction;
import com.example.ProyectoCiclo3.Services.Response;
import com.example.ProyectoCiclo3.Services.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.util.Date;
import java.util.ArrayList;

@Controller
@RequestMapping("movements")
public class TransactionMovimentsController {

    private TransactionService service;

    public TransactionMovimentsController(TransactionService ser){
        this.service = ser;
    }

    @GetMapping("index")
    public String index(Model transanctions){
        ArrayList<Transaction> transactionsDB = this.service.selectAll();
        transanctions.addAttribute("mismovimientos", transactionsDB);

        return "transactions/consult";
    }


    @GetMapping("create")
    public String create(){
        return "transactions/insert";
    }

    @PostMapping("create")
    public RedirectView create(Transaction data){
        data.setCreatedat(new Date());
        data.setUpdatedat(new Date());
        Response response = this.service.createTransactionWithOutId(data);
        return new RedirectView("/movements/index");
    }
}
