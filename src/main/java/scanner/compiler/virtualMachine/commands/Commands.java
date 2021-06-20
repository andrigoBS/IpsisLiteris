package scanner.compiler.virtualMachine.commands;

import scanner.compiler.errors.ErrorMessage;
import scanner.compiler.virtualMachine.Executer;

import java.util.Stack;

public enum Commands implements Command {

    // Operações Aritméticas

    ADD {
        @Override
        public void execute (Object parameter, Executer executer) {
            Stack<Object> stack = executer.getStack();
            Object param1 = stack.pop();
            Object param2 = stack.pop();
            if (param1 instanceof Boolean && param2 instanceof Boolean) {
                Commands.throwError(ErrorMessage.LOGIC_ARITHMETIC, executer);
                return;
            }
            if (param1 instanceof Number && param2 instanceof Number){
                stack.push(numericSum((Number) param1, (Number) param2));
                return;
            }
            stack.push(param1.toString() + param2);
        }

        private Object numericSum (Number param1, Number param2) {
            if (param1.getClass().equals(Double.class) || param2.getClass().equals(Double.class)){
                return param2.doubleValue() + param1.doubleValue();
            }
            return param2.intValue() + param1.intValue();
        }
    },

    DIV {
        @Override
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Number) || !(param2 instanceof Number)){
                Commands.throwError(ErrorMessage.NOT_A_NUMBER, executer);
                return;
            }
            if (((Number)param1).intValue() == 0) {
                Commands.throwError(ErrorMessage.ZERO_DIVISION, executer);
                return;
            }
            executer.getStack().push(((Number)param2).doubleValue() / ((Number) param1).doubleValue());
        }
    },

    MUL {
        @Override
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Number) || !(param2 instanceof Number)){
                Commands.throwError(ErrorMessage.NOT_A_NUMBER, executer);
                return;
            }
            if (param1 instanceof Integer && param2 instanceof Integer){
                executer.getStack().push((Integer) param1 * (Integer) param2);
                return;
            }
            executer.getStack().push(((Number)param1).doubleValue() * ((Number)param2).doubleValue());
        }
    },

    SUB {
        @Override
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Number) || !(param2 instanceof Number)){
                Commands.throwError(ErrorMessage.NOT_A_NUMBER, executer);
                return;
            }
            if (param1 instanceof Integer && param2 instanceof Integer){
                executer.getStack().push((Integer) param2 - (Integer) param1);
                return;
            }
            executer.getStack().push(((Number)param2).doubleValue() - ((Number)param1).doubleValue());
        }
    },

    MOD {
        @Override
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Integer) || !(param2 instanceof Integer)){
                Commands.throwError(ErrorMessage.NOT_A_INTEGER, executer);
                return;
            }
            executer.getStack().push(((Integer) param1) % ((Integer) param2));
        }
    },

    IDV {
        @Override
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Integer) || !(param2 instanceof Integer)){
                Commands.throwError(ErrorMessage.NOT_A_INTEGER, executer);
                return;
            }
            executer.getStack().push(((Integer) param1) / ((Integer) param2));
        }
    },

    // Memória

    ALB {
        @Override
        public void execute (Object parameter, Executer executer) {
            Commands.allocate((Integer) parameter, false, executer);
        }
    },

    ALI {
        @Override
        public void execute (Object parameter, Executer executer) {
            Commands.allocate((Integer) parameter, 0, executer);
        }
    },

    ALR {
        @Override
        public void execute (Object parameter, Executer executer) {
            Commands.allocate((Integer) parameter, 0.0, executer);
        }
    },

    ALS {
        @Override
        public void execute (Object parameter, Executer executer) {
            Commands.allocate((Integer) parameter, "", executer);
        }
    },

    LDB {
        @Override
        public void execute (Object parameter, Executer executer) {
            Boolean bool = (Boolean) parameter;
            Commands.load(bool, executer);
        }
    },

    LDI {
        @Override
        public void execute (Object parameter, Executer executer) {
            Integer integer = (Integer) parameter;
            Commands.load(integer, executer);
        }
    },

    LDR {
        @Override
        public void execute (Object parameter, Executer executer) {
            Double aDouble = (Double) parameter;
            Commands.load(aDouble, executer);
        }
    },

    LDS {
        @Override
        public void execute (Object parameter, Executer executer) {
            String string = (String) parameter;
            Commands.load(string, executer);
        }
    },

    LDV {
        @Override
        public void execute (Object parameter, Executer executer) {
            Integer address = (Integer) parameter;
            Stack<Object> stack = executer.getStack();
            stack.push(stack.get(address - 1));
        }
    },

    STR {
        @Override
        public void execute (Object parameter, Executer executer) {
            int address = (Integer) parameter - 1;
            Stack<Object> stack = executer.getStack();
            Object loaded = stack.pop();
            if (stack.get(address).getClass().equals(loaded.getClass())) {
                stack.set(address, loaded);
                return;
            }
            throw new ClassCastException();
        }
    },

    // Lógica

    AND {
        @Override
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Boolean) || !(param2 instanceof Boolean)){
                Commands.throwError(ErrorMessage.NOT_A_BOOLEAN, executer);
                return;
            }
            executer.getStack().push(((Boolean) param1) && ((Boolean) param2));
        }
    },

    NOT {
        @Override
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            if (!(param1 instanceof Boolean)){
                Commands.throwError(ErrorMessage.NOT_A_BOOLEAN, executer);
                return;
            }
            executer.getStack().push( !((Boolean)param1) );
        }
    },

    OR {
        @Override
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Boolean) || !(param2 instanceof Boolean)){
                Commands.throwError(ErrorMessage.NOT_A_BOOLEAN, executer);
                return;
            }
            executer.getStack().push(((Boolean) param1) || ((Boolean) param2));
        }
    },

    // Relacional

    BGE {
        @Override
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Number) || !(param2 instanceof Number)){
                Commands.throwError(ErrorMessage.NOT_A_NUMBER, executer);
                return;
            }
            executer.getStack().push(((Boolean) param1) && ((Boolean) param2));
        }
    },

    BGR {
        @Override
        public void execute (Object parameter, Executer executer) {

        }
    },

    DIF {
        @Override
        public void execute (Object parameter, Executer executer) {

        }
    },

    EQL {
        @Override
        public void execute (Object parameter, Executer executer) {

        }
    },

    SME {
        @Override
        public void execute (Object parameter, Executer executer) {

        }
    },

    SMR {
        @Override
        public void execute (Object parameter, Executer executer) {

        }
    },

    // Desvio

    JMF {
        @Override
        public void execute (Object parameter, Executer executer) {

        }
    },

    JMP {
        @Override
        public void execute (Object parameter, Executer executer) {

        }
    },

    JMT {
        @Override
        public void execute (Object parameter, Executer executer) {

        }
    },

    // Syscall

    STP {
        @Override
        public void execute (Object parameter, Executer executer) {

        }
    },

    REA {
        @Override
        public void execute (Object parameter, Executer executer) {

        }
    },

    WRT {
        @Override
        public void execute (Object parameter, Executer executer) {

        }
    };

    private static void throwError (ErrorMessage error, Executer executer){
        executer.getError().accept(error);
        executer.setHalt(true);
    }

    private static void allocate (int space, Object value, Executer executer) {
        for (int i = 0; i < space; i++){
            executer.getStack().push(value);
        }
    }

    private static void load (Object value, Executer executer) {
        executer.getStack().push(value);
    }

}
