/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.controllers;

import com.BTS.converter.entities.ClientPartner;
import com.BTS.converter.entities.DetailData;
import com.BTS.converter.entities.Temp;
import com.BTS.converter.entities.TrExtension;
import com.BTS.converter.repositories.ClientPartnerRepository;
import com.BTS.converter.repositories.DetailDataRepository;
import com.BTS.converter.repositories.HistoryFileRepository;
import com.BTS.converter.repositories.TrExtensionRepository;
import com.BTS.converter.repositories.tempRepository;
import com.BTS.converter.services.ClientPartnerService;
import com.BTS.converter.services.DetailDataService;
import com.BTS.converter.services.ExtensionService;
import com.BTS.converter.services.HistoryFileService;
import com.BTS.converter.services.ParameterService;
import com.BTS.converter.services.TypeService;
import com.BTS.converter.services.tempService;
import com.BTS.converter.tools.Methods;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Reza
 */
@Controller
public class ConverterController {

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
    tempRepository tempRepository;

    @Autowired
    tempService tempService;

    @Autowired
    TrExtensionRepository extensionRepository;

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("historys", historyService.getAll());
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("params", paramService.getAll());
        model.addAttribute("ext", extService.getAll());
        return "convert";
    }

    @GetMapping("/convert")
    public String convert(Model model, RedirectAttributes redirectAttributes) throws IOException {
        String clientId = request.getParameter("client");
        String delim_data = request.getParameter("delim_1");
        ///getById
        ClientPartner client = (ClientPartner) clientService.getById(clientId);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat times = new SimpleDateFormat("HH-mm-ss");
        String format_date = sdf.format(date);
        String format_time = times.format(date);

        for (ClientPartner client1 : clientRepo.getByExt(client.getClientId())) {
            for (TrExtension extension : extensionRepository.getByClientId(client1.getId())) {
                method.insert_into(client1.getIncomingPath() + "\\", client1.getParameter().getSymbol(),
                        extension.getExtension().getExtension(), client1.getId().toString());
            }
        }
//            convert
        for (BigDecimal clientTemp : tempRepository.clientId()) {
            for (String tempGetFileName : tempRepository.FileName(clientTemp)) {
                System.out.println(tempGetFileName);
                if (historyRepo.getByOldFilename(tempGetFileName) != null) {
                    System.out.println("file sudah di convert");
                    redirectAttributes.addFlashAttribute("status", "file has been converted");
                } else {
                    String filename = client.getType().getName() + "_" + client.getName() + "_" + tempGetFileName + "_" + format_date + "_" + format_time;
                    if (method.converting(client.getOutgoingPath(), tempGetFileName + "_" + format_date + ".txt", "" + client.getId(),
                            client.getParameter2(), ";", tempGetFileName).equalsIgnoreCase("convert ok")) {
                        historyService.save(method.saveHistory(client.getIncomingPath(),
                                client.getOutgoingPath() + "\\" + client.getName(), tempGetFileName, filename + ".txt", "" + client.getId(), date));
                       
                        method.saveDataDetail(tempGetFileName, client.getId());
                    }
                    System.out.println(tempGetFileName);
                    System.out.println("data berhasil di convert");
                }
                redirectAttributes.addFlashAttribute("status", "file successfully converted");
            }
        }
        tempRepository.deleteTemp();
        System.out.println("temp berhasil di hapus");

        return "redirect:/home";
    }

    @GetMapping("/historyDelete")
    public String delete(RedirectAttributes redirectAttributes) {
        try {
            historyService.deleteAll();
            redirectAttributes.addFlashAttribute("status", "file successfully deleted");
        } catch (Exception e) {
            e.getStackTrace();
            redirectAttributes.addFlashAttribute("status", "file failed to delete");
        }
        return "redirect:/parameter";
    }
}
