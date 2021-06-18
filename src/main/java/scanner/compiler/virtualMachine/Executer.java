package scanner.compiler.virtualMachine;

import lombok.Data;


import java.util.Stack;
import java.util.function.Consumer;

@Data
public class Executer {

    private final Stack<Object> stack = new Stack<>();

    private int pointer = 0;

}
