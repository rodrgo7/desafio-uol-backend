package oliveiradev.Cadastro_Jogadores.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import oliveiradev.Cadastro_Jogadores.entity.enums.GrupoCodinome;
import oliveiradev.Cadastro_Jogadores.web.CodinomeDTO;
import oliveiradev.Cadastro_Jogadores.web.VingadoresDTO;

@Repository
public class VingadoresRepository implements CodinomeRepository {

    @Override
    public CodinomeDTO buscarCodinomes() throws Exception {
        var codinomes = RestClient
            .builder()
            .baseUrl(GrupoCodinome.VINGADORES.getUri())
            .build()
            .get()
            .retrieve()
            .body(String.class);
        var objectMapper = new ObjectMapper();
        var vingadores = objectMapper.readValue(codinomes, VingadoresDTO.class);

        return vingadores;
    }
}
