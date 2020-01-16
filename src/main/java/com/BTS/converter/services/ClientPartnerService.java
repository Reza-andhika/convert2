/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.services;

import com.BTS.converter.entities.ClientPartner;
import com.BTS.converter.repositories.ClientPartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Reza
 */
@Service
public class ClientPartnerService {
    @Autowired
    ClientPartnerRepository clientRepo;
    
    public Iterable<ClientPartner> getAll(){
        return clientRepo.findAll();
    }
    
    public boolean save(ClientPartner client){
        if (clientRepo.save(client)!=null) {
            return true;
        } else {
            return false;
        }
    }
    public  void delete(@RequestParam("id")int id){
        clientRepo.deleteById(id);
    }
    
    public ClientPartner getById(String id){
        return clientRepo.getById(id);
    }
    
    public ClientPartner getByIncoming(String path){
        return clientRepo.getByIncoming(path);
    }
}
