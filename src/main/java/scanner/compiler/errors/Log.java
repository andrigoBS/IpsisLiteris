package scanner.compiler.errors;

import java.util.ArrayList;

public class Log {
    private static Log INSTANCE = null;

    private ArrayList<AnalyserError> errors;

    public Log() {
        errors = new ArrayList<>();
    }

    public static Log getInstance() {
        if(INSTANCE == null){
            INSTANCE = new Log();
        }
        return INSTANCE;
    }

    public void add(AnalyserError analyserError){
        errors.add(analyserError);
    }
}
