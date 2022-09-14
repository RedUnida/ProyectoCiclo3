package com.example.ProyectoCiclo3.Services;

import com.example.ProyectoCiclo3.Entities.Transaction;
import com.example.ProyectoCiclo3.repository.ITransactionRepository;
import org.springframework.stereotype.Service;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TransactionService {

    private ITransactionRepository transactionRepository;
    public TransactionService(ITransactionRepository rep){
        this.transactionRepository = rep;
    }
    public ArrayList<Transaction> findTransaction(long id){
        return  this.transactionRepository.findTransaction(id);
    }

    public Response createTransaction(Transaction data, long id){
        Response response = new Response();

        ArrayList<Transaction> existe = this.transactionRepository.findTransaction(data.getId());
        if(existe != null && existe.size() > 0){
            response.setCode(500);
            response.setMessage("Error, el correo electronico ya esta en uso.");
            return  response;
        }

        this.transactionRepository.save(data);
        response.setCode(200);
        response.setMessage("Transaccion registrada exitosamente");
        return response;
    }

    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public Response deleteTransaction(long id){
        Response response = new Response();
        this.transactionRepository.deleteById(id);
        response.setCode(200);
        response.setMessage("Transaccion eliminado exitosamente");
        return response;
    }

    public Response updateTransaction(Transaction data, long id){
        Response response = new Response();
        Transaction existe = selectById(id);
        existe.setCreatedat(data.getCreatedat());
        existe.setUpdatedat(data.getUpdatedat());
        existe.setAmount(data.getAmount());
        existe.setConcept(data.getConcept());
        existe.setId(id);
        this.transactionRepository.save(existe);
        response.setCode(200);
        response.setMessage("Transaccion actualizada exitosamente");
        return response;
    }

    public Transaction selectById(long Id){
        Optional<Transaction> existe = this.transactionRepository.findById(Id);
        if(existe.isPresent()){
            return existe.get();
        }
        else {
            return null;
        }
    }

}
