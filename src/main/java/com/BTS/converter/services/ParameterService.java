/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.services;

import com.BTS.converter.entities.ClientPartner;
import com.BTS.converter.entities.Parameter;
import com.BTS.converter.repositories.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Reza
 */
@Service
public class ParameterService {
    @Autowired
    ParameterRepository paramRepo;
    
    public Iterable<Parameter> getAll(){
        return paramRepo.findAll();
    }
    
    public boolean save(Parameter param){
        if (paramRepo.save(param)!=null) {
            return true;
        } else {
            return false;
        }
    }
    
     public void delete(@RequestParam("id")int id){
        paramRepo.deleteById(id);
    }
    
    public Parameter getById(String id){
        return paramRepo.findById(Integer.parseInt(id)).get();
    }
}
