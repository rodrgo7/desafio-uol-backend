package oliveiradev.Cadastro_Jogadores.services;

import java.util.List;

import org.springframework.stereotype.Service;

import oliveiradev.Cadastro_Jogadores.entity.CodinomeRepositoryFactory;
import oliveiradev.Cadastro_Jogadores.entity.enums.GrupoCodinome;
import oliveiradev.Cadastro_Jogadores.exception.CodinomesIndisponiveisException;

@Service
public class CodinomeService {
    private final CodinomeRepositoryFactory codinomeRepositoryFactory;

    public CodinomeService(CodinomeRepositoryFactory codinomeRepositoryFactory) {
        this.codinomeRepositoryFactory = codinomeRepositoryFactory;
    }

    public String gerarCodinome (GrupoCodinome grupoCodinome, List<String> codinomeEmUso) throws Exception {
    var codinomeDisponiveis = listarCodinomesDisponiveis(grupoCodinome, codinomeEmUso);
        if (codinomeDisponiveis.isEmpty())
            throw new CodinomesIndisponiveisException();
        
        var codinomeSorteado = sortearCodinome(codinomeDisponiveis);
            
        return codinomeSorteado;
    }
                
    private String sortearCodinome(List<String> codinomeDisponiveis) {
        return codinomeDisponiveis.get((int) (Math.random() * codinomeDisponiveis.size()));
    }
        
    private List<String> listarCodinomesDisponiveis(GrupoCodinome grupoCodinome, List<String> codinomeEmUso) throws Exception {
    var codinomes = buscarCodinomes(grupoCodinome);
        
    var codinomeDisponiveis = codinomes
        .stream()
        .filter(codinome -> !codinomeEmUso.contains(codinome))
        .toList();
       return codinomeDisponiveis;
    }
        
    private List<String> buscarCodinomes(GrupoCodinome grupoCodinome) throws Exception {
        var codinomeRepository = codinomeRepositoryFactory.create(grupoCodinome);

        return codinomeRepository.buscarCodinomes().getCodinomes();
    }
}
