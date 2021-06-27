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
