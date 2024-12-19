package oliveiradev.Cadastro_Jogadores.repository;

import oliveiradev.Cadastro_Jogadores.web.CodinomeDTO;

public interface CodinomeRepository {
    CodinomeDTO buscarCodinomes() throws Exception;
}
