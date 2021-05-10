package scanner.model;

import scanner.compiler.build.IpsisLiteris;
import scanner.compiler.build.ParseException;
import java.io.*;

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

    public String open(String path) throws IOException, ClassNotFoundException {
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

    public boolean hasFileName(){
        return fileManager.hasFileName();
    }

    public String compile(){
        try {
            IpsisLiteris parser = new IpsisLiteris(fileManager.getFileInputStream());
            return parser.getResult();
        } catch (ParseException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public void execute(){

    }
}
