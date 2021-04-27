package scanner.controller.topBar;

import scanner.controller.AbstractController;
import scanner.controller.dialog.Dialog;
import scanner.controller.resultView.ResultController;
import scanner.controller.scrollEditor.ScrollEditorController;
import javafx.application.Platform;

public class TopBarController extends AbstractController{
    private final Dialog dialog = new Dialog();

    public void newFile(){
        if(getCodeEditor().isSaved() || dialog.newNoSave()){
            getCodeEditor().newFile();
            getScrollEditorController().loadText("");
        }
    }

    public void openFile(){
        if(getCodeEditor().isSaved() || dialog.openNoSave()){
            try {
                String path = dialog.filePicker(getCodeEditor().getCurrentDirectory());
                if(path != null){
                    String text = getCodeEditor().open(path);
                    getScrollEditorController().loadText(text);
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
            }else{
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
        if(getCodeEditor().isSaved()){
            String result = getCodeEditor().compile();
            getResultController().setText(result);
        }else{
            dialog.compileError();
        }
    }

    public void execute(){
        getCodeEditor().execute();
    }

    private ScrollEditorController getScrollEditorController(){
        return (ScrollEditorController) getControllerBrother("ScrollEditor");
    }

    private ResultController getResultController(){
        return (ResultController) getControllerBrother("Result");
    }
}
