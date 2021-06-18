package scanner.model.dto;

import lombok.Data;

@Data
public class InstructionRowDTO {
    private final int address;
    private final String command;
    private int parameter;
}
