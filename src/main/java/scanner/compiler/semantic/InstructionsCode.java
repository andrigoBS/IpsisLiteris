package scanner.compiler.semantic;

public enum InstructionsCode {
    STP("STP"),

    ALI("ALI"),
    ALR("ALR"),
    ALS("ALS"),
    ALB("ALB"),

    LDI("LDI"),
    LDR("LDR"),
    LDS("LDS"),
    LDB("LDB"),

    STC("STC"),

    REA("REA"),
    STR("STR"),

    WRT("WRT"),
    LDV("LDV"),

    JMF("JMF"),
    JMT("JMT"),
    JMP("JMP"),

    //Relacional
    EQL("EQL"),
    DIF("DIF"),
    SMR("SMR"),
    BGR("BGR"),
    SME("SME"),
    BGE("BGE"),

    //Aritimética
    ADD("ADD"),
    SUB("SUB"),
    MUL("MUL"),
    DIV("DUV"),

    //Logica
    OR("OR"),
    AND("AND"),
    NOT("NOT");

    public final String label;

    private InstructionsCode(String label) {
        this.label = label;
    }
}
