package oliveiradev.Cadastro_Jogadores.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import oliveiradev.Cadastro_Jogadores.services.JogadorService;

@Controller
@RequestMapping("listagem-jogadores")
public class ListagemJogadoresController {
    private final JogadorService jogadorService;

    public ListagemJogadoresController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public String listagemJogadores(Model model) {
        model.addAttribute("jogadores", jogadorService.listarJogadores());

        return "listagem_jogadores";
    }
}
