package scanner.controller.hotBar;

import scanner.controller.AbstractController;

import scanner.controller.topBar.TopBarController;
import javafx.fxml.FXML;

public class HotBarController extends AbstractController {
    @FXML
    public void onClickNew(){
        getTopBarController().newFile();
    }

    @FXML
    public void onClickOpen(){
        getTopBarController().openFile();
    }

    @FXML
    public void onClickSave(){
        getTopBarController().saveFile();
    }

    @FXML
    public void onClickCut(){
        getTopBarController().cutEdit();
    }

    @FXML
    public void onClickCopy(){
        getTopBarController().copyEdit();
    }

    @FXML
    public void onClickPaste(){
        getTopBarController().pasteEdit();
    }

    @FXML
    public void onClickCompile(){
        getTopBarController().compile();
    }

    @FXML
    public void onClickExecute(){
        getTopBarController().execute();
    }

    private TopBarController getTopBarController(){
        return (TopBarController) getControllerBrother("TopBar");
    }
}
