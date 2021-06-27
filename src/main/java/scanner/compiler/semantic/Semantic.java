package scanner.compiler.semantic;
import scanner.compiler.build.IpsisLiterisConstants;
import scanner.compiler.build.Token;
import scanner.compiler.semantic.VariableCategory;
import scanner.compiler.semantic.Symbol;
import scanner.model.dto.InstructionRowDTO;


import java.util.*;


public class Semantic {
    Stack<Token> as12 = new Stack<>();
    ArrayList<Integer> as13 = new ArrayList<Integer>();
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
