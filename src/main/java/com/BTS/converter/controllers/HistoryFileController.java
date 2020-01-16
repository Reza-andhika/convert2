/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.controllers;

import com.BTS.converter.entities.ClientPartner;
import com.BTS.converter.entities.DetailData;
import com.BTS.converter.entities.HistoryFile;
import com.BTS.converter.services.ClientPartnerService;
import com.BTS.converter.services.HistoryFileService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Reza
 */
@Controller
public class HistoryFileController {

    @Autowired
    HistoryFileService historyService;

    @Autowired
    ClientPartnerService clientService;

    @RequestMapping("/history")
    public String getAll(Model model, HistoryFile historyFile) {
        model.addAttribute("hisories", historyService.getAll());
        model.addAttribute("clients", clientService.getAll());
        return "###";
    }

    @PostMapping("/historySave")
    public String save(Model model, @Valid HistoryFile historyFile, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("histories", historyService.getAll());
            model.addAttribute("clients", clientService.getAll());

//        model.addAttribute(model);
        }

        model.addAttribute("histories", historyService.getAll());
        model.addAttribute("clients", clientService.getAll());

        historyService.save(historyFile);

        return "redirect:/history";
    }
}
