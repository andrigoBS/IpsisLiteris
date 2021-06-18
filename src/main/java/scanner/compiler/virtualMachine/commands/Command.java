package scanner.compiler.virtualMachine.commands;

import scanner.compiler.virtualMachine.Executer;

import java.util.Stack;

@FunctionalInterface
public interface Command {

    void execute(Object parameter, Executer executer);

}
