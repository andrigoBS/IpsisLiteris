package scanner.model;

import scanner.compiler.build.IpsisLiteris;
import scanner.compiler.virtualMachine.IdEst;
import scanner.model.dto.InstructionRowDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class CodeEditor {
    private FileManager fileManager;

    private ArrayList<InstructionRowDTO> instructions;

    private boolean isRunning;

    public CodeEditor(){
        fileManager = new FileManager();
        instructions = new ArrayList<>();
    }

    public void updateText(String text){
        fileManager.setText(text);
    }

    public File getCurrentDirectory(){
        return fileManager.getCurrentDirectory();
    }

    public String getFileOnlyName(){
        return fileManager.getFileOnlyName();
    }

    public void newFile(){
        fileManager.newFile();
        instructions = new ArrayList<>();
    }

    public String open(String path) throws IOException {
        fileManager.load(path);
        instructions = new ArrayList<>();
        return fileManager.getText();
    }

    public void save(String text) throws IOException {
        fileManager.save(text);
    }

    public void save() throws IOException {
        fileManager.save();
    }

    public boolean isSaved(){
        return fileManager.isSaved();
    }

    public boolean isClear() {
        return fileManager.isClear();
    }

    public boolean hasFileName(){
        return fileManager.hasFileName();
    }

    public boolean isCompiled(){
        return !instructions.isEmpty();
    }

    public void compile(Consumer<String> printError) {
        instructions = new ArrayList<>(IpsisLiteris.compile(fileManager.getFileInputStream(), printError));
    }

    public IdEst.IdEstBuilder getRunner(){
        return IdEst.builder();
    }

    public void execute(IdEst idEst){
        if(isRunning) return;
        isRunning = true;
        idEst.run(instructions);
        isRunning = false;
    }

    public ArrayList<InstructionRowDTO> getObjectCodeTable(){
        return instructions;
    }
}
