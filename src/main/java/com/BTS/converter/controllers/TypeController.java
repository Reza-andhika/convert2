/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.controllers;

import com.BTS.converter.entities.DetailData;
import com.BTS.converter.entities.Parameter;
import com.BTS.converter.entities.CorporateType;
import com.BTS.converter.services.ParameterService;
import com.BTS.converter.services.TypeService;
import com.BTS.converter.tools.Methods;
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
public class TypeController {
    @Autowired
    TypeService typeService;
    
    @Autowired
    HttpServletRequest request;
    
    
    @RequestMapping("/type")
    public String getAll(Model model, CorporateType type) {
        model.addAttribute("types", typeService.getAll());
        return "type";
    }

    @PostMapping("/typeSave")
    public String save(@Valid CorporateType type, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
//            model.addAttribute("types", typeService.getAll());
            redirectAttributes.addFlashAttribute("status", "file failed to save");
        }
        Methods methods = new Methods();
        String name = request.getParameter("name");
        
        type.setId(methods.id_for_type(name));
        typeService.save(type);
        redirectAttributes.addFlashAttribute("status", "file saved successfully");
//        model.addAttribute("types", typeService.getAll());
        
        return "redirect:/type";
    }
    
    @GetMapping("/typeDelete")
    public String delete(String id, RedirectAttributes redirectAttributes){
        try{
            typeService.delete(id);
            redirectAttributes.addFlashAttribute("status", "file successfully deleted");
        }catch(Exception e){
            e.getStackTrace();
            redirectAttributes.addFlashAttribute("status", "file failed to delete");
        }
        return "redirect:/type";
    }
}
