package oliveiradev.Cadastro_Jogadores.services;

import java.util.List;

import org.springframework.stereotype.Service;

import oliveiradev.Cadastro_Jogadores.entity.Jogador;
import oliveiradev.Cadastro_Jogadores.entity.enums.GrupoCodinome;
import oliveiradev.Cadastro_Jogadores.repository.JogadorRepository;

@Service
public class JogadorService {
    private final JogadorRepository jogadorRepository;
    private final CodinomeService codinomeService;

    public JogadorService (JogadorRepository jogadorRepository, CodinomeService codinomeService) {
        this.jogadorRepository = jogadorRepository;
        this.codinomeService = codinomeService;
    }

    public Jogador registrarJogador(Jogador jogador) throws Exception {
        var codinomesEmUso = listarCodinomesEmUso(jogador.grupoCodinome());
        var novoCodinome = codinomeService.gerarCodinome(jogador.grupoCodinome(), codinomesEmUso);

        var novoJogador = new Jogador(jogador.nome(), jogador.email(), jogador.telefone(), novoCodinome,  jogador.grupoCodinome());

        return jogadorRepository.salvar(novoJogador);
    }

    public List<Jogador> listarJogadores() {
        return jogadorRepository.listaJogadores();
    }

    private List<String> listarCodinomesEmUso(GrupoCodinome grupoCodinome) {
        return jogadorRepository.listarCodinomesPorGrupo(grupoCodinome);
    }
}