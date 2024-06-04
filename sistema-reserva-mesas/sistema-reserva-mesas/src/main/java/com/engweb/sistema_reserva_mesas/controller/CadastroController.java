package com.engweb.sistema_reserva_mesas.controller;

import com.engweb.sistema_reserva_mesas.model.Usuario;
import com.engweb.sistema_reserva_mesas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

@Controller
public class CadastroController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String nome,
                               @RequestParam String cpf,
                               @RequestParam String email,
                               @RequestParam String senha,
                               RedirectAttributes redirectAttributes) {

        // Verifica se já existe um usuário com o mesmo CPF
        if (usuarioRepository.findByCpf(cpf).isPresent()) {
            redirectAttributes.addFlashAttribute("cpfExistente", true);
            return "redirect:/register";
        }

        // Verifica se já existe um usuário com o mesmo e-mail
        if (usuarioRepository.findByEmail(email).isPresent()) {
            redirectAttributes.addFlashAttribute("emailExistente", true);
            return "redirect:/register";
        }

        // Se as validações passarem, cria e salva o usuário
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuarioRepository.save(usuario);

        redirectAttributes.addFlashAttribute("successMessage", "Cadastro realizado com sucesso!");
        return "redirect:/register";
    }
}