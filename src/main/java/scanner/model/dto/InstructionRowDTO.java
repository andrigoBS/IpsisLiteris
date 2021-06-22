package scanner.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @AllArgsConstructor @RequiredArgsConstructor
public class InstructionRowDTO {

    private final int address;
    private final String command;
    private Object parameter;
}
