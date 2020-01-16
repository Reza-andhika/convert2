/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.controllers;

//import com.BTS.converter.entities.DetailData;
import com.BTS.converter.entities.Parameter;
import com.BTS.converter.repositories.ParameterRepository;
import com.BTS.converter.services.ParameterService;
import com.BTS.converter.tools.Methods;
import java.math.BigDecimal;
import java.math.BigInteger;
//import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
//import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
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
public class ParameterController {

    @Autowired
    ParameterService paramService;
    
    @Autowired
    ParameterRepository paramRepo;
        
    @Autowired
    HttpServletRequest request;

    Methods methods = new Methods();


    @RequestMapping("/parameter")
    public String getAll(Model model, Parameter parameter) {
        model.addAttribute("parameters", paramService.getAll());
        return "parameter";
    }

    @PostMapping("/paramSave")
    public String save(@Valid Parameter parameter, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
           redirectAttributes.addFlashAttribute("status", "file failed to save");
        }
        String symbol = request.getParameter("symbol");
        Parameter param = new Parameter();
        if(paramRepo.loopIdParameter()==null){
            param.setId(new BigDecimal(1));
            param.setSymbol(symbol);
            if (paramService.save(param)) {
                System.out.println("Berhasil simpan parameter");
            } else {
                System.out.println("Tidak berhasil simpan parameter");
            }
        }
        else{
            param.setId(new BigDecimal(paramRepo.loopIdParameter()));
            param.setSymbol(symbol);
            if (paramService.save(param)) {
                System.out.println("Berhasil simpan parameter");
            } else {
                System.out.println("Tidak berhasil simpan parameter");
            }
        }
        redirectAttributes.addFlashAttribute("status", "file saved successfully");
        return "redirect:/parameter";
    }
    
    @GetMapping("/paramDelete")
    public String delete(int id,RedirectAttributes redirectAttributes){
        try {
            paramService.delete(id);
            redirectAttributes.addFlashAttribute("status", "file successfully deleted");
        } catch (Exception e) {
            e.getStackTrace();
            redirectAttributes.addFlashAttribute("status", "file failed to delete");
        }
        return "redirect:/parameter";
    }
}
