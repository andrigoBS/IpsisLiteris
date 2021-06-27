package scanner.compiler.semantic;
import javafx.util.converter.IntegerStringConverter;
import scanner.compiler.build.IpsisLiterisConstants;
import scanner.compiler.build.Token;
import scanner.compiler.build.TokenMgrError;
import scanner.compiler.semantic.Symbol;
import scanner.compiler.semantic.VariableCategory;
import scanner.compiler.semantic.InstructionsCode;
import scanner.compiler.semantic.Context;
import scanner.model.dto.InstructionRowDTO;

public final class Actions {
    static boolean isVariable(VariableCategory cat){
        return cat == VariableCategory.VAR_INT || cat == VariableCategory.VAR_LIT || cat == VariableCategory.VAR_LOG || cat == VariableCategory.VAR_REA;
    }

    static void Instruction(Semantic target, InstructionsCode instruction, Object parameter){

        InstructionRowDTO new_instruction = new InstructionRowDTO(target.instruction_pointer, instruction.label);
        new_instruction.setParameter(parameter);
        target.program.add(new_instruction);
    }

    public static void AC1_EndOfProgram(Semantic target){
        Instruction(target, InstructionsCode.STP, 0);
    }

    public static void AC2_ProgramIdentifierRec(Semantic target, Token identifier){
        var identifier_value = identifier.image;
        Symbol program_identifier = new Symbol(identifier_value,VariableCategory.UNKNON, -1, -1);
        target.symbolTable.put(identifier_value, program_identifier);
    }

    public static void AC3_ContextToConstant(Semantic target){
        target.context = Context.DEC_CONST;
        target.VIT = 0;
    }

    public static void AC4_EndOfDeclaration (Semantic target){
        target.VP += target.VIT;
        switch (target.type){
            case VAR_INT, CONST_INT -> {
                Instruction(target, InstructionsCode.ALI, target.VP);
                target.instruction_pointer += 1;
            }
            case VAR_REA, CONST_REA -> {
                Instruction(target, InstructionsCode.ALR, target.VP);
                target.instruction_pointer += 1;
            }
            case VAR_LIT, CONST_LIT -> {
                Instruction(target, InstructionsCode.ALS, target.VP);
                target.instruction_pointer += 1;
            }
            case VAR_LOG -> {
                Instruction(target, InstructionsCode.ALB, target.VP);
                target.instruction_pointer += 1;
            }
        }
        if(target.type == VariableCategory.VAR_INT ||
            target.type == VariableCategory.VAR_REA ||
            target.type == VariableCategory.VAR_LIT ||
            target.type == VariableCategory.VAR_LOG){
            target.VP = 0;
            target.VIT = 0;
        }
    }

    public static void AC5_ConstantDeclarationRecognition(Semantic target, Token value){
        switch (target.type){
            case CONST_INT -> {
                if(value.kind != IpsisLiterisConstants.INTEGER){
                    throw new TokenMgrError("É esperado um Inteiro!", -1);
                }
                Instruction(target, InstructionsCode.LDI, value.image);
                target.instruction_pointer += 1;
            }
            case CONST_REA -> {
                if(value.kind != IpsisLiterisConstants.REAL) throw new TokenMgrError("É esperado um Real!", -1);
                Instruction(target, InstructionsCode.LDR, value.image);
                target.instruction_pointer += 1;
            }
            case CONST_LIT -> {
                if(value.kind != IpsisLiterisConstants.LITERAL) throw new TokenMgrError("É esperado um Literal!", -1);
                Instruction(target, InstructionsCode.LDS, value.image);
                target.instruction_pointer += 1;
            }
        }
        Instruction(target, InstructionsCode.STC, target.VP);
        target.instruction_pointer += 1;
        target.VP = 0;
    }

    public static void AC6_VariableContextRecgnition(Semantic target){
        target.context = Context.DEC_VAR;
    }

