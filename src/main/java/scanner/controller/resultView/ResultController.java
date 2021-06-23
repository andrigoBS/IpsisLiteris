package scanner.controller.resultView;

import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import org.fxmisc.richtext.CodeArea;
import scanner.controller.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultController extends AbstractController implements Initializable {
    @FXML
    private CodeArea codeArea;

    @FXML
    private Label rowColView;

    private int inputPosition;

    private String backupText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        codeArea.setEditable(false);
        codeArea.caretPositionProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue < inputPosition){
                codeArea.displaceCaret(codeArea.getText().length());
            }
        }));
        codeArea.setOnKeyPressed(keyEvent -> {
            if(codeArea.isEditable()){
                if(keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                    if (codeArea.getCaretPosition() >= inputPosition) {
                        backupText = backupText.substring(0, codeArea.getText().length());
                    }
                }else if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                    codeArea.setEditable(false);
                    backupText += keyEvent.getText();
                }else if(!(keyEvent.getCode().isMediaKey() || keyEvent.getCode().isFunctionKey() || keyEvent.getCode().isNavigationKey() || keyEvent.getCode().isArrowKey() || keyEvent.getCode().isModifierKey())){
                    if (codeArea.getCaretPosition() >= inputPosition) {
                        char character = keyEvent.getText().charAt(0);
                        if(keyEvent.isShiftDown()){
                            character = Character.isLowerCase(character) ? Character.toUpperCase(character) : Character.toLowerCase(character);
                        }
                        backupText += character;
                    }
                }
            }
        });
        codeArea.setOnKeyReleased(keyEvent -> {
            if (codeArea.isEditable()) {
                codeArea.replaceText(backupText);
            }
        });
//        setText("console\nlogsom\n"); //teste console
//        enableInput(); //teste console
    }

    public void enableInput(){
        backupText = codeArea.getText();
        inputPosition = backupText.length();
        codeArea.setEditable(true);
        codeArea.displaceCaret(inputPosition);
    }

    public void setText(String text){
        clear();
        codeArea.replaceText(text);
    }

    public void clear(){
        codeArea.clear();
    }

    public void setRowCol(int row, int col){
        rowColView.setText(" Linha: "+row+",  Coluna: "+col);
    }
}
