package scanner.controller.topBar;

import javafx.application.Platform;
import scanner.compiler.virtualMachine.IdEst;
import scanner.controller.AbstractController;
import scanner.controller.Main;
import scanner.controller.dialog.Dialog;
import scanner.controller.resultView.ResultController;
import scanner.controller.scrollEditor.ScrollEditorController;

import java.io.IOException;

public class TopBarController extends AbstractController{
    private final Dialog dialog = new Dialog();

    private final IdEst idEst;

    public TopBarController() {
        idEst = getCodeEditor()
                .getRunner()
                .onError(errorMessage -> getResultController().printError(errorMessage))
                .onRead(() -> getResultController().getInputText())
                .onWrite(result -> getResultController().printText(result))
                .build();
    }

    public void newFile(){
        if(getCodeEditor().isSaved() || dialog.newNoSave()){
            getCodeEditor().newFile();
            getScrollEditorController().loadText("");
        }
        getResultController().clear();
        Main.resetFileTile();
    }

    public void openFile(){
        if(getCodeEditor().isSaved() || dialog.openNoSave()){
            try {
                String path = dialog.filePicker(getCodeEditor().getCurrentDirectory());
                if(path != null){
                    String text = getCodeEditor().open(path);
                    getScrollEditorController().loadText(text);
                    getResultController().clear();
                    Main.setFileTitle(getCodeEditor().getFileOnlyName());
                }
            } catch (Exception e) {
                e.printStackTrace();
                dialog.openError();
            }
        }
    }

    public void saveFile() {
        try {
            if(getCodeEditor().hasFileName()){
                getCodeEditor().save();
                Main.setFileTitle(getCodeEditor().getFileOnlyName());
                dialog.savedAlert();
            }else {
                saveAsFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
            dialog.saveError();
        }
    }

    public void saveAsFile(){
        try {
            String path = dialog.fileSaver(getCodeEditor().getCurrentDirectory());
            if(path != null){
                getCodeEditor().save(path);
                Main.setFileTitle(getCodeEditor().getFileOnlyName());
                dialog.savedAlert();
            }
        } catch (Exception e) {
            e.printStackTrace();
            dialog.saveError();
        }
    }

    public void exit(){
        if(getCodeEditor().isSaved() || dialog.exitNoSave()){
            Platform.exit();
        }
    }

    public void copyEdit(){
        getScrollEditorController().copy();
    }

    public void pasteEdit(){
        getScrollEditorController().paste();
    }

    public void cutEdit(){
        getScrollEditorController().cut();
    }

    public void compile(){
        if(!getCodeEditor().isClear()){
            if(getCodeEditor().hasFileName()){
                try {
                    getCodeEditor().save();
                } catch (IOException e) {
                    e.printStackTrace();
                    dialog.saveError();
                }
            }
            getCodeEditor().compile(error -> getResultController().printError(error));
        }
    }

    public void execute(){
        if(!getCodeEditor().isCompiled()){
            compile();
        }getResultController().clear();
        getCodeEditor().execute(idEst);
    }

    public void openObjectCode(){
        dialog.objectCodeTable(getCodeEditor().getObjectCodeTable());
    }

    @Override
    public void stop() {
        exit();
    }

    private ScrollEditorController getScrollEditorController(){
        return (ScrollEditorController) getControllerBrother("ScrollEditor");
    }

    private ResultController getResultController(){
        return (ResultController) getControllerBrother("Result");
    }
}
