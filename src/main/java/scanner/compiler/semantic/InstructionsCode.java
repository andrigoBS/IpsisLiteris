package scanner.compiler.semantic;

public enum InstructionsCode {
    ADD("ADD"),
    STP("STP"),

    ALI("ALI"),
    ALR("ALR"),
    ALS("ALS"),
    ALB("ALB"),

    LDI("LDI"),
    LDR("LDR"),
    LDS("LDS");

    public final String label;

    private InstructionsCode(String label) {
        this.label = label;
    }
}
