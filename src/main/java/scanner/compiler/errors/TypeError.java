package scanner.compiler.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TypeError {
    SCANNER("Léxico"),
    PARSER("Sintático");

    @Getter
    private final String name;
}
