package scanner.compiler.semantic;

import scanner.compiler.build.Token;
import scanner.model.dto.InstructionRowDTO;

import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;


public class Semantic {
    Stack<Token> as12 = new Stack<>();
    Stack<Integer> as13 = new Stack<>();
    int as14;

    public int VT = 0;
    public int VP = 0;
    public int VIT = 0;
    public Context context;
    public VariableCategory type;

    public int instruction_pointer = 1;
    public boolean indexable_variable = false;

    public Stack<Integer> jumpStack = new Stack<>();
    public HashMap<String, Symbol> symbolTable = new HashMap<>();

    public Vector<InstructionRowDTO> program = new Vector<>();
}
