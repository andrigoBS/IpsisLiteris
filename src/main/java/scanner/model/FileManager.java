package scanner.model;

import java.io.*;

public class FileManager {
    public static final String DEFAULT_FILE_NAME = "../main.ipsli";

    public static final String DEFAULT_DIRECTORY = "../";

    private String fileName;

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
    }

    public File getCurrentDirectory(){
        String directory = fileName == null?
                           DEFAULT_DIRECTORY :
                           fileName.substring(0, fileName.lastIndexOf("/"));
        return new File(directory);
    }

    public InputStream getFileInputStream() throws FileNotFoundException {
        return new FileInputStream(fileName);
    }

    public boolean hasFileName(){
        return fileName != null;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        isSaved = false;
    }
}