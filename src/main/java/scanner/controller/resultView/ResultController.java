package scanner.controller.resultView;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import scanner.compiler.errors.ErrorMessage;
import scanner.controller.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class ResultController extends AbstractController implements Initializable {
    @FXML
    private CodeArea codeArea;

    @FXML
    private TextField inputTextField;

    @FXML
    private Label rowColView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inputTextField.setDisable(true);

        inputTextField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                Platform.exitNestedEventLoop("AWAIT_ENTER", null);
            }
        });
    }

    public String getInputText(){
        inputTextField.setDisable(false);
        Platform.enterNestedEventLoop("AWAIT_ENTER");
        inputTextField.setDisable(true);
        String text = inputTextField.getText();
        inputTextField.clear();
        codeArea.replaceText(codeArea.getText()+text+"\n");
        return text;
    }

    public void setText(String text){
        clear();
        codeArea.replaceText(text);
    }

    public void printText(String text){
        codeArea.replaceText(codeArea.getText()+text+"\n");
    }

    public void printError(ErrorMessage errorMessage){
        codeArea.replaceText(codeArea.getText()+errorMessage.getText()+"\n");
    }

    public void clear(){
        codeArea.clear();
    }

    public void setRowCol(int row, int col){
        rowColView.setText(" Linha: "+row+",  Coluna: "+col);
    }
}
