package scanner.compiler.errors;

import lombok.Getter;
import scanner.compiler.build.IpsisLiterisConstants;
import scanner.compiler.build.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private final ErrorMessage errorMsg;
    @Getter
    private final ArrayList<TokenType> tokensExpected;

    public AnalyserError (Token t, ErrorMessage errorMsg, List<Integer> tokensKindsExpected) {
        token = t.image;
        line = t.beginLine;
        column = t.beginColumn;
        tokenType = TokenType.parseToken(IpsisLiterisConstants.tokenImage[t.kind]);
        this.errorMsg = errorMsg;
        id = t.kind;
        this.tokensExpected = mapTokensExpected(tokensKindsExpected);
    }

    public AnalyserError (Token t, ErrorMessage errorMsg) {
        this(t, errorMsg, null);
    }

    @Override
    public String toString() {
        return errorMsg.applyValues(token, line, column, tokensExpected);
    }

    private ArrayList<TokenType> mapTokensExpected(List<Integer> kinds){
        if(kinds == null) return null;
        return (ArrayList<TokenType>) kinds.stream().map(
                kind -> TokenType.parseToken(IpsisLiterisConstants.tokenImage[kind])
        ).collect(Collectors.toList());
    }
}
