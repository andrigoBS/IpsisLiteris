package scanner.compiler.virtualMachine;

import lombok.Data;
import scanner.compiler.errors.ErrorMessage;


import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;

@Data
public class Executer {

    private final Stack<Object> stack = new Stack<>();

    private int pointer = 0;

    private boolean halt = false;

    private Consumer<String> read;

    private Consumer<ErrorMessage> error;

    private Function<Class<?>, Object> write;

}
