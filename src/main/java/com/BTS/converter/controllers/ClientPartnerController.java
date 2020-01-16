/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.controllers;

import com.BTS.converter.entities.ClientPartner;
import com.BTS.converter.entities.Parameter;
import com.BTS.converter.entities.CorporateType;
import com.BTS.converter.entities.Extension;
import com.BTS.converter.entities.TrExtension;
import com.BTS.converter.repositories.ClientPartnerRepository;
import com.BTS.converter.services.ClientPartnerService;
import com.BTS.converter.services.ExtensionService;
import com.BTS.converter.services.TypeService;
import com.BTS.converter.services.ParameterService;
import com.BTS.converter.services.TrExtensionService;
import com.BTS.converter.tools.Methods;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ClientPartnerController {

    @Autowired
    ClientPartnerService clientService;

    @Autowired
    TrExtensionService extensionService;

    @Autowired
    ClientPartnerRepository clientRepository;

    @Autowired
    TypeService typeService;

    @Autowired
    ParameterService paramService;

    @Autowired
    ExtensionService extService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    Methods methods;

    @RequestMapping("/clientPartner")
    public String getAll(Model model, ClientPartner partner) {
        model.addAttribute("extensions", extensionService.getAll());
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("types", typeService.getAll());
        model.addAttribute("params", paramService.getAll());
        model.addAttribute("ext", extService.getAll());
        return "client";
    }

//    @GetMapping("/extension")
//    public String getById(Model model, String id) {
//        model.addAttribute("extensions", extensionService.getByclientId(Integer.parseInt(id)));
//        return "redirect:/clientPartner";
//    }

    @GetMapping("/clientSave")
    public String save(Model model, RedirectAttributes redirectAttributes) throws IOException {

        String name = request.getParameter("name");
        String incoming = request.getParameter("incomingPath");
        String outgoing = request.getParameter("outgoingPath");
        String simbol = request.getParameter("symbol");
        String simbol2 = request.getParameter("symbol2");
        String tipe = request.getParameter("type");
        String[] extensi = request.getParameterValues("states[]");
        System.out.println(name);
        System.out.println(incoming);
        if (clientService.getByIncoming(incoming) != null) {

            redirectAttributes.addFlashAttribute("status", "path have been used");
        } else {
            if (methods.saveClient(tipe + '_' + methods.id_for_client(name), name, incoming, outgoing, simbol, simbol2, tipe).equalsIgnoreCase("success")) {
                ClientPartner clientPartner = clientService.getByIncoming(incoming);
                for (String str : extensi) {
                    methods.saveTransactionExtension(clientPartner.getId(), Integer.parseInt(str));
                }

                redirectAttributes.addFlashAttribute("status", "file saved successfully");
            } else {
                redirectAttributes.addFlashAttribute("status", "file failed to save");
            }

        }

        return "redirect:/clientPartner";
    }

    @GetMapping("/clientDelete")
    public String delete(String id, RedirectAttributes redirectAttributes) {
        try {
            clientService.delete(Integer.parseInt(id));
            redirectAttributes.addFlashAttribute("status", "file successfully deleted");
        } catch (Exception e) {
            e.getStackTrace();
            redirectAttributes.addFlashAttribute("status", "file filed to deleted");
        }
        return "redirect:/clientPartner";
    }

    @GetMapping("/extensi")
    public String getByCientId(String id, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("trExtension", extensionService.getByclientId(Integer.parseInt(id)));
        return "redirect:/clientPartner";
    }
}
