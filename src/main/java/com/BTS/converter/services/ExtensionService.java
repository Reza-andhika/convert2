/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.services;

import com.BTS.converter.entities.CorporateType;
import com.BTS.converter.entities.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BTS.converter.repositories.ExtensionRepository;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author Reza
 */
@Service
public class ExtensionService {
    @Autowired
    ExtensionRepository extRepo;
    
    public Iterable<Extension> getAll(){
        return extRepo.findAll();
    }
    
    public boolean save(Extension data) {
        if (extRepo.save(data) != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public void delete(int id){
       extRepo.deleteById(id);
    }
    
}
