package com.engweb.sistema_reserva_mesas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsultaReservasUsuarioController {
    @GetMapping("/minhas-reservas")
    public String showMinhasReservasPage() {
        return "reservas-usuario";
    }
}
