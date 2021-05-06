package scanner.controller.resultView;

import scanner.controller.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ResultController extends AbstractController {
    @FXML
    private TextArea textView;

    @FXML
    private Label rowColView;

    public void setText(String text){
        textView.clear();
        textView.setText(text);
    }

    public void setRowCol(int row, int col){
        rowColView.setText(" Linha: "+row+",  Coluna: "+col);
    }
}
