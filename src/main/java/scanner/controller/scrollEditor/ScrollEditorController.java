package scanner.controller.scrollEditor;

import scanner.controller.AbstractController;
import scanner.controller.resultView.ResultController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.util.ResourceBundle;

public class ScrollEditorController extends AbstractController implements Initializable {
    @FXML
    private TextArea writer;

    @FXML
    private TextArea counter;

    @FXML
    public void onTextChange(){
        getCodeEditor().updateText(writer.getText());
        updateCounter();
    }

    @FXML
    public void updateCounterScrollBar(){
        counter.setScrollTop(writer.getScrollTop());
    }

    @FXML
    public void updateWriterScrollBar(){
        writer.setScrollTop(counter.getScrollTop());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        counter.setText("1");
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
        writer.setText(text);
        updateCounter();
    }

    private void updateCounter(){
        int lines = countLines(writer.getText());
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < lines; i++) {
            text.append(i + 1).append("\n");
        }
        text.append(lines + 1);
        counter.setText(text.toString());
    }

    private int countLines(String text){
        return (int) text.chars().filter(ch -> ch == '\n').count();
    }

    private ResultController getResultController(){
        return (ResultController) getControllerBrother("Result");
    }
}
