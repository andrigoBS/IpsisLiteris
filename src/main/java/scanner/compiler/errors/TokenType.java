package scanner.compiler.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
    INVALID_TOKEN("Token Inválido");

    public static TokenType parseToken(String token) {
        token = token.replace("<", "").replace(">", "");
        return TokenType.valueOf(token);
    }

    @Getter
    private final String text;
}
