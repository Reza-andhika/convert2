/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.controllers;

import com.BTS.converter.entities.Extension;
import com.BTS.converter.repositories.ExtensionRepository;
import com.BTS.converter.services.ExtensionService;
import com.BTS.converter.tools.Methods;
import java.math.BigDecimal;
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
 * @author hp
 */
@Controller
public class ExtensionController {

    @Autowired
    ExtensionService extensionService;
    
    @Autowired
    ExtensionRepository extensionRepo;

    @Autowired
    Methods method;

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/extension")
    public String getAll(Model model, Extension extension) {
        model.addAttribute("extensions", extensionService.getAll());
        return "extension";
    }

    @PostMapping("/extenSave")
    public String save(@Valid Extension extension, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("status", "file failed to save");
        }
        String ext = request.getParameter("extensi");
        Extension exten= new Extension();
        if(extensionRepo.loopIdExtension()==null){
            exten.setId(new BigDecimal(1));
            exten.setExtension(ext);
            if (extensionService.save(exten)) {
                System.out.println("Berhasil simpan parameter");
            } else {
                System.out.println("Tidak berhasil simpan parameter");
            }
        }
        else{
            exten.setId(new BigDecimal(extensionRepo.loopIdExtension()));
            exten.setExtension(ext);
            if (extensionService.save(exten)) {
                System.out.println("Berhasil simpan parameter");
            } else {
                System.out.println("Tidak berhasil simpan parameter");
            }
        }
        redirectAttributes.addFlashAttribute("status", "file saved successfully");
        System.out.println("berhasil");
        return "redirect:/extension";
    }

    @GetMapping("/extenDelete")
    public String delete(int id, RedirectAttributes redirectAttributes) {
        try {
            extensionService.delete(id);
            redirectAttributes.addFlashAttribute("status", "file successfully deleted");
        } catch (Exception e) {
            e.getStackTrace();
            redirectAttributes.addFlashAttribute("status", "file failed to delete");
        }
        return "redirect:/extension";
    }
}
