/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.services;

import com.BTS.converter.entities.TrExtension;
import com.BTS.converter.repositories.TrExtensionRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class TrExtensionService {
    
    @Autowired
    TrExtensionRepository repository;
    
    public Iterable<TrExtension> getAll(){
        return repository.findAll();
    }
    
    public boolean save(TrExtension te){
        if (repository.save(te)!=null) {
            return true;
        } else {
            return false;
        }
    }
    public void delete(BigDecimal id){
        repository.deleteById(id);
    }
    
    public TrExtension getByclientId(int id){
        return repository.getByClientId(new BigDecimal(id)).get(id);
    }
}
