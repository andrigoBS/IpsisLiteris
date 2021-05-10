package scanner.compiler.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import scanner.compiler.build.IpsisLiterisConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum TokenType {
    // Palavras reservadas
    DEF("program"),
    DATA_DEF("define"),
    IS("is"),
    EXE("execute"),
    VAR("variable"),
    NOT_VAR("not"),
    SET("set"),
    TO("to"),
    GET("get"),
    PUT("put"),
    NAT("natural"),
    REAL("real"),
    CHAR("char"),
    BOOL("boolean"),
    TRUE("true"),
    FALSE("false"),
    WHILE("while"),
    LOOP("loop"),
    DO("do"),
    IF("verify"),
    // Símbolos
    // Agregadores
    OPEN_CURLY("{"),
    CLOSE_CURLY("}"),
    OPEN_PARENT("("),
    CLOSE_PARENT(")"),
    OPEN_SQUARE("["),
    CLOSE_SQUARE("]"),
    // Comparadores
    EQUAL("=="),
    N_EQUAL("!="),
    GREATER(">"),
    LOWER("<"),
    LOW_EQ(">="),
    GREAT_EQ("<="),
    // Operadores Aritméticos
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVIDE("/"),
    POWER("**"),
    INT_DIVIDE("%"),
    MOD("%%"),
    // Operadores Lógicos
    AND("&"),
    OR("|"),
    NOT("!"),
    // Simbolos especiais
    DELIMITER("."),
    SEPARATOR(","),
    HEADER_DEF(":-"),
    IDENTIFIER("identificador"),
    INTEGER("inteiro"),
    FLOAT("real"),
    LITERAL("literal"),
    INVALID_TOKEN("Token Inválido"),
    EOF("Final do arquivo");

    public static TokenType parseToken(String token) {
        token = token.replace("<", "").replace(">", "");
        return TokenType.valueOf(token);
    }

    public static ArrayList<String> getIDERegexList(){
        // Palavras reservadas 1
        ArrayList<TokenType> words = new ArrayList<>(List.of(DEF, DATA_DEF, IS, EXE, VAR, NOT_VAR, SET, TO, GET, PUT));
        // Palavras reservadas 2
        ArrayList<TokenType> words2 = new ArrayList<>(List.of(NAT, REAL, CHAR, BOOL, TRUE, FALSE, WHILE, LOOP, DO, IF));
        // Agregadores
        ArrayList<TokenType> agr = new ArrayList<>(List.of(OPEN_CURLY, CLOSE_CURLY, OPEN_PARENT, CLOSE_PARENT, OPEN_SQUARE, CLOSE_SQUARE));
        // Comparadores
        ArrayList<TokenType> com = new ArrayList<>(List.of(EQUAL, N_EQUAL, GREATER, LOWER, LOW_EQ, GREAT_EQ));
        // Operadores Aritméticos
        ArrayList<TokenType> ari = new ArrayList<>(List.of(PLUS, MINUS, TIMES, DIVIDE, POWER, INT_DIVIDE, MOD));
        // Operadores Lógicos
        ArrayList<TokenType> log = new ArrayList<>(List.of(AND, OR, NOT));
        // Simbolos especiais
        ArrayList<TokenType> special = new ArrayList<>(List.of(DELIMITER, SEPARATOR, HEADER_DEF));

        return new ArrayList<String>(List.of(
                words.stream().map(TokenType::getText).collect(Collectors.joining("|")),
                words2.stream().map(TokenType::getText).collect(Collectors.joining("|")),
                agr.stream().map(tokenType -> "\\"+tokenType.getText()).collect(Collectors.joining("|")),
                com.stream().map(TokenType::getText).collect(Collectors.joining("|")),
                ari.stream().map(tokenType -> "\\"+tokenType.getText()).collect(Collectors.joining("|")),
                log.stream().map(tokenType -> "\\"+tokenType.getText()).collect(Collectors.joining("|")),
                special.stream().map(TokenType::getText).collect(Collectors.joining("|"))
        ));
    }

    @Getter
    private final String text;
}
