package scanner.compiler.virtualMachine;

import javafx.application.Platform;
import lombok.Builder;
import lombok.Cleanup;
import scanner.compiler.errors.ErrorMessage;
import scanner.compiler.virtualMachine.commands.Commands;
import scanner.model.dto.InstructionRowDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

@Builder(setterPrefix = "on")
public class IdEst {

    private Callable<String> read;

    private Consumer<String> error;

    private Consumer<String> write;

    public void run(List<InstructionRowDTO> instructions) {
        Executer executer = new Executer(read, error, write);
        while (!executer.isHalt()){
            InstructionRowDTO instruction = instructions.get(executer.getPointer());
            Commands.valueOf(instruction.getCommand()).execute(instruction.getParameter(), executer);
            executer.increasePointer();
        }
        executer.getWrite().accept("\n\nPrograma Terminado ...");
    }

    public void run (InputStream inputStream) {
        run(getInstructions(inputStream));
    }

    public List<InstructionRowDTO> getInstructions (InputStream inputStream) {
        Map<String, Function<String, Object>> parsers = Map.of(
                "LDS", String::new,
                "LDR", Double::parseDouble,
                "LDB", IdEst::parseBool
        );
        List<InstructionRowDTO> instructions = new ArrayList<>();
        @Cleanup Scanner scanner = new Scanner(inputStream);
        Object param;
        int address = 1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                String[] instruction = new String[] {
                        line.substring(0, line.indexOf(" ")),
                        line.substring(line.indexOf(" ") + 1)
                };
                if (parsers.containsKey(instruction[0])) {
                    param = parsers.get(instruction[0]).apply(instruction[1]);
                } else {
                    param = Integer.parseInt(instruction[1]);
                }
                instructions.add(new InstructionRowDTO(address++, instruction[0], param));
            }
        }
        return instructions;
    }

    public static void main (String[] args) throws FileNotFoundException {
        IdEst.builder()
                .onError(System.err::println)
                .onRead(new Scanner(System.in)::nextLine)
                .onWrite(System.out::println)
                .build()
                .run(new FileInputStream(args[0]));
    }

    public static boolean parseBool (String string) {
        List<String> acceptedValues = List.of("true", "false", "1", "0");
        if (!acceptedValues.contains(string.trim())) {
            throw new NumberFormatException();
        }
        if (string.equals("1")){
            return true;
        }
        return Boolean.parseBoolean(string);
    }

}
