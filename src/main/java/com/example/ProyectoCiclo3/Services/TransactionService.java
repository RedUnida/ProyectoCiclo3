package com.example.ProyectoCiclo3.Services;

import com.example.ProyectoCiclo3.Entities.Transaction;
import com.example.ProyectoCiclo3.repository.ITransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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


    public ArrayList<Transaction> selectAll(){
        return (ArrayList<Transaction>) this.transactionRepository.findAll();
    }


    public Response createTransactionWithOutId(Transaction data){
        Response response = new Response();

        ArrayList<Transaction> all_transactions = (ArrayList<Transaction>) this.transactionRepository.findAll();

        if( all_transactions.size() == 0){
            long default_id = 1;
            data.setId( default_id );
        }else{
            ArrayList<Long> ids = new ArrayList<>();
            all_transactions.forEach((n) -> ids.add(n.getId()));
            data.setId(Collections.max(ids) + 1);
        }

        Optional<Transaction> existe = this.transactionRepository.findById(data.getId());
        if(existe == null){
            response.setCode(500);
            response.setMessage("La transaccion ya existe");
            return  response;
        }

        this.transactionRepository.save(data);
        response.setCode(200);
        response.setMessage("Transaccion registrada exitosamente");
        return response;
    }

    public Response createTransaction(Transaction data, long id ){
        Response response = new Response();



        Transaction existe = selectById(id);
        if(existe != null){
            response.setCode(500);
            response.setMessage("La transaccion ya existe");
            return  response;
        }

        data.setId(id);
        this.transactionRepository.save(data);
        response.setCode(200);
        response.setMessage("Transaccion registrada exitosamente");
        return response;
    }


    public Response deleteTransaction(long id){
        Response response = new Response();
        Transaction existe = selectById(id);
        if(existe == null){
            response.setCode(500);
            response.setMessage("La transaccion no existe");
            return  response;
        }
        this.transactionRepository.deleteById(id);
        response.setCode(200);
        response.setMessage("Transaccion eliminado exitosamente");
        return response;
    }

    public Response updateTransaction(Transaction data, long id){
        Response response = new Response();
        Transaction existe = selectById(id);
        if (existe == null){
            response.setCode(500);
            response.setMessage("La transaccion no existe.");
            return response;
        }
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
