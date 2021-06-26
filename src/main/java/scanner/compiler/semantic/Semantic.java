package scanner.compiler.semantic;
import scanner.compiler.build.IpsisLiterisConstants;
import scanner.compiler.build.Token;
import scanner.compiler.semantic.VariableCategory;
import scanner.compiler.semantic.Symbol;
import scanner.model.dto.InstructionRowDTO;


import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;


public class Semantic {
    public int VT = 0;
    public int VP = 0;
    public int VIT = 0;
    public Context context;
    public VariableCategory type;

    public int instruction_pointer = 1;
    public boolean indexable_variable = false;

    public Stack<Integer> jumpStack = new Stack<Integer>();
    public HashMap<String, Symbol> symbolTable = new HashMap<String, Symbol>();

    public Vector<InstructionRowDTO> program = new Vector<InstructionRowDTO>();
}
