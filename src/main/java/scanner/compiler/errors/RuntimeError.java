package scanner.compiler.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum RuntimeError {

    // Runtime
    OPERATION_VALUES("ERRO EM TEMPO DE EXECUÇÃO:\n\tOperação {OPERATION} " +
            "não aplicável aos valores \"{VALUE1}\" do tipo {TYPE1} e \"{VALUE2}\" do tipo {TYPE2}."),

    OPERATION_VALUE("ERRO EM TEMPO DE EXECUÇÃO:\n\tOperação {OPERATION} " +
            "não aplicável ao valore \"{VALUE1}\" do tipo {TYPE1}."),

    ZERO_DIVISION("ERRO EM TEMPO DE EXECUÇÃO:\n\tDivisão por zero."),

    TYPE_ERROR("ERRO EM TEMPO DE EXECUÇÃO:\n\tValor \"{VALUE1}\" de tipo {TYPE1} " +
            "incompatível com variável de tipo {TYPE2}."),

    READ_TYPE_ERROR ("ERRO EM TEMPO DE EXECUÇÃO:\n\tValor \"{VALUE1}\" lido é " +
            "incompatível com variável de tipo {TYPE2}."),

    CONSTANT_ERROR("ERRO EM TEMPO DE EXECUÇÃO:\n\tNão é possível sobrescrever uma constante."),

    INFINITE_PROGRAM("ERRO EM TEMPO DE EXECUÇÃO:\n\tNão há mais instruções, mas o programa não foi finalizado");

    @Getter
    private final String text;

}
