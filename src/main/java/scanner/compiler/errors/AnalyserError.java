package scanner.compiler.errors;

import scanner.compiler.build.IpsisLiterisConstants;
import scanner.compiler.build.Token;

public class AnalyserError {
    private final String token;
    private final int line;
    private final int column;
    private final int id;
    private final TokenType tokenType;
    private final TypeError type;
    private final ErrorMessage errorMsg;

    public AnalyserError (Token t, ErrorMessage errorMsg, TypeError type) {
        token = t.image;
        line = t.beginLine;
        column = t.beginColumn;
        tokenType = TokenType.valueOf(IpsisLiterisConstants.tokenImage[t.kind].replace("<", "")
                                                                              .replace(">", ""));
        this.type = type;
        this.errorMsg = errorMsg;
        id = t.kind;
    }

    @Override
    public String toString() {
        return errorMsg + " at line: " + line + " at column: " + column;
    }
}
