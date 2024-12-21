package oliveiradev.Cadastro_Jogadores.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import oliveiradev.Cadastro_Jogadores.entity.Jogador;
import oliveiradev.Cadastro_Jogadores.entity.enums.GrupoCodinome;
import oliveiradev.Cadastro_Jogadores.exception.CodinomesIndisponiveisException;
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
        return getViewAndModel(model, new Jogador(null, null, null, null, null));
    }    

    @PostMapping
    public String cadastroJogador(@ModelAttribute @Valid Jogador jogador, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            return getViewAndModel(model, jogador);
        }

        try {
            jogadorService.registrarJogador(jogador);
            return "redirect:/cadastro-jogador";
        } catch (CodinomesIndisponiveisException e) {
            result.rejectValue("grupoCodinome", "CodinomesIndisponiveisException", e.getMessage());
        }
    
        jogadorService.registrarJogador(jogador);
        return "redirect:/cadastro-jogador";
    }

    private String getViewAndModel(Model model, Jogador jogador) {
        model.addAttribute("jogador", jogador);
        model.addAttribute("grupoCodinomes", GrupoCodinome.values());
        return "cadastro_jogador";
    }
}
