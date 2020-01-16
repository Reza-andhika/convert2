/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.services;

import com.BTS.converter.entities.Temp;
import com.BTS.converter.repositories.tempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class tempService {
      @Autowired
      tempRepository repository;
    
    public Iterable<Temp> getAll(){
        return repository.findAll();
    }
    
    public boolean save(Temp temp){
        if (repository.save(temp)!=null) {
            return true;
        } else {
            return false;
        }
    }
    
    public Temp getById(String id){
        return repository.findById(Integer.parseInt(id)).get();
    }

}
