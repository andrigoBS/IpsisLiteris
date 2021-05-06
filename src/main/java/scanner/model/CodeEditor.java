package scanner.model;

import scanner.compiler.build.IpsisLiteris;
import scanner.compiler.build.ParseException;

import java.io.*;
import java.util.List;

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
            List<IpsisLiteris.AnalyserResult> result = parser.Start();
            StringBuilder message = new StringBuilder();
            for (IpsisLiteris.AnalyserResult analyser : result) {
                if(analyser.error){
                    message.append("Erro léxico ")
                           .append(analyser.errorMsg)
                           .append(", Token ")
                           .append(analyser.token)
                           .append(" na linha ")
                           .append(analyser.line)
                           .append(" e coluna ")
                           .append(analyser.column)
                           .append("\n");
                }else {
                    message.append("Aceito ")
                           .append("Token ")
                           .append(analyser.token)
                           .append(" na linha ")
                           .append(analyser.line)
                           .append(" e coluna ")
                           .append(analyser.column)
                           .append(" do tipo ")
                           .append(analyser.type)
                           .append(" com id ")
                           .append(analyser.id)
                           .append("\n");
                }

            }
            return message.toString();
        } catch (ParseException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public void execute(){

    }
}