    public static void AC7_NaturalType(Semantic target){
        target.type = target.context == Context.DEC_VAR ? VariableCategory.VAR_INT : VariableCategory.CONST_INT;
    }
    public static void AC8_RealType(Semantic target){
        target.type = target.context == Context.DEC_VAR ? VariableCategory.VAR_REA : VariableCategory.CONST_REA;
    }
    public static void AC9_LiteralType(Semantic target){
        target.type = target.context == Context.DEC_VAR ? VariableCategory.VAR_LIT : VariableCategory.CONST_LIT;
    }
    public static void AC10_LogicalType(Semantic target){
        target.type = VariableCategory.VAR_LOG;
    }

    public static void AC11_ConstantRecognition(Semantic target, Token value) {
        if(value == null) throw new TokenMgrError("Deu ruim brother", -2);

        var identifier_value = value.image;
        if(target.symbolTable.containsKey(identifier_value)) throw new TokenMgrError("Identificador " + identifier_value +  " já declarado", -1);

        target.VT += 1;
        target.VP += 1;
        Symbol new_symbol = new Symbol(identifier_value, target.type, target.VT, -1);
        target.symbolTable.put(identifier_value, new_symbol);
    }

    public static void AC12_VariableRecognition(Semantic target, Token value) {
        if(value == null)
            throw new TokenMgrError("Deu ruim brother", -2);

        var identifier_value = value.image;
        if(target.context == Context.DEC_VAR){
            if(target.symbolTable.containsKey(identifier_value))
                throw new TokenMgrError("Identificador " + identifier_value +  " já declarado", -1);
        }else{
            target.indexable_variable = false;
        }
        target.as12.push(value);
    }

    public static void AC13_IndexableVariableSize(Semantic target){
        switch (target.context) {
            case DEC_VAR -> {
                var identifier_value = target.as12.pop().image;
                if (!target.indexable_variable) {
                    target.VT += 1;
                    target.VP += 1;

                    Symbol new_symbol = new Symbol(identifier_value, target.type, target.VT, -1);
                    target.symbolTable.put(identifier_value, new_symbol);
                } else {
                    target.VIT += target.as14;
                    Symbol new_symbol = new Symbol(identifier_value, target.type, target.VT + 1, target.as14);
                    target.symbolTable.put(identifier_value, new_symbol);
                    target.VT += target.as14;
                }
            }
            case ATRIBUITION -> {
                while (!target.as12.empty()) {
                    var identifier_value =  target.as12.pop().image;
                    if (!target.symbolTable.containsKey(identifier_value) || !isVariable(target.symbolTable.get(identifier_value).category)) {
                        throw new TokenMgrError("Identificador não declarado ou é constante!", -3);
                    }

                    var entry = target.symbolTable.get(identifier_value);

                    if (entry.atribute_02 == -1) {
                        if (target.indexable_variable) throw new TokenMgrError("Variável não indexavel!", -3);
                        target.as13.push(entry.atribute_01);
                    } else {
                        if (!target.indexable_variable)
                            throw new TokenMgrError("Variável indexada exige um índice!", -3);
                        target.as13.push(entry.atribute_01 + target.as14 - 1);
                    }
                }
            }
            case DATA_ENTRY -> {
                for (var variable : target.as12) {
                    var identifier_value = variable.image;
                    if (!target.symbolTable.containsKey(identifier_value) || !isVariable(target.symbolTable.get(identifier_value).category)) {
                        throw new TokenMgrError("Identificador não declarado ou é constante!", -3);
                    }

                    var entry = target.symbolTable.get(identifier_value);

                    if (entry.atribute_02 == -1) {
                        if (target.indexable_variable) throw new TokenMgrError("Variável não indexavel!", -3);

                        var category = entry.category;
                        Instruction(target, InstructionsCode.REA, category);
                        target.instruction_pointer += 1;
                        Instruction(target, InstructionsCode.STR, entry.atribute_01);
                        target.instruction_pointer += 1;
                    } else {
                        if (!target.indexable_variable)
                            throw new TokenMgrError("Variável indexada exige um índice!", -3);

                        var category = entry.category;
                        Instruction(target, InstructionsCode.REA, category);
                        target.instruction_pointer += 1;
                        Instruction(target, InstructionsCode.STR, entry.atribute_01 + target.as14 - 1);
                        target.instruction_pointer += 1;
                    }
                }
            }
        }
    }

