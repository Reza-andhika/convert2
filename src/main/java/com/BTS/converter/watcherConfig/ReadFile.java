/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.watcherConfig;

import com.BTS.converter.entities.ClientPartner;
import com.BTS.converter.repositories.ClientPartnerRepository;
import com.BTS.converter.repositories.ClientPartnerRepository;
import com.BTS.converter.repositories.TypeRepository;
import com.BTS.converter.repositories.TypeRepository;
import com.BTS.converter.services.ClientPartnerService;
import com.BTS.converter.tools.Methods;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;

/**
 *
 * @author hp
 */
//@Controller
public class ReadFile {

    @Autowired
    ClientPartnerRepository partnerRepository;

    @Autowired
    ClientPartnerService partnerService;

    @Autowired
    TypeRepository typeRepository;

//    @Autowired
    public void reads() throws IOException {
//        ClientPartner client = (ClientPartner) partnerService.getAll();
//        for (ClientPartner clientPartner : partnerService.getAll()) {
        Path dir = Paths.get("D:\\lern\\to");
        WatcherFile files = new WatcherFile(dir);
        files.processEvents();
    }   
}