package oliveiradev.Cadastro_Jogadores.entity;

import org.springframework.stereotype.Component;

import oliveiradev.Cadastro_Jogadores.entity.enums.GrupoCodinome;
import oliveiradev.Cadastro_Jogadores.repository.CodinomeRepository;
import oliveiradev.Cadastro_Jogadores.repository.LigaDaJusticaRepository;
import oliveiradev.Cadastro_Jogadores.repository.VingadoresRepository;

@Component
public class CodinomeRepositoryFactory {
    private final LigaDaJusticaRepository ligaDaJusticaRepository;
    private final VingadoresRepository vingadoresRepository;

    public CodinomeRepositoryFactory(LigaDaJusticaRepository ligaDaJusticaRepository, VingadoresRepository vingadoresRepository) {
        this.ligaDaJusticaRepository = ligaDaJusticaRepository;
        this.vingadoresRepository = vingadoresRepository;
    }

    public CodinomeRepository create(GrupoCodinome grupoCodinome) {
        return switch (grupoCodinome) {
            case LIGA_DA_JUSTICA -> ligaDaJusticaRepository;
            case VINGADORES -> vingadoresRepository;
        };
    }
}