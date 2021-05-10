package scanner.controller.scrollEditor;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;
import scanner.compiler.errors.TokenType;
import scanner.controller.AbstractController;
import scanner.controller.resultView.ResultController;

import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScrollEditorController extends AbstractController implements Initializable {
    private static final ArrayList<String> REGEX_LIST = TokenType.getIDERegexList();
    private static final String IMPORTANT_PATTERN = "\\b(" + REGEX_LIST.get(0) + ")\\b";
    private static final String KEYWORD_PATTERN = "\\b(" + REGEX_LIST.get(1) + ")\\b";
    private static final String PAREN_PATTERN = "(" + REGEX_LIST.get(2) + ")";
    private static final String BRACE_PATTERN = "(" + REGEX_LIST.get(3) + ")";
    private static final String BRACKET_PATTERN = "(" + REGEX_LIST.get(4) + ")";
    private static final String SEMICOLON_PATTERN = "(" + REGEX_LIST.get(5) + ")";
    private static final String SPECIAL_PATTERN = "\\b(" + REGEX_LIST.get(6) + ")\\b";
    private static final String STRING_PATTERN = "\"[-a-zA-Z0-9._]*\"";
    //TODO: REGEX PROVISORIO MELHORAR ISSO. TALVEZ USAR O LEXICO!!!!!!!!!!!!!!!!!!!!!!!

    private static final Pattern PATTERN = Pattern.compile(
            "(?<IMPORTANT>" + IMPORTANT_PATTERN + ")" +
            "|(?<KEYWORD>" + KEYWORD_PATTERN + ")" +
            "|(?<PAREN>" + PAREN_PATTERN + ")" +
            "|(?<BRACE>" + BRACE_PATTERN + ")" +
            "|(?<BRACKET>" + BRACKET_PATTERN + ")" +
            "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")" +
            "|(?<STRING>" + STRING_PATTERN + ")" +
            "|(?<SPECIAL>" + SPECIAL_PATTERN + ")"
    );

    private ExecutorService executor;

    @FXML
    private CodeArea writer;

    @FXML
    public void onTextChange(){
        getCodeEditor().updateText(writer.getText());
    }

    @Override
    public void stop(){
        executor.shutdown();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        writer.setParagraphGraphicFactory(LineNumberFactory.get(writer));
        executor = Executors.newSingleThreadExecutor();
        writer.setParagraphGraphicFactory(LineNumberFactory.get(writer));
        Subscription cleanupWhenDone = writer.multiPlainChanges()
                .successionEnds(Duration.ofMillis(500))
                .supplyTask(this::computeHighlightingAsync)
                .awaitLatest(writer.multiPlainChanges())
                .filterMap(t -> {
                    if(t.isSuccess()) {
                        return Optional.of(t.get());
                    } else {
                        t.getFailure().printStackTrace();
                        return Optional.empty();
                    }
                })
                .subscribe(this::applyHighlighting);
    }

    private Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
        String text = writer.getText();
        Task<StyleSpans<Collection<String>>> task = new Task<StyleSpans<Collection<String>>>() {
            @Override
            protected StyleSpans<Collection<String>> call() throws Exception {
                return computeHighlighting(text);
            }
        };
        executor.execute(task);
        return task;
    }

    private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
        writer.setStyleSpans(0, highlighting);
    }

    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass =
                    matcher.group("IMPORTANT") != null ? "important" :
                            matcher.group("KEYWORD") != null ? "keyword" :
                                    matcher.group("PAREN") != null ? "paren" :
                                            matcher.group("BRACE") != null ? "brace" :
                                                    matcher.group("BRACKET") != null ? "bracket" :
                                                            matcher.group("SEMICOLON") != null ? "semicolon" :
                                                                    matcher.group("STRING") != null ? "string" :
                                                                            matcher.group("SPECIAL") != null ? "special" :
                                                                                    null; /* never happens */ assert styleClass != null;
            //TODO: Melhorar esses ternarios copiado da net :(
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

    @FXML
    public void updateTextCursor(){
        int position = writer.getCaretPosition();
        String textUtilPosition = writer.getText().substring(0, position);
        int row = countLines(textUtilPosition) + 1;
        int col = position - textUtilPosition.lastIndexOf("\n");
        getResultController().setRowCol(row, col);
    }

    public void copy(){
        writer.copy();
    }

    public void paste(){
        writer.paste();
    }

    public void cut(){
        writer.cut();
    }

    public void loadText(String text){
        writer.replaceText(text);
    }

    private int countLines(String text){
        return (int) text.chars().filter(ch -> ch == '\n').count();
    }

    private ResultController getResultController(){
        return (ResultController) getControllerBrother("Result");
    }
}
