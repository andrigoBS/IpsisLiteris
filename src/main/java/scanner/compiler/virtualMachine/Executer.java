package scanner.compiler.virtualMachine;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

@Data
public class Executer {

    private final Stack<Object> stack = new Stack<>();

    private int pointer = 0;

    @Setter(value = AccessLevel.PRIVATE)
    private boolean halt = false;

    @NonNull
    private Callable<String> read;

    @NonNull
    private Consumer<String> error;

    @NonNull
    private Consumer<String> write;

    public void halt() {
        setHalt(true);
    }

    public void increasePointer () {
        pointer++;
    }

}
