package scanner.model;

import scanner.compiler.build.IpsisLiteris;
import scanner.compiler.build.ParseException;
import java.io.*;
import java.util.Stack;

public class CodeEditor {
    private FileManager fileManager;

    public CodeEditor(){
        fileManager = new FileManager();
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

    public String compile(){
        try {
            return IpsisLiteris.compile(fileManager.getFileInputStream());
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public void execute(){

    }
}
