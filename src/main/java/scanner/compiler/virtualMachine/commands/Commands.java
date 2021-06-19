package scanner.compiler.virtualMachine.commands;

import scanner.compiler.virtualMachine.Executer;

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

        }
    },

    MUL {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    SUB {
        @Override
        public void execute(Object parameter, Executer executer) {

        }
    },

    MOD {
        @Override
        public void execute(Object parameter, Executer executer) {

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

}
