package scanner.compiler.errors;

import java.util.ArrayList;
import java.util.stream.Collectors;

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

    @Override
    public String toString() {
        return errors.stream().map(AnalyserError::toString).collect(Collectors.joining("\n"));
    }
}
