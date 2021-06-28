package scanner.compiler.semantic;

public enum VariableCategory {

    UNKNON(-1),
    VAR_INT(1),
    VAR_REA(2),
    VAR_LIT(3),
    VAR_LOG(4),
    CONST_INT(1),
    CONST_REA(2),
    CONST_LIT(3);

    private final int value;

    private VariableCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
