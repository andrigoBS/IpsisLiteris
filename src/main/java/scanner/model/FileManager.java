package scanner.model;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManager {
    //private String currentPath = "./any.ipsli/";

    private String currentPath = "./src/test/files/any.ipsli";

    private String fileName;

    @Getter @Setter
    private String text;

    private boolean isSaved;

    public FileManager() {
        newFile();
    }

    public void newFile(){
        fileName = null;
        text = "";
        isSaved = false;
    }

    public void save(String fileName) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
        out.write(text);
        out.close();
        isSaved = true;
        this.fileName = fileName;
        this.currentPath = fileName;
    }

    public void save() throws IOException{
        save(fileName);
    }

    public void load(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line).append("\n");
        }
        reader.close();
        text = builder.toString();
        isSaved = true;
        this.fileName = fileName;
        this.currentPath = fileName;
    }

    public File getCurrentDirectory(){
        return new File(currentPath).getParentFile();
    }

    public String getFileOnlyName(){
        return new File(fileName).getName();
    }

    public InputStream getFileInputStream() {
        return new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
    }

    public boolean hasFileName(){
        return fileName != null;
    }

    public boolean isSaved() {
        return isSaved || text.trim().isEmpty();
    }
}