/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.services;

import com.BTS.converter.entities.Parameter;
import com.BTS.converter.entities.CorporateType;
import com.BTS.converter.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Reza
 */
@Service
public class TypeService {
    @Autowired
    TypeRepository typeRepo;
    
    public Iterable<CorporateType> getAll(){
        return typeRepo.findAll();
    }
    
    public boolean save(CorporateType tp){
        if (typeRepo.save(tp)!=null) {
            return true;
        } else {
            return false;
        }
    }
    public void delete(String id){
        typeRepo.deleteById(id);
    }
    
    public CorporateType getById(String id){
        return typeRepo.findById(id).get();
    }
}
