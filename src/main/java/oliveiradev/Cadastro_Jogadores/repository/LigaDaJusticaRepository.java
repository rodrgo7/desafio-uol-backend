package oliveiradev.Cadastro_Jogadores.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import oliveiradev.Cadastro_Jogadores.entity.enums.GrupoCodinome;
import oliveiradev.Cadastro_Jogadores.web.CodinomeDTO;
import oliveiradev.Cadastro_Jogadores.web.LigaDaJusticaDTO;

@Repository
public class LigaDaJusticaRepository implements CodinomeRepository {

    @Override
    public CodinomeDTO buscarCodinomes() throws Exception {
        var codinomes = RestClient.builder()
            .baseUrl(GrupoCodinome.LIGA_DA_JUSTICA.getUri())
            .build()
            .get()
            .retrieve()
            .body(String.class);
        
        var xmlMapper = new XmlMapper();
        var ligaDaJustica = xmlMapper.readValue(codinomes, LigaDaJusticaDTO.class);

        return ligaDaJustica;
    }
}
