package oliveiradev.Cadastro_Jogadores.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import oliveiradev.Cadastro_Jogadores.entity.Jogador;
import oliveiradev.Cadastro_Jogadores.entity.enums.GrupoCodinome;
import oliveiradev.Cadastro_Jogadores.services.JogadorService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("cadastro-jogador")
public class CadastroJogadorController {
    private final JogadorService jogadorService;

    public CadastroJogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public String PaginaCadastroJogador(Model model) {
        model.addAttribute("jogador", new Jogador(null, null, null, null, null));
        model.addAttribute("grupoCodinomes", GrupoCodinome.values());
        return "cadastro_jogador";
    }    

    @PostMapping
    public String cadastroJogador(@ModelAttribute Jogador jogador) {
        try {
            jogadorService.registrarJogador(jogador);

            return "redirect:/cadastro-jogador";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/cadastro-jogador";
        }
    }
}
