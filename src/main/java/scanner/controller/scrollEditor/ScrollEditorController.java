package scanner.controller.scrollEditor;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;
import scanner.compiler.validator.ColorRegex;
import scanner.controller.AbstractController;
import scanner.controller.resultView.ResultController;

import java.net.URL;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;

public class ScrollEditorController extends AbstractController implements Initializable {
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
        executor = Executors.newSingleThreadExecutor();
        writer.setParagraphGraphicFactory(LineNumberFactory.get(writer));
        writer.multiPlainChanges()
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
                .subscribe(highlighting -> writer.setStyleSpans(0, highlighting));
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

    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = ColorRegex.getMatcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass = ColorRegex.getCss(matcher.group().toLowerCase());

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
