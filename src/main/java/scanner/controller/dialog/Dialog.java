package scanner.controller.dialog;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Optional;

public final class Dialog {

    private final String primaryTitle;

    public Dialog(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public Dialog(){
        this("Compilador - Ipsis Literis");
    }

    public boolean newNoSave(){
        return confirmationYesNo("Arquivo não salvo! Criar novo mesmo assim?", ": Novo");
    }

    public boolean openNoSave(){
        return confirmationYesNo("Arquivo não salvo! Abir outro mesmo assim?", ": Open");
    }

    public boolean exitNoSave(){
        return confirmationYesNo("Deseja sair sem salvar?",": Sair");
    }

    public String filePicker(File openDirectory) {
        return fileChooser(openDirectory, ChooserType.OPEN);
    }

    public String fileSaver(File openDirectory){
        return fileChooser(openDirectory, ChooserType.SAVE);
    }

    public void saveError(){
        errorAlert("Erro ao Salvar o arquivo", ": Erro ao salvar");
    }

    public void openError(){
        errorAlert("Erro ao abrir o arquivo", ": Erro ao abrir");
    }

    public void compileError(){
        errorAlert("Você deve salvar o arquivo antes", ": Erro ao compilar");
    }

    private String fileChooser(File openDirectory, ChooserType type){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(openDirectory);
        fileChooser.setTitle(primaryTitle+type.getTitle());
        fileChooser.getExtensionFilters().addAll(type.getExtensionFilters());
        File selectedFile = type.execute(fileChooser);
        if (selectedFile != null) {
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    private boolean confirmationYesNo(String msg, String title) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setHeaderText(null);
        dialog.getButtonTypes().clear();
        ButtonType yes = new ButtonType("Sim", ButtonType.YES.getButtonData());
        ButtonType no = new ButtonType("Não", ButtonType.NO.getButtonData());
        dialog.getButtonTypes().addAll(yes, no);
        dialog.setContentText(msg);
        dialog.setTitle(primaryTitle+title);
        Optional<ButtonType> option = dialog.showAndWait();
        return option.get().equals(dialog.getButtonTypes().get(0));
    }

    private void errorAlert(String msg, String title) {
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setHeaderText(null);
        dialog.setContentText(msg);
        dialog.setTitle(primaryTitle+title);
        Optional<ButtonType> option = dialog.showAndWait();
    }

    private interface ChooserTypeExecute{
        File execute(FileChooser fileChooser);
    }

    private enum ChooserType implements ChooserTypeExecute{
        SAVE(": Salvar arquivo",
                new FileChooser.ExtensionFilter("Ipsis Literis File", "*.ipsli", "*,il"),
                new FileChooser.ExtensionFilter("Text File", "*.txt")
        ){
            @Override
            public File execute(FileChooser fileChooser) {
                return fileChooser.showSaveDialog(new Alert(Alert.AlertType.CONFIRMATION).getOwner());
            }
        },
        OPEN(": Abrir arquivo",
                new FileChooser.ExtensionFilter("Ipsis Literis File", "*.ipsli"),
                new FileChooser.ExtensionFilter("Text File", "*.txt")
        ){
            @Override
            public File execute(FileChooser fileChooser) {
                return fileChooser.showOpenDialog(new Alert(Alert.AlertType.CONFIRMATION).getOwner());
            }
        };

        private final String title;

        private final FileChooser.ExtensionFilter[] extensionFilters;

        ChooserType(String title, FileChooser.ExtensionFilter... extensionFilter) {
            this.title = title;
            this.extensionFilters = extensionFilter;
        }

        public String getTitle() {
            return title;
        }

        public FileChooser.ExtensionFilter[] getExtensionFilters() {
            return extensionFilters;
        }
    }
}