    public static void AC14_IndexableVariableIndexRecognition(Semantic target, Token value){
        target.as14 = Integer.parseInt(value.image);
        target.indexable_variable = true;
    }

    public static void AC15_AtribuitionBegin(Semantic target){
        target.context = Context.ATRIBUITION;
    }

    public static void AC16_AtribuitionEnd(Semantic target){
        while(!target.as13.empty()){
            var attribute = target.as13.pop();
            Instruction(target, InstructionsCode.STR, attribute);
            target.instruction_pointer += 1;
        }
    }

    public static void AC17_ContextToEntry(Semantic target){
        target.context = Context.DATA_ENTRY;
    }

    public static void AC18_DataOutput(Semantic target){
        Instruction(target, InstructionsCode.WRT, 0);
        target.instruction_pointer += 1;
    }

    public static void AC19_DataOutputIdentifierRecognition(Semantic target, Token value){
        if(target.symbolTable.containsKey(value.image)){
            target.as12.push(value);
            target.indexable_variable = false;
        }else{
            // ERRO IDENTIFICADOR NAO DECLARADO
        }
    }

    public static void AC20_DataOutputIndexableIdentifierRecognition(Semantic target){
        var last_token = target.as12.pop();
        var identifier = target.symbolTable.get(last_token.image);
        if(!target.indexable_variable){
            if (identifier.atribute_02 == -1){
                Instruction(target, InstructionsCode.LDV, identifier.atribute_01);
                target.instruction_pointer += 1;
            }else{
                //“identificador de variável indexada exige índice”
            }
        }else{
            if (identifier.atribute_02 != -1){
                Instruction(target, InstructionsCode.LDV, identifier.atribute_01 + target.as14 - 1);
                target.instruction_pointer += 1;
            }else{
                //erro: “identificador de constante ou de variável não indexada”
            }
        }
    }

    public static void AC21_ConstantIntegerToOutputOrExpression(Semantic target, Token value){
        Instruction(target, InstructionsCode.LDI, value);
        target.instruction_pointer += 1;
    }
    public static void AC22_ConstantRealToOutputOrExpression(Semantic target, Token value){
        Instruction(target, InstructionsCode.LDR, value);
        target.instruction_pointer += 1;
    }
    public static void AC23_ConstantLiteralToOutputOrExpression(Semantic target, Token value){
        Instruction(target, InstructionsCode.LDS, value);
        target.instruction_pointer += 1;
    }
    public static void AC24_EndOfSelection(Semantic target){
        var jump_address = target.jumpStack.pop();
        for(var instruction : target.program){
            if(instruction.getAddress() == jump_address){
                instruction.setParameter(target.instruction_pointer);
                break;
            }
        }
    }

    public static void AC25_JMF(Semantic target){
        Instruction(target, InstructionsCode.JMF, "?");
        target.instruction_pointer += 1;
        target.jumpStack.add(target.instruction_pointer - 1);
    }

    public static void AC26_JMT(Semantic target){
        Instruction(target, InstructionsCode.JMT, "?");
        target.instruction_pointer += 1;
        target.jumpStack.add(target.instruction_pointer - 1);
    }

    public static void AC27_JMP(Semantic target){
        var jump_address = target.jumpStack.pop();
        for(var instruction : target.program){
            if(instruction.getAddress() == jump_address){
                instruction.setParameter(target.instruction_pointer + 1);
                Instruction(target, InstructionsCode.JMP, "?");
                target.instruction_pointer += 1;
                target.jumpStack.add(target.instruction_pointer - 1);
                break;
            }
        }
    }

