package com.gym.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.gym.domain.Ticket;
import com.gym.gym.service.TicketService;

import groovy.util.logging.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@Slf4j
@RequestMapping("/admin/ticket")
public class TicketController {
    
    @Autowired private TicketService ticketService;

    @GetMapping("/list")
    public String list(Model model) throws Exception {

        List<Ticket> ticketList = ticketService.allList();
        
        model.addAttribute("ticketList", ticketList);

        return "/admin/ticket/list";
    }
    
    
    @GetMapping("/insert")
    public String insert() {
        return "/admin/ticket/insert";
    }

    @PostMapping("/insert")
    public String insert(Ticket ticket) throws Exception {
        
        int result = ticketService.insert(ticket);

        if ( result == 0 ) {
            return "/admin/ticket/insert/?error";
        }
        
        return "/admin/ticket/list";
    }
    
    @GetMapping("/update")
    public String update() {
        return "/admin/ticket/update";
    }
    
    @PostMapping("/update")
    public String update(Ticket ticket) throws Exception {
        
        int result = ticketService.update(ticket);
    
        if ( result == 0 ) {
            return "/admin/ticket/update/?error";
        }

        return "/admin/ticket/list";
    }
    
    @GetMapping("/delete")
    public String delete(@RequestParam("no") int no) throws Exception {

        int result = ticketService.delete(no);
    
        if ( result == 0 ) {
            return "/admin/ticket/delete/?error";
        }

        return "/admin/ticket/list";
    }
    
    
    
    

}
