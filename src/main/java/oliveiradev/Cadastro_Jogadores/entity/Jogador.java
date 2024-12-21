package oliveiradev.Cadastro_Jogadores.entity;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import oliveiradev.Cadastro_Jogadores.entity.enums.GrupoCodinome;

@Validated
public record Jogador(
    @NotBlank 
    String nome,
    @NotBlank 
    String email,
    String telefone,
    String codinome,
    @NotNull
    GrupoCodinome grupoCodinome) { }
