package scanner.controller.scrollEditor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import scanner.controller.AbstractController;
import scanner.controller.resultView.ResultController;

import java.net.URL;
import java.util.ResourceBundle;

public class ScrollEditorController extends AbstractController implements Initializable {
    @FXML
    private CodeArea writer;

    @FXML
    public void onTextChange(){
        getCodeEditor().updateText(writer.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        writer.setParagraphGraphicFactory(LineNumberFactory.get(writer));
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
