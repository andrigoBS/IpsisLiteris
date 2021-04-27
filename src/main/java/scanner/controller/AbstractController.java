package scanner.controller;

import scanner.model.CodeEditor;

import java.util.HashMap;

public abstract class AbstractController {
    private static final HashMap<String, AbstractController> CHILDREN = new HashMap<>();

    private static final CodeEditor CODE_EDITOR = new CodeEditor();

    public AbstractController() {
        CHILDREN.put(this.getClass().getSimpleName(), this);
    }

    protected AbstractController getControllerBrother(String brotherName) {
        return CHILDREN.get(brotherName+"Controller");
    }

    protected CodeEditor getCodeEditor() {
        return CODE_EDITOR;
    }
}
