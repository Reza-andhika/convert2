/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.services;

import com.BTS.converter.entities.ClientPartner;
import com.BTS.converter.entities.DetailData;
import com.BTS.converter.entities.Temp;
import com.BTS.converter.entities.TrExtension;
import com.BTS.converter.repositories.ClientPartnerRepository;
import com.BTS.converter.repositories.DetailDataRepository;
import com.BTS.converter.repositories.HistoryFileRepository;
import com.BTS.converter.repositories.TrExtensionRepository;
import com.BTS.converter.repositories.tempRepository;
import com.BTS.converter.tools.Methods;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hp
 */
@Component
@Transactional
@Service
public class ScheduledTask {

    private static final DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    HistoryFileService historyService;

    @Autowired
    HistoryFileRepository historyRepo;

    @Autowired
    ClientPartnerRepository clientRepo;

    @Autowired
    TypeService typeService;

    @Autowired
    ParameterService paramService;

    @Autowired
    ClientPartnerService clientService;

    @Autowired
    DetailDataService dataService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ExtensionService extService;

    @Autowired
    Methods method;

    @Autowired
    DetailDataRepository dataRepository;

    @Autowired
    tempService tempService;

    @Autowired
    tempRepository tempRepository;

    @Autowired
    TrExtensionService extensionService;

    @Autowired
    TrExtensionRepository extensionRepository;

    public static Logger logger = LogManager.getLogger(ScheduledTask.class.getName());

    @Scheduled(fixedRate = 30000, initialDelay = 2000)
    public void saves() throws IOException, InterruptedException {
        logger.info("fix rate task execute time  - {} ", time.format(LocalDateTime.now()));
        for (ClientPartner clients : clientService.getAll()) {
            ClientPartner client = (ClientPartner) clientService.getByIncoming(clients.getIncomingPath());
            for (ClientPartner client1 : clientRepo.getByExt(client.getClientId())) {
                for (TrExtension extension : extensionRepository.getByClientId(client1.getId())) {
                    method.insert_into(client1.getIncomingPath(), client1.getParameter().getSymbol(),
                            extension.getExtension().getExtension(), client1.getId().toString());
                }
            }

            logger.info("from saves -> berhasil simpan");
        }
        for (BigDecimal temp : tempRepository.clientId()) {
            logger.info("client id from saves-> " + temp);
            if (temp != null) {
                converts(temp);
            }
        }
    }

//    @Scheduled(fixedRate = 40000, initialDelay = 5000)
    public void converts(BigDecimal clientTemp) throws InterruptedException, IOException {

        logger.info("client id -> " + clientTemp);
        ClientPartner client = (ClientPartner) clientService.getById(clientTemp.toString());
//            System.out.println(client.getIncomingPath());
        System.out.println("-----------------------------------------");
        for (String tempGetFileName : tempRepository.FileName(clientTemp)) {
            logger.info("from convert -> " + tempGetFileName);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat times = new SimpleDateFormat("HH-mm-ss");
            String format_date = sdf.format(date);
            String format_time = times.format(date);
            String filename = client.getType().getName() + "_" + client.getName() + "_" + format_date + ";" + format_time;

            if (historyRepo.getByOldFilename(tempGetFileName) != null) {
                System.out.println("file sudah di convert");
            } else {
                method.converting(client.getOutgoingPath(), tempGetFileName + "_" + format_date + ".txt", "" + client.getId(),
                        client.getParameter2(), ";", tempGetFileName).equalsIgnoreCase("File is created");
//                historyService.save(method.saveHistory(client.getIncomingPath(),
//                        client.getOutgoingPath() + "\\" + client.getName(), tempGetFileName, filename + ".txt", "" + client.getId(), date));
                method.saveDataDetail(tempGetFileName, client.getId());
                System.out.println(tempGetFileName);
                logger.info("from convert -> data berhasil di convert");

            }

        }
        tempRepository.deleteTemp();
        logger.info("from convert -> temp berhasil di hapus");
    }
}
