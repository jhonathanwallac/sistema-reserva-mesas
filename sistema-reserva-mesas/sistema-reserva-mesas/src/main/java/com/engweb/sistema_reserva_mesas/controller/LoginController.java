package com.engweb.sistema_reserva_mesas.controller;

import com.engweb.sistema_reserva_mesas.model.Usuario;
import com.engweb.sistema_reserva_mesas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/escolha-usuario")
    public String showEscolhaUsuarioPage() {
        return "escolha-usuario";  // Nome do arquivo HTML da página de escolha do usuário
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha, Model model, RedirectAttributes redirectAttributes) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (usuario.getSenha().equals(senha)) {
                return "redirect:/escolha-usuario"; // Redireciona para a página de sucesso
            } else {
                redirectAttributes.addFlashAttribute("senhaIncorreta", true); // Adiciona atributo para o pop-up de senha incorreta
            }
        } else {
            redirectAttributes.addFlashAttribute("emailNaoCadastrado", true); // Adiciona atributo para o pop-up de e-mail não cadastrado
        }

        return "redirect:/login"; // Redireciona de volta para a página de login
    }

}
