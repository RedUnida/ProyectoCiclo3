package com.example.ProyectoCiclo3.Services;

import com.example.ProyectoCiclo3.Entities.Employee;
import com.example.ProyectoCiclo3.repository.IEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmployeeService {

    private IEmployeeRepository employeeRepository;
    public EmployeeService(IEmployeeRepository rep){
        this.employeeRepository = rep;
    }
    public ArrayList<Employee> selectAllEmployees(){
        return (ArrayList <Employee>) this.employeeRepository.findAll();
    }

    public Response createEmployee (Employee data){
        Response response = new Response();
        Optional<Employee> existe = this.employeeRepository.findById(data.getId());
        if(existe != null){
            response.setCode(500);
            response.setMessage("La transaccion ya existe");
            return  response;
        }

        this.employeeRepository.save(data);
        response.setCode(200);
        response.setMessage("¡Su empleado ha sido registrada correctamente!");
        return response;
    }

    public Response deleteEmployee (long id){
        Response response = new Response();
        this.employeeRepository.deleteById(id);
        response.setCode(200);
        response.setMessage("¡Su empleado ha sido eliminada correctamente!");
        return response;
    }

    public Response upDateEmployee (Employee data, long id){
        Response response = new Response();
        Optional<Employee> valida = this.employeeRepository.findById(data.getId());
        if (valida == null){
            response.setCode(500);
            response.setMessage("¡El ID digitado no existe!");
            return response;
        }
        Employee existe = valida.get();
        existe.setProfile(data.getProfile());
        existe.setTransactions(data.getTransactions());
        existe.setEmails(data.getEmails());
        existe.setEnterprise(data.getEnterprise());
        existe.setCreatedat(data.getCreatedat());
        existe.setUpdatedAT(data.getUpdatedAT());
        existe.setRole(data.getRole());
        this.employeeRepository.save(existe);
        response.setCode(200);
        response.setMessage("¡Sus datos han sido actualizados correctamente!");
        return response;
    }
    public ArrayList<Employee> selectById(long Id){
        return this.employeeRepository.findEmployeeById(Id);
    }

}



