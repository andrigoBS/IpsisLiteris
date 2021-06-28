package scanner.compiler.virtualMachine.commands;

import lombok.AllArgsConstructor;
import scanner.compiler.errors.ErrorMessage;
import scanner.compiler.errors.RuntimeError;
import scanner.compiler.virtualMachine.Executer;
import scanner.compiler.virtualMachine.IdEst;

import java.util.*;
import java.util.function.Function;


public enum Commands implements Command {

    // Operações Aritméticas

    ADD {

        @Override
        public void execute (Object parameter, Executer executer) {
            Stack<Object> stack = executer.getStack();
            Object param1 = stack.pop();
            Object param2 = stack.pop();
            if (param1 instanceof Number && param2 instanceof Number){
                stack.push(numericSum((Number) param1, (Number) param2));
                return;
            }
            if (param1 instanceof String && param2 instanceof String){
                stack.push(param2.toString() + param1);
                return;
            }
            Commands.throwError(
                    applyValues(RuntimeError.OPERATION_VALUES, "SOMA", param1, param2), executer
            );
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
                Commands.throwError(
                        applyValues(RuntimeError.OPERATION_VALUES, "DIVISÃO", param1, param2), executer
                );
                return;
            }
            if (((Number)param1).intValue() == 0) {
                Commands.throwError(RuntimeError.ZERO_DIVISION.getText(), executer);
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
                Commands.throwError(
                        applyValues(RuntimeError.OPERATION_VALUES, "MULTIPLICAÇÃO", param1, param2), executer
                );
                return;
            }
            if (param1 instanceof Integer && param2 instanceof Integer){
                executer.getStack().push((Integer) param1 * (Integer) param2);
                return;
            }
            executer.getStack().push(((Number)param2).doubleValue() * ((Number)param1).doubleValue());
        }
    },

    SUB {

        @Override
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Number) || !(param2 instanceof Number)){
                Commands.throwError(
                        applyValues(RuntimeError.OPERATION_VALUES, "SUBTRAÇÃO", param1, param2), executer
                );
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
                Commands.throwError(
                        applyValues(RuntimeError.OPERATION_VALUES, "RESTO DE DIVISÃO", param1, param2), executer
                );
                return;
            }
            executer.getStack().push(((Integer) param2) % ((Integer) param1));
        }
    },

    IDV {
        @Override
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Integer) || !(param2 instanceof Integer)){
                Commands.throwError(
                        applyValues(RuntimeError.OPERATION_VALUES, "DIVISÃO INTEIRA", param1, param2), executer
                );
                return;
            }
            executer.getStack().push(((Integer) param2) / ((Integer) param1));
        }
    },

    POW {
        @Override
        public void execute(Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Number) || ! (param2 instanceof Number)){
                Commands.throwError(
                        applyValues(RuntimeError.OPERATION_VALUES, "POTENCIAÇÃO", param1, param2), executer
                );
                return;
            }
            double result = Math.pow(((Number)param2).doubleValue(), ((Number)param1).doubleValue());
            if (result == Math.floor(result) && Double.isFinite(result)){
                executer.getStack().push((int) result);
                return;
            }
            executer.getStack().push(result);
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
            int address = (Integer) parameter - 1;
            Stack<Object> stack = executer.getStack();
            stack.push(stack.get(address));
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
            Commands.throwError(
                    applyValues(RuntimeError.TYPE_ERROR, null, loaded, stack.get(address)), executer
            );
        }
    },

    STC {
        @Override
        public void execute(Object parameter, Executer executer) {
            int range = (Integer) parameter - 1;
            Stack<Object> stack = executer.getStack();
            Object value = stack.pop();
            for (int i = range; i < stack.size(); i ++) {
                if (! stack.get(i).getClass().equals(value.getClass()) ) {
                    Commands.throwError(
                            applyValues(RuntimeError.TYPE_ERROR, null, value, stack.get(i)), executer
                    );
                    return;
                }
                stack.set(i, value);
            }
        }
    },

    DPC {
        @Override
        public void execute (Object parameter, Executer executer) {
            executer.getStack().push(executer.getStack().peek());
        }
    },

    // Lógica

    AND {
        @Override
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Boolean) || !(param2 instanceof Boolean)){
                Commands.throwError(
                        applyValues(RuntimeError.OPERATION_VALUES, "\"E\" LÓGICO", param1, param2), executer
                );
                return;
            }
            executer.getStack().push(((Boolean) param2) && ((Boolean) param1));
        }
    },

    NOT {
        @Override
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            if (!(param1 instanceof Boolean)){
                Commands.throwError(
                        applyValues(RuntimeError.OPERATION_VALUE, "\"NOT\" LÓGICO", param1), executer
                );
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
                Commands.throwError(
                        applyValues(RuntimeError.OPERATION_VALUES, "\"OU\" LÓGICO", param1, param2), executer
                );
                return;
            }
            executer.getStack().push(((Boolean) param2) || ((Boolean) param1));
        }
    },

    // Relacional

    BGE {
        @Override
        @SuppressWarnings("DuplicatedCode")
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Number) || !(param2 instanceof Number)){
                Commands.throwError(
                        applyValues(RuntimeError.OPERATION_VALUES, "MAIOR OU IGUAL", param1, param2), executer
                );
                return;
            }
            executer.getStack().push(((Number)param2).doubleValue() >= ((Number)param1).doubleValue());
        }
    },

    BGR {
        @Override
        @SuppressWarnings("DuplicatedCode")
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Number) || !(param2 instanceof Number)){
                Commands.throwError(
                        applyValues(RuntimeError.OPERATION_VALUES, "MAIOR QUE", param1, param2), executer
                );
                return;
            }
            executer.getStack().push(((Number)param2).doubleValue() > ((Number)param1).doubleValue());
        }
    },

    DIF {
        @Override
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            executer.getStack().push(!param2.equals(param1));
        }
    },

    EQL {
        @Override
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            executer.getStack().push(param2.equals(param1));
        }
    },

    SME {
        @Override
        @SuppressWarnings("DuplicatedCode")
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Number) || !(param2 instanceof Number)){
                Commands.throwError(
                        applyValues(RuntimeError.OPERATION_VALUES, "MENOR OU IGUAL", param1, param2), executer
                );
                return;
            }
            executer.getStack().push(((Number)param2).doubleValue() <= ((Number)param1).doubleValue());
        }
    },

    SMR {
        @Override
        @SuppressWarnings("DuplicatedCode")
        public void execute (Object parameter, Executer executer) {
            Object param1 = executer.getStack().pop();
            Object param2 = executer.getStack().pop();
            if (!(param1 instanceof Number) || !(param2 instanceof Number)){
                Commands.throwError(
                        applyValues(RuntimeError.OPERATION_VALUES, "MENOR QUE", param1, param2), executer
                );
                return;
            }
            executer.getStack().push(((Number)param2).doubleValue() < ((Number)param1).doubleValue());
        }
    },

    // Desvio

    JMF {
        @Override
        public void execute (Object parameter, Executer executer) {
            Commands.jumpIf(false, parameter, executer);
        }
    },

    JMP {
        @Override
        public void execute (Object parameter, Executer executer) {
            int address = (Integer) parameter - 1;
            executer.setPointer(address - 1);
        }
    },

    JMT {
        @Override
        public void execute (Object parameter, Executer executer) {
            Commands.jumpIf(true, parameter, executer);
        }
    },

    // Syscall

    STP {
        @Override
        public void execute (Object parameter, Executer executer) {
            executer.halt();
        }
    },

    REA {

        private final List<Function<String, Object>> functionList = List.of(
                Integer::parseInt,
                Double::parseDouble,
                String::new,
                IdEst::parseBool
        );

        @Override
        public void execute (Object parameter, Executer executer) {
            int type = (Integer) parameter - 1;
            if (type >= functionList.size()) {
                Commands.throwError(RuntimeError.CONSTANT_ERROR.getText(), executer);
                return;
            }
            try {
                String call = executer.getRead().call();
                saveValue(type, call, executer);
            } catch (Exception e) {
                e.printStackTrace();
                Commands.throwError("ERRO EM CHAMADA DE SISTEMA PARA LEITURA", executer);
            }
        }

        private void saveValue (int type, String value, Executer executer){
            Function<String, Object> convert = functionList.get(type);
            try {
                Object param = convert.apply(value);
                executer.getStack().push(param);
            }catch (NumberFormatException e) {
                Commands.throwError(
                        applyValues(RuntimeError.TYPE_ERROR, null, value, convert.apply("1")), executer
                );
            }
        }
    },

    WRT {
        @Override
        public void execute (Object parameter, Executer executer) {
            executer.getWrite().accept(executer.getStack().pop().toString());
        }
    };

    private static void throwError (String message, Executer executer){
        executer.getError().accept(message);
        executer.halt();
    }

    private static void allocate (int space, Object value, Executer executer) {
        for (int i = 0; i < space; i++){
            executer.getStack().push(value);
        }
    }

    private static void load (Object value, Executer executer) {
        executer.getStack().push(value);
    }

    private static void jumpIf (boolean logicResult, Object parameter, Executer executer) {
        Object param = executer.getStack().pop();
        int address = (Integer) parameter - 1;
        if (!(param instanceof Boolean)) {
            Commands.throwError(
                    applyValues(RuntimeError.OPERATION_VALUE, "DESVIO CONDICIONAL", param), executer
            );
            return;
        }
        if (((Boolean) param) == logicResult) {
            executer.setPointer(address - 1);
        }
    }

    private static String applyValues(RuntimeError error, String operation, Object... values){

        Map<Class<?>, String> types = Map.of(
                Integer.class, "INTEIRO",
                Double.class, "REAL",
                Boolean.class, "LÓGICO",
                String.class, "LITERAL"
        );
        String errorMessage = error.getText().replace("{OPERATION}", operation);
        for (int i = 0; i < values.length ; i++){
            errorMessage = errorMessage.replace("{VALUE" + (i+1) + "}", values[i].toString())
                                        .replace("{TYPE" + (i+1) +"}", types.get(values[i].getClass()));
        }

        return errorMessage;
    }

}
