package scanner.compiler.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum ErrorMessage {

    // Léxico
    INVALID_TOKEN("ERRO LÉXICO:\n\tToken Não reconhecido: \"{TOKEN}\" na linha {ROW} e coluna {COL}."),

    // Sintático
    MISSING("ERRO SINTÁTICO:\n\tEsperava encontrar {TYPES} na linha {ROW} e coluna {COL}.\n\tEncontrado {TOKEN} no lugar."),

    // Semântico
    VARIABLE_NOT_DECLARED("ERRO SEMÂNTICO:\n\tIdentificador '{TOKEN}' não declarado."),
    VARIABLE_ALREADY_DECLARED("ERRO SEMÂNTICO:\n\tIdentificador '{TOKEN}' já declarado."),
    CONSTANT_OR_NOT_INDEXABLE("ERRO SEMÂNTICO:\n\tIdentificador '{TOKEN}' é constante ou não indenxável."),
    VARIABLE_NEEDS_INDEX("ERRO SEMÂNTICO:\n\tIdentificador de variável indexada exige índice")

    ;

    @Getter
    private final String text;

    public String applyValues(String token, int row, int col, ArrayList<TokenType> types){
        String returnText = text.replace("{TOKEN}", token)
                                .replace("{ROW}", String.valueOf(row))
                                .replace("{COL}", String.valueOf(col));

        if(types != null){
            returnText = returnText.replace("{TYPES}", types.stream().map(TokenType::getText)
                                                                           .collect(Collectors.joining(" ou ")));
        }

        return returnText;
    }
}
