package oliveiradev.Cadastro_Jogadores;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import oliveiradev.Cadastro_Jogadores.entity.Jogador;
import oliveiradev.Cadastro_Jogadores.entity.enums.GrupoCodinome;

@SpringBootTest
@AutoConfigureMockMvc
class CadastroJogadoresApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void cadastrarListarJogadoresSucesso() throws Exception {

	var jogador = new Jogador("test", "teste@teste.com", "123456", null, GrupoCodinome.VINGADORES);

	mockMvc.perform(post("/cadastro-jogador")
		.param("nome", jogador.nome())
		.param("email", jogador.email())
		.param("telefone", jogador.telefone())
		.param("grupoCodinome", jogador.grupoCodinome().name()))
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/cadastro-jogador"));

		mockMvc
			.perform(get( "/listagem_jogadores"))
			.andDo(print())
			.andExpect(status().isNotFound())
			.andExpect(view().name("listagem_jogadores"))
			.andExpect(model().attribute("jogadores", hasSize(1)))
			.andExpect(model().attribute("jogadores", contains(allOf(
				hasProperty("nome", is(jogador.nome())),
				hasProperty("email", is(jogador.email())),
				hasProperty("telefone", is(jogador.telefone())),
				hasProperty("grupoCodinome", is(jogador.grupoCodinome()))
			))));	
	}
}
