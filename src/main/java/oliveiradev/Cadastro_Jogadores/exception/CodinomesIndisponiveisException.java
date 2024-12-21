package oliveiradev.Cadastro_Jogadores.exception;

public class CodinomesIndisponiveisException extends IllegalArgumentException {
    public CodinomesIndisponiveisException() {
        super("Não há codinomes disponíveis no momento, selecione outro grupo.");
    }

}
