/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.services;

import com.BTS.converter.entities.ClientPartner;
import com.BTS.converter.repositories.ClientPartnerRepository;
import com.BTS.converter.repositories.TrExtensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class ClientView {
    @Autowired
    ClientPartnerRepository clientRepository;
    
    @Autowired
    TrExtensionRepository extensionRepository;
    
    
}
