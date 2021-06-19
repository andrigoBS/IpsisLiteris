package scanner.compiler.virtualMachine.commands;

import scanner.compiler.errors.ErrorMessage;
import scanner.compiler.virtualMachine.Executer;

import java.util.Map;
import java.util.Stack;


public enum Commands implements Command {

    // Operações Aritméticas

    ADD {
        @Override
        public void execute(Object parameter, Executer executer) {
            Stack<Object> stack = executer.getStack();
            Object param1 = stack.pop();
            Object param2 = stack.pop();
            if (Commands.isNumeric(param1) && Commands.isNumeric(param2)){
                stack.push(numericSum(param1, param2));
                return;
            }
            if (param1.getClass().equals(String.class) && param2.getClass().equals(String.class)) {
                stack.push(param1.toString() + param2);
                return;
            }
            Commands.throwError(ErrorMessage.LOGIC_ARITHMETIC, executer);
        }

        private Object numericSum (Object param1, Object param2) {
            if (param1.getClass().equals(Double.class) || param2.getClass().equals(Double.class)){
                return Double.parseDouble(param1.toString()) + Double.parseDouble(param2.toString());
            }
            return Integer.parseInt(param1.toString()) + Integer.parseInt(param2.toString());
        }
    },

    DIV {
        @Override
        public void execute(Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!Commands.isNumeric(param1) || !Commands.isNumeric(param2)){
                Commands.throwError(ErrorMessage.NOT_A_NUMBER, executer);
            }
            if (param1.equals(0)) {
                Commands.throwError(ErrorMessage.ZERO_DIVISION, executer);
            }
        }
    },

    MUL {
        @Override
        public void execute(Object parameter, Executer executer) {
            Commands.throwError(ErrorMessage.NOT_A_NUMBER, executer);
        }
    },

    SUB {
        @Override
        public void execute(Object parameter, Executer executer) {
            Commands.throwError(ErrorMessage.NOT_A_NUMBER, executer);
        }
    },

    MOD {
        @Override
        public void execute(Object parameter, Executer executer) {
            Commands.throwError(ErrorMessage.NOT_A_NUMBER, executer);
        }
    },

    // Memória

    ALB {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    ALI {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    ALR {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    ALS {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    LDB {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    LDI {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    LDR {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    LDS {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    LDV {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    STR {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    // Lógica

    AND {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    NOT {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    OR {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    // Relacional

    BGE {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    BGR {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    DIF {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    EQL {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    SME {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    SMR {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    // Desvio

    JMF {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    JMP {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    JMT {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    // Syscall

    STP {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    REA {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    WRT {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    };

    private static boolean isNumeric (Object object) {
        return object.getClass().equals(Integer.class) || object.getClass().equals(Double.class);
    }

    private static void throwError(ErrorMessage error, Executer executer){
        executer.getError().accept(error);
        executer.setHalt(true);
    }

}
