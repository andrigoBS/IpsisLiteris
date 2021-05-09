/* IpsisLiteris.java */
/* Generated By:JavaCC: Do not edit this line. IpsisLiteris.java */
package scanner.compiler.build;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;import scanner.compiler.errors.AnalyserError;import scanner.compiler.errors.ErrorMessage;import scanner.compiler.errors.Log;import scanner.compiler.errors.TypeError;import javax.lang.model.type.ErrorType;

public class IpsisLiteris implements IpsisLiterisConstants {

    public static void main (String[] args) throws ParseException, TokenMgrError {
        IpsisLiteris parser = new IpsisLiteris(System.in);
        parser.Program();
    }

    private void skipUntil(int type) {
        Token t = getToken(1);
        while (t.kind != type) {
            getNextToken();
            t = getToken(1);
        }
    }

//Analisador Sintático
  final public 
void Program() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case HEADER_DEF:{
      jj_consume_token(HEADER_DEF);
      jj_consume_token(LITERAL);
      break;
      }
    default:
      jj_la1[0] = jj_gen;
      ;
    }
    try {
      jj_consume_token(DEF);
      jj_consume_token(OPEN_CURLY);
    } catch (ParseException e) {
Token t = getToken(1);
        Log.getInstance().add(new AnalyserError(getToken(1), ErrorMessage.INVALID_TOKEN, TypeError.PARSER));
    }
    VarDeclaration();
    ProgMain();
    jj_consume_token(CLOSE_CURLY);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case IDENTIFIER:{
      jj_consume_token(IDENTIFIER);
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      ;
    }
}

  final public void VarDeclaration() throws ParseException {
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case DATA_DEF:{
        jj_consume_token(DATA_DEF);
        jj_consume_token(OPEN_CURLY);
        VarField();
        jj_consume_token(CLOSE_CURLY);
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        ;
      }
    } catch (ParseException e) {
Log.getInstance().add(new AnalyserError(getToken(1), ErrorMessage.INVALID_TOKEN, TypeError.PARSER));
        skipUntil(EXE);
    }
}

  final public void VarField() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NOT_VAR:{
      ConstDef();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case VAR:{
        VarDef();
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        ;
      }
      break;
      }
    case VAR:{
      VarDef();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NOT_VAR:{
        ConstDef();
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        ;
      }
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void ConstDef() throws ParseException {
    jj_consume_token(NOT_VAR);
    jj_consume_token(VAR);
    label_1:
    while (true) {
      Type();
      jj_consume_token(IS);
      IdList();
      Constants();
      jj_consume_token(DELIMITER);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NAT:
      case REAL:
      case CHAR:
      case BOOL:{
        ;
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        break label_1;
      }
    }
}

  final public void VarDef() throws ParseException {
    jj_consume_token(VAR);
    label_2:
    while (true) {
      Type();
      jj_consume_token(IS);
      IdList();
      jj_consume_token(DELIMITER);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NAT:
      case REAL:
      case CHAR:
      case BOOL:{
        ;
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        break label_2;
      }
    }
}

  final public void ProgMain() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EXE:{
      jj_consume_token(EXE);
      jj_consume_token(OPEN_CURLY);
      CommandList();
      jj_consume_token(CLOSE_CURLY);
      break;
      }
    default:
      jj_la1[8] = jj_gen;
Log.getInstance().add(new AnalyserError(getToken(1), ErrorMessage.INVALID_TOKEN, TypeError.PARSER));
    }
}

  final public void Command() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case WHILE:{
      While();
      break;
      }
    case LOOP:{
      DoWhile();
      break;
      }
    case GET:{
      Read();
      break;
      }
    case PUT:{
      Print();
      break;
      }
    case IF:{
      Select();
      break;
      }
    case SET:{
      Attribution();
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void Attribution() throws ParseException {
    jj_consume_token(SET);
    Expression();
    jj_consume_token(TO);
    IdList();
}

  final public void Select() throws ParseException {
    jj_consume_token(IF);
    Expression();
    jj_consume_token(IS);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case TRUE:{
      IsTrue();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FALSE:{
        IsFalse();
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        ;
      }
      break;
      }
    case FALSE:{
      IsFalse();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TRUE:{
        IsTrue();
        break;
        }
      default:
        jj_la1[11] = jj_gen;
        ;
      }
      break;
      }
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void IsTrue() throws ParseException {
    jj_consume_token(TRUE);
    jj_consume_token(OPEN_CURLY);
    CommandList();
    jj_consume_token(CLOSE_CURLY);
}

  final public void IsFalse() throws ParseException {
    jj_consume_token(FALSE);
    jj_consume_token(OPEN_CURLY);
    CommandList();
    jj_consume_token(CLOSE_CURLY);
}

  final public void Print() throws ParseException {
    jj_consume_token(PUT);
    jj_consume_token(OPEN_CURLY);
    Value();
    jj_consume_token(CLOSE_CURLY);
}

  final public void Read() throws ParseException {
    jj_consume_token(GET);
    jj_consume_token(OPEN_CURLY);
    IdList();
    jj_consume_token(CLOSE_CURLY);
}

  final public void DoWhile() throws ParseException {
    jj_consume_token(LOOP);
    jj_consume_token(OPEN_CURLY);
    CommandList();
    jj_consume_token(CLOSE_CURLY);
    jj_consume_token(WHILE);
    Expression();
    jj_consume_token(IS);
    jj_consume_token(TRUE);
}

  final public void While() throws ParseException {
    jj_consume_token(WHILE);
    Expression();
    jj_consume_token(IS);
    jj_consume_token(TRUE);
    jj_consume_token(DO);
    jj_consume_token(OPEN_CURLY);
    CommandList();
    jj_consume_token(CLOSE_CURLY);
}

// Sintático Úteis
  final public 
void Constants() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LITERAL:{
      jj_consume_token(LITERAL);
      break;
      }
    case INTEGER:{
      jj_consume_token(INTEGER);
      break;
      }
    case FLOAT:{
      jj_consume_token(FLOAT);
      break;
      }
    case TRUE:{
      jj_consume_token(TRUE);
      break;
      }
    case FALSE:{
      jj_consume_token(FALSE);
      break;
      }
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void Value() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case IDENTIFIER:{
      Id();
      break;
      }
    case TRUE:
    case FALSE:
    case INTEGER:
    case FLOAT:
    case LITERAL:{
      Constants();
      break;
      }
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void Id() throws ParseException {
    jj_consume_token(IDENTIFIER);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case OPEN_SQUARE:{
      jj_consume_token(OPEN_SQUARE);
      jj_consume_token(INTEGER);
      jj_consume_token(CLOSE_SQUARE);
      break;
      }
    default:
      jj_la1[15] = jj_gen;
      ;
    }
}

  final public void Type() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NAT:{
      jj_consume_token(NAT);
      break;
      }
    case REAL:{
      jj_consume_token(REAL);
      break;
      }
    case CHAR:{
      jj_consume_token(CHAR);
      break;
      }
    case BOOL:{
      jj_consume_token(BOOL);
      break;
      }
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void IdList() throws ParseException {
    Id();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case SEPARATOR:{
        ;
        break;
        }
      default:
        jj_la1[17] = jj_gen;
        break label_3;
      }
      jj_consume_token(SEPARATOR);
      Id();
    }
}

  final public void CommandList() throws ParseException {
    label_4:
    while (true) {
      Command();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case DELIMITER:{
        jj_consume_token(DELIMITER);
        break;
        }
      default:
        jj_la1[18] = jj_gen;
Log.getInstance().add(new AnalyserError(getToken(1), ErrorMessage.INVALID_TOKEN, TypeError.PARSER));
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case SET:
      case GET:
      case PUT:
      case WHILE:
      case LOOP:
      case IF:{
        ;
        break;
        }
      default:
        jj_la1[19] = jj_gen;
        break label_4;
      }
    }
}

  final public void Expression() throws ParseException {
    ExpLogicAritmetic();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EQUAL:
    case N_EQUAL:
    case GREATER:
    case LOWER:
    case LOW_EQ:
    case GREAT_EQ:{
      Comparator();
      ExpLogicAritmetic();
      break;
      }
    default:
      jj_la1[20] = jj_gen;
      ;
    }
}

  final public void ExpLogicAritmetic() throws ParseException {
    Element2();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:
      case MINUS:
      case OR:{
        ;
        break;
        }
      default:
        jj_la1[21] = jj_gen;
        break label_5;
      }
      LowPriorityOperator();
      Element2();
    }
}

  final public void Element2() throws ParseException {
    Element1();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TIMES:
      case DIVIDE:
      case INT_DIVIDE:
      case MOD:
      case AND:{
        ;
        break;
        }
      default:
        jj_la1[22] = jj_gen;
        break label_6;
      }
      MediumPriorityOperator();
      Element1();
    }
}

  final public void Element1() throws ParseException {
    Element();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case POWER:{
        ;
        break;
        }
      default:
        jj_la1[23] = jj_gen;
        break label_7;
      }
      jj_consume_token(POWER);
      Element();
    }
}

  final public void Element() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case TRUE:
    case FALSE:
    case IDENTIFIER:
    case INTEGER:
    case FLOAT:
    case LITERAL:{
      Value();
      break;
      }
    case OPEN_PARENT:{
      ParentesisExp();
      break;
      }
    case NOT:{
      jj_consume_token(NOT);
      ParentesisExp();
      break;
      }
    default:
      jj_la1[24] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void ParentesisExp() throws ParseException {
    jj_consume_token(OPEN_PARENT);
    Expression();
    jj_consume_token(CLOSE_PARENT);
}

  final public void Comparator() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EQUAL:{
      jj_consume_token(EQUAL);
      break;
      }
    case N_EQUAL:{
      jj_consume_token(N_EQUAL);
      break;
      }
    case LOWER:{
      jj_consume_token(LOWER);
      break;
      }
    case GREATER:{
      jj_consume_token(GREATER);
      break;
      }
    case LOW_EQ:{
      jj_consume_token(LOW_EQ);
      break;
      }
    case GREAT_EQ:{
      jj_consume_token(GREAT_EQ);
      break;
      }
    default:
      jj_la1[25] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void LowPriorityOperator() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case PLUS:{
      jj_consume_token(PLUS);
      break;
      }
    case MINUS:{
      jj_consume_token(MINUS);
      break;
      }
    case OR:{
      jj_consume_token(OR);
      break;
      }
    default:
      jj_la1[26] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void MediumPriorityOperator() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case TIMES:{
      jj_consume_token(TIMES);
      break;
      }
    case DIVIDE:{
      jj_consume_token(DIVIDE);
      break;
      }
    case INT_DIVIDE:{
      jj_consume_token(INT_DIVIDE);
      break;
      }
    case MOD:{
      jj_consume_token(MOD);
      break;
      }
    case AND:{
      jj_consume_token(AND);
      break;
      }
    default:
      jj_la1[27] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

