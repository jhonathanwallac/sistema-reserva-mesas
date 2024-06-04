package com.engweb.sistema_reserva_mesas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RealizaReservaController {
    @GetMapping("/reservar")
    public String showReservaPage() {
        return "realiza-reserva";
    }
}
