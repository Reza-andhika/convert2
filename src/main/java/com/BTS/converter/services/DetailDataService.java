/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.services;

import com.BTS.converter.entities.ClientPartner;
import com.BTS.converter.entities.DetailData;
import com.BTS.converter.repositories.DetailDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Reza
 */
@Service
public class DetailDataService {
    
    @Autowired
    DetailDataRepository detailRepo;
    
    public Iterable<DetailData> getAll(){
        return detailRepo.findAll();
    }
    
    public boolean save(DetailData detail){
        if (detailRepo.save(detail)!=null) {
            return true;
        } else {
            return false;
        }
    }
    
    public DetailData getById(String id){
        return detailRepo.findById(Integer.parseInt(id)).get();
    }

    
}
