package com.example.ProyectoCiclo3.Services;

import com.example.ProyectoCiclo3.Entities.Profile;
import com.example.ProyectoCiclo3.Entities.Profile;
import com.example.ProyectoCiclo3.repository.IProfileRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProfileService {

    private IProfileRepository profileRepository;
    public ProfileService(IProfileRepository rep){
        this.profileRepository = rep;
    }
    public ArrayList<Profile> selectAllEnterprise(){
        return (ArrayList <Profile>) this.profileRepository.findAll();
    }

    public Response createProfile (Profile data, long id){
        Response response = new Response();
        Profile existe = selectById(data.getId());
        if(existe != null){
            response.setCode(500);
            response.setMessage("La transaccion ya existe");
            return  response;
        }

        data.setId(id);
        this.profileRepository.save(data);
        response.setCode(200);
        response.setMessage("¡Su empleado ha sido registrada correctamente!");
        return response;
    }

    public Response deleteProfile (long id){
        Response response = new Response();
        this.profileRepository.deleteById(id);
        response.setCode(200);
        response.setMessage("¡Su empleado ha sido eliminada correctamente!");
        return response;
    }

    public Response upDateProfile (Profile data, long id){
        Response response = new Response();
        Profile existe = selectById(data.getId());
        if (existe == null){
            response.setCode(500);
            response.setMessage("¡El ID digitado no existe!");
            return response;
        }
        existe.setImage(data.getImage());
        existe.setPhone(data.getPhone());
        existe.setCreatedat(data.getCreatedat());
        existe.setUpdatedat(data.getUpdatedat());

        this.profileRepository.save(existe);
        response.setCode(200);
        response.setMessage("¡Sus datos han sido actualizados correctamente!");
        return response;
    }
    public Profile selectById(long Id){
        Optional<Profile> existe = this.profileRepository.findById(Id);
        if(existe.isPresent()){
            return existe.get();
        }
        else {
            return null;
        }
    }

}
