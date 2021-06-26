package scanner.compiler.virtualMachine.commands;

import scanner.compiler.virtualMachine.Executer;

@FunctionalInterface
public interface Command {

    void execute(Object parameter, Executer executer);

}