// Analisador Léxico
  final public 
void Lexic() throws ParseException {
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case DEF:
      case DATA_DEF:
      case IS:
      case EXE:
      case VAR:
      case NOT_VAR:
      case SET:
      case TO:
      case GET:
      case PUT:
      case NAT:
      case REAL:
      case CHAR:
      case BOOL:
      case TRUE:
      case FALSE:
      case WHILE:
      case LOOP:
      case DO:
      case IF:
      case OPEN_CURLY:
      case CLOSE_CURLY:
      case OPEN_PARENT:
      case CLOSE_PARENT:
      case OPEN_SQUARE:
      case CLOSE_SQUARE:
      case EQUAL:
      case N_EQUAL:
      case GREATER:
      case LOWER:
      case LOW_EQ:
      case GREAT_EQ:
      case PLUS:
      case MINUS:
      case TIMES:
      case DIVIDE:
      case POWER:
      case INT_DIVIDE:
      case MOD:
      case AND:
      case OR:
      case NOT:
      case DELIMITER:
      case SEPARATOR:
      case HEADER_DEF:
      case IDENTIFIER:
      case INTEGER:
      case FLOAT:
      case LITERAL:{
        ;
        break;
        }
      default:
        jj_la1[28] = jj_gen;
        break label_8;
      }
      token();
    }
}

  final public void token() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case DEF:{
      jj_consume_token(DEF);
      break;
      }
    case DATA_DEF:{
      jj_consume_token(DATA_DEF);
      break;
      }
    case IS:{
      jj_consume_token(IS);
      break;
      }
    case EXE:{
      jj_consume_token(EXE);
      break;
      }
    case VAR:{
      jj_consume_token(VAR);
      break;
      }
    case NOT_VAR:{
      jj_consume_token(NOT_VAR);
      break;
      }
    case SET:{
      jj_consume_token(SET);
      break;
      }
    case TO:{
      jj_consume_token(TO);
      break;
      }
    case GET:{
      jj_consume_token(GET);
      break;
      }
    case PUT:{
      jj_consume_token(PUT);
      break;
      }
    case NAT:{
      jj_consume_token(NAT);
      break;
      }
    case REAL:{
      jj_consume_token(REAL);
      break;
      }
    case CHAR:{
      jj_consume_token(CHAR);
      break;
      }
    case BOOL:{
      jj_consume_token(BOOL);
      break;
      }
    case TRUE:{
      jj_consume_token(TRUE);
      break;
      }
    case FALSE:{
      jj_consume_token(FALSE);
      break;
      }
    case WHILE:{
      jj_consume_token(WHILE);
      break;
      }
    case LOOP:{
      jj_consume_token(LOOP);
      break;
      }
    case DO:{
      jj_consume_token(DO);
      break;
      }
    case IF:{
      jj_consume_token(IF);
      break;
      }
    case OPEN_CURLY:{
      jj_consume_token(OPEN_CURLY);
      break;
      }
    case CLOSE_CURLY:{
      jj_consume_token(CLOSE_CURLY);
      break;
      }
    case OPEN_PARENT:{
      jj_consume_token(OPEN_PARENT);
      break;
      }
    case CLOSE_PARENT:{
      jj_consume_token(CLOSE_PARENT);
      break;
      }
    case OPEN_SQUARE:{
      jj_consume_token(OPEN_SQUARE);
      break;
      }
    case CLOSE_SQUARE:{
      jj_consume_token(CLOSE_SQUARE);
      break;
      }
    case EQUAL:{
      jj_consume_token(EQUAL);
      break;
      }
    case N_EQUAL:{
      jj_consume_token(N_EQUAL);
      break;
      }
    case GREATER:{
      jj_consume_token(GREATER);
      break;
      }
    case LOWER:{
      jj_consume_token(LOWER);
      break;
      }
    case LOW_EQ:{
      jj_consume_token(LOW_EQ);
      break;
      }
    case GREAT_EQ:{
      jj_consume_token(GREAT_EQ);
      break;
      }
    case PLUS:{
      jj_consume_token(PLUS);
      break;
      }
    case MINUS:{
      jj_consume_token(MINUS);
      break;
      }
    case TIMES:{
      jj_consume_token(TIMES);
      break;
      }
    case DIVIDE:{
      jj_consume_token(DIVIDE);
      break;
      }
    case POWER:{
      jj_consume_token(POWER);
      break;
      }
    case INT_DIVIDE:{
      jj_consume_token(INT_DIVIDE);
      break;
      }
    case MOD:{
      jj_consume_token(MOD);
      break;
      }
    case AND:{
      jj_consume_token(AND);
      break;
      }
    case OR:{
      jj_consume_token(OR);
      break;
      }
    case NOT:{
      jj_consume_token(NOT);
      break;
      }
    case DELIMITER:{
      jj_consume_token(DELIMITER);
      break;
      }
    case SEPARATOR:{
      jj_consume_token(SEPARATOR);
      break;
      }
    case HEADER_DEF:{
      jj_consume_token(HEADER_DEF);
      break;
      }
    case IDENTIFIER:{
      jj_consume_token(IDENTIFIER);
      break;
      }
    case INTEGER:{
      jj_consume_token(INTEGER);
      break;
      }
    case FLOAT:{
      jj_consume_token(FLOAT);
      break;
      }
    case LITERAL:{
      jj_consume_token(LITERAL);
      break;
      }
    default:
      jj_la1[29] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  /** Generated Token Manager. */
  public IpsisLiterisTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[30];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static private int[] jj_la1_2;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	   jj_la1_init_2();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x0,0x0,0x2000,0x10000,0x20000,0x30000,0x3c00000,0x3c00000,0x8000,0xb0340000,0x8000000,0x4000000,0xc000000,0xc000000,0xc000000,0x0,0x3c00000,0x0,0x0,0xb0340000,0x0,0x0,0x0,0x0,0xc000000,0x0,0x0,0x0,0xfffff000,0xfffff000,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x1000000,0x2000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x60000000,0x62000000,0x10,0x0,0x800000,0x400000,0x0,0xfc0,0x103000,0xec000,0x10000,0x62200004,0xfc0,0x103000,0xec000,0x63ffffff,0x63ffffff,};
	}
	private static void jj_la1_init_2() {
	   jj_la1_2 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x2,0x2,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x2,0x0,0x0,0x0,0x2,0x2,};
	}

  /** Constructor with InputStream. */
  public IpsisLiteris(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public IpsisLiteris(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new IpsisLiterisTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public IpsisLiteris(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new IpsisLiterisTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new IpsisLiterisTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public IpsisLiteris(IpsisLiterisTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(IpsisLiterisTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[73];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 30; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		   if ((jj_la1_2[i] & (1<<j)) != 0) {
			 la1tokens[64+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 73; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  private boolean trace_enabled;

/** Trace enabled. */
  final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
