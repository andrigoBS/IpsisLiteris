package scanner.compiler.semantic;
import lombok.AllArgsConstructor;
import scanner.compiler.semantic.VariableCategory;
@AllArgsConstructor
public class Symbol {
    public String Identifier;
    public VariableCategory category;
    public int atribute_01;
    public int atribute_02;
}
