package oliveiradev.Cadastro_Jogadores.entity;

import oliveiradev.Cadastro_Jogadores.entity.enums.GrupoCodinome;

public record Jogador(
    String nome,
    String email,
    String telefone,
    String codinome,
    GrupoCodinome grupoCodinome) { }
