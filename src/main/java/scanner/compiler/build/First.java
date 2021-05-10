package scanner.compiler.build;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public enum First implements IpsisLiterisConstants {

    // Grupos

    MEDIUM_PRIORITY (List.of(TIMES, DIVIDE, INT_DIVIDE, MOD, AND)),

    LOW_PRIORITY (List.of(PLUS, MINUS, OR)),

    COMPARATOR (List.of(TIMES, DIVIDE, INT_DIVIDE, MOD, AND)),

    CONSTANTS (List.of(LITERAL, INTEGER, FLOAT, TRUE, FALSE)),

    TYPE (List.of(NAT, REAL, CHAR, BOOL)),

    // Regras

    ID (List.of(IDENTIFIER)),

    VALUE (List.of(), List.of(ID, CONSTANTS)),

    PARENTESIS_EXP(List.of(OPEN_PARENT)),

    ELEMENT (List.of(NOT), List.of(VALUE, PARENTESIS_EXP)),

    ELEMENT1 (List.of(), List.of(ELEMENT)),

    ELEMENT2 (List.of(), List.of(ELEMENT1)),

    EXP_LOGIC_ARITMETIC (List.of(), List.of(ELEMENT2)),

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
