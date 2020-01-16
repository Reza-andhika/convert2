/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.BTS.converter.entities.DetailData;
import com.BTS.converter.services.DetailDataService;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Reza
 */
@Controller
public class DetailDataController {

    @Autowired
    DetailDataService detailService;

    @RequestMapping("/detaildata")
    public String getAll(Model model, DetailData detaildata) {
        model.addAttribute("details", detailService.getAll());

        return "###";
    }

    @PostMapping("/detailSave")
    public String save(Model model, @Valid DetailData detaildata, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("details", detailService.getAll());
        }

        model.addAttribute("details", detailService.getAll());
        
        detailService.save(detaildata);
        return "redirect:/detaildata";
    }
}
