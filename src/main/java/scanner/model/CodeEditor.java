package scanner.model;

import scanner.compiler.build.IpsisLiteris;
import scanner.compiler.build.ParseException;
import scanner.compiler.virtualMachine.IdEst;
import scanner.model.dto.InstructionRowDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CodeEditor {
    private FileManager fileManager;

    private ArrayList<InstructionRowDTO> instructions;

    private boolean isRunning;

    public CodeEditor(){
        fileManager = new FileManager();
        instructions = new ArrayList<>();
        //TODO: remover antes de entregar
        try {
            instructions = new ArrayList<>(IdEst.builder().build().getInstructions(new FileInputStream("src/jar/files/test.ie")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
    }

    public String open(String path) throws IOException {
        fileManager.load(path);
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

    public String compile(){
        try {
            return IpsisLiteris.compile(fileManager.getFileInputStream());
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public void compile(Consumer<String> print){
//        try {
//            IpsisLiteris.compile(fileManager.getFileInputStream(), print);
//        } catch (ParseException | IOException e) {
//            e.printStackTrace();
//            print.accept(e.getMessage());
//        }
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
