package scanner.compiler.semantic;
import scanner.compiler.build.IpsisLiterisConstants;
import scanner.compiler.build.Token;
import scanner.compiler.build.TokenMgrError;
import scanner.compiler.semantic.Symbol;
import scanner.compiler.semantic.VariableCategory;
import scanner.compiler.semantic.InstructionsCode;
import scanner.compiler.semantic.Context;
import scanner.model.dto.InstructionRowDTO;

public final class Actions {
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
        target.VP += + target.VIT;
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
            target.VP = target.VIT = 0;
        }
    }

    public static void AC5_ConstantDeclarationRecognition(Semantic target, Token value){
        switch (target.type){
            case CONST_INT -> {
                if(value.kind != IpsisLiterisConstants.INTEGER) throw new TokenMgrError("É esperado um Inteiro!", -1);
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
}
