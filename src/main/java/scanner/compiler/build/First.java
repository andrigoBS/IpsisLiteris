package scanner.compiler.build;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public enum First implements IpsisLiterisConstants {

    // Regras

    ID (List.of(IDENTIFIER)),

    ID_PUT (List.of(IDENTIFIER)),

    CONSTANTS (List.of(LITERAL, INTEGER, FLOAT, TRUE, FALSE)),

    CONSTANTS_PUT (List.of(LITERAL, INTEGER, FLOAT)),

    VALUE (List.of(), List.of(ID, CONSTANTS)),

    // Grupos

    MEDIUM_PRIORITY (List.of(TIMES, DIVIDE, INT_DIVIDE, MOD, AND)),

    LOW_PRIORITY (List.of(PLUS, MINUS, OR)),

    COMPARATOR (List.of(TIMES, DIVIDE, INT_DIVIDE, MOD, AND)),

    TYPE (List.of(NAT, REAL, CHAR, BOOL)),

    // Expressão

    ID_ELEMENT (List.of(IDENTIFIER)),

    CONSTANTS_ELEMENT (List.of(LITERAL, INTEGER, FLOAT, TRUE, FALSE)),

    VALUE_ELEMENT (List.of(), List.of(ID_ELEMENT, CONSTANTS_ELEMENT)),

    VALUE_PRINT(List.of(), List.of(ID_PUT, CONSTANTS)),

    PARENTESIS_EXP(List.of(OPEN_PARENT)),

    ELEMENT (List.of(NOT), List.of(VALUE_ELEMENT, PARENTESIS_EXP)),

    TERM1 (List.of(), List.of(ELEMENT)),

    TERM2 (List.of(), List.of(TERM1)),

    EXP_LOGIC_ARITMETIC (List.of(), List.of(TERM2)),

    EXPRESSION (List.of(), List.of(EXP_LOGIC_ARITMETIC)),

    ID_LIST (List.of(), List.of(ID)),

    ATTRIBUTION (List.of(SET)),

    SELECT (List.of(IF)),

    PRINT (List.of(PUT)),

    READ (List.of(GET)),

    WHILE_DO (List.of(WHILE)),

    DO_WHILE (List.of(LOOP)),

    COMMAND (List.of(), List.of(ATTRIBUTION, SELECT, PRINT, READ, DO_WHILE, WHILE_DO)),

    COMMAND_LIST (List.of(OPEN_CURLY)),

    MAIN (List.of(EXE)),

    VAR_DEF (List.of(VAR)),

    CONST_DEF (List.of(NOT_VAR)),

    VAR_FIELD (List.of(), List.of(CONST_DEF, VAR_DEF)),

    VAR_DECLARATION (List.of(DATA_DEF)),

    PROGRAM (List.of(HEADER_DEF, DEF));
    
    First(List<Integer> first){
        this.first = first;
    }

    First(List<Integer> first, List<First> sideFirsts) {
        List<Integer> list = new ArrayList<>(first);
        for (First firstList : sideFirsts) {
            list.addAll(firstList.getFirst());
        }
        this.first = list;
    }

    @Getter
    private final List<Integer> first;

}
