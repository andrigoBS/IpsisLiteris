package scanner.compiler.errors;

import lombok.Getter;
import scanner.compiler.build.IpsisLiterisConstants;
import scanner.compiler.build.Token;

public class AnalyserError {
    @Getter
    private final String token;
    @Getter
    private final int line;
    @Getter
    private final int column;
    @Getter
    private final int id;
    @Getter
    private final TokenType tokenType;
    @Getter
    private final TypeError type;
    @Getter
    private final ErrorMessage errorMsg;

    public AnalyserError (Token t, ErrorMessage errorMsg, TypeError type) {
        token = t.image;
        line = t.beginLine;
        column = t.beginColumn;
        tokenType = TokenType.parseToken(IpsisLiterisConstants.tokenImage[t.kind]);
        this.type = type;
        this.errorMsg = errorMsg;
        id = t.kind;
    }

    @Override
    public String toString() {
        return errorMsg + " at line: " + line + " at column: " + column;
    }
}