    public static void AC28_LoopRecognition(Semantic target){
        target.jumpStack.add(target.instruction_pointer);
    }

    public static void AC29_EndOfLoop(Semantic target){
        var jump_address = target.jumpStack.pop();
        Instruction(target, InstructionsCode.JMT, jump_address);
        target.instruction_pointer += 1;
    }

    public static void AC30_LoopExpressionRecognition(Semantic target){
        target.jumpStack.add(target.instruction_pointer);
    }

    public static void AC31_ExpressionJMF(Semantic target){
        Instruction(target, InstructionsCode.JMF, "?");
        target.instruction_pointer += 1;
        target.jumpStack.add(target.instruction_pointer - 1);
    }

    public static void AC32_EndOfLoop(Semantic target){
        var jump_address31 = target.jumpStack.pop();
        var jump_address30 = target.jumpStack.pop();
        for(var instruction : target.program){
            if(instruction.getAddress() == jump_address31){
                instruction.setParameter(target.instruction_pointer + 1);
                Instruction(target, InstructionsCode.JMP, jump_address30);
                target.instruction_pointer += 1;
                break;
            }
        }
    }

    public static void AC33_RelationalEqual(Semantic target){
        Instruction(target, InstructionsCode.EQL, 0);
        target.instruction_pointer += 1;
    }
    public static void AC34_RelationalDiferent(Semantic target){
        Instruction(target, InstructionsCode.DIF, 0);
        target.instruction_pointer += 1;
    }
    public static void AC35_RelationalSmaller(Semantic target){
        Instruction(target, InstructionsCode.SMR, 0);
        target.instruction_pointer += 1;
    }
    public static void AC36_RelationalBigger(Semantic target){
        Instruction(target, InstructionsCode.BGR, 0);
        target.instruction_pointer += 1;
    }
    public static void AC37_RelationalSmallerEqual(Semantic target){
        Instruction(target, InstructionsCode.BGE, 0);
        target.instruction_pointer += 1;
    }
    public static void AC38_RelationalBiggerEqual(Semantic target){
        Instruction(target, InstructionsCode.BGE, 0);
        target.instruction_pointer += 1;
    }
    public static void AC39_AritimeticAdd(Semantic target){
        Instruction(target, InstructionsCode.ADD, 0);
        target.instruction_pointer += 1;
    }
    public static void AC40_AritimeticSub(Semantic target){
        Instruction(target, InstructionsCode.SUB, 0);
        target.instruction_pointer += 1;
    }
    public static void AC41_LogicOr(Semantic target){
        Instruction(target, InstructionsCode.OR, 0);
        target.instruction_pointer += 1;
    }
    public static void AC42_AritimeticMultiply(Semantic target){
        Instruction(target, InstructionsCode.MUL, 0);
        target.instruction_pointer += 1;
    }
    public static void AC43_AritimeticDivide(Semantic target){
        Instruction(target, InstructionsCode.DIV, 0);
        target.instruction_pointer += 1;
    }
    public static void AC44_AritimeticDivideInteger(Semantic target){
        //TODO
        target.instruction_pointer += 1;
    }
    public static void AC45_AritimeticModule(Semantic target){
        //TODO
        target.instruction_pointer += 1;
    }

    public static void AC46_LogicAnd(Semantic target){
        Instruction(target, InstructionsCode.AND, 0);
        target.instruction_pointer += 1;
    }
    public static void AC47_AritimeticPotency(Semantic target){
        //TODO
        target.instruction_pointer += 1;
    }
    public static void AC48_LogicalConstantTrue(Semantic target){
        Instruction(target, InstructionsCode.LDB, "true");
        target.instruction_pointer += 1;
    }
    public static void AC49_LogicalConstantFalse(Semantic target){
        Instruction(target, InstructionsCode.LDB, "false");
        target.instruction_pointer += 1;
    }
    public static void AC50_LogicNot(Semantic target){
        Instruction(target, InstructionsCode.NOT, 0);
        target.instruction_pointer += 1;
    }

}
