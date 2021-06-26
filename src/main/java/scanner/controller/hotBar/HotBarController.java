package scanner.controller.hotBar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import scanner.controller.AbstractController;
import scanner.controller.topBar.TopBarController;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;


public class HotBarController extends AbstractController implements Initializable {
    @FXML
    private ImageView newFile;

    @FXML
    private ImageView openFile;

    @FXML
    private ImageView saveFile;

    @FXML
    private ImageView cutEdit;

    @FXML
    private ImageView copyEdit;

    @FXML
    private ImageView pasteEdit;

    @FXML
    private ImageView compile;

    @FXML
    private ImageView execute;

    @FXML
    private ImageView objectCode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Map<String, ImageView> map = Map.of(
                "Novo Arquivo", newFile,
                "Abri Arquivo", openFile,
                "Salvar Arquivo", saveFile,
                "Cortar", cutEdit,
                "Copiar", copyEdit,
                "Colar", pasteEdit,
                "Compilar", compile,
                "Executar", execute,
                "Ver o código objeto gerado", objectCode
        );
        map.forEach((key, value) -> setTooltip(value, key));
    }

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

    @FXML
    public void onClickObjectCode(){ getTopBarController().openObjectCode(); }
    
    private void setTooltip(Node node, String title){
        Tooltip tooltip = new Tooltip(title);
        Tooltip.install(node, tooltip);
        tooltip.setShowDelay(Duration.millis(100));
    }

    private TopBarController getTopBarController(){
        return (TopBarController) getControllerBrother("TopBar");
    }
}
