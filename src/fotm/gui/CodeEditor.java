package fotm.gui;

import java.net.URL;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import netscape.javascript.JSException;

public class CodeEditor extends StackPane {

    /**
     * a webview used to encapsulate the CodeMirror JavaScript.
     */
    final WebView webview = new WebView();
    final WebEngine webEngine = webview.getEngine();

    /**
     * a snapshot of the code to be edited kept for easy initilization and
     * reversion of editable code.
     */
    private String editingCode;

    /**
     * a template for editing code - this can be changed to any template derived
     * from the supported modes at http://codemirror.net to allow syntax
     * highlighted editing of a wide variety of languages.
     */
    private final String editingTemplate
            = "<!doctype html>"
            + "<html>"
            + "<head>"
            + "  <link rel=\"stylesheet\" href='codemirror.css'>"
            + "  <script src='codemirror.js'></script>"
            + "  <script src='css.js'></script>"
            + "<script language='javascript'>"
            + " function getHeight() { "
            + "   var e = document.getElementById('mydiv');  "
            + "   return e.offsetHeight;"
            + " }"
            + "function setHeight(h) {"
            + "document.getElementById('mydiv').height = h;"
            + "}"
            + " </script>"
            + "</head>"
            + "<body>"
            + "<div id='mydiv'>"
            + "<form><textarea id=\"code\" name=\"code\">\n"
            + "${code}"
            + "</textarea></form>"
            + "<script>"
            + "  var editor = CodeMirror.fromTextArea(document.getElementById(\"code\"), {"
            + "    lineNumbers: true,"
            + "    matchBrackets: true,"
            + "    mode: \"text/css\""
            + "  });"
            + "</script>"
            + "</div>"
            + "</body>"
            + "</html>";

    private String applyEditingTemplate() {
        String str = editingTemplate.replace("href='codemirror.css'",
                "href='" + getClass().getResource("codemirror/codemirror.css") + "'");

        str = str.replace("src='codemirror.js'",
                "src='" + getClass().getResource("codemirror/codemirror.js") + "'");

        str = str.replace("src='css.js'",
                "src='" + getClass().getResource("codemirror/css.js") + "'");

        return str.replace("${code}", editingCode);
    }

    /**
     * sets the current code in the editor and creates an editing snapshot of
     * the code which can be reverted to.
     */
    public void setCode(String newCode) {
        this.editingCode = newCode;
        webview.getEngine().loadContent(applyEditingTemplate());
    }

    /**
     * returns the current code in the editor and updates an editing snapshot of
     * the code which can be reverted to.
     */
    public String getCodeAndSnapshot() {
        this.editingCode = (String) webview.getEngine().executeScript("editor.getValue();");
        return editingCode;
    }

    /**
     * revert edits of the code to the last edit snapshot taken.
     */
    public void revertEdits() {
        setCode(editingCode);
    }

    /**
     * Create a new code editor.
     *
     * @param editingCode the initial code to be edited in the code editor.
     */
    CodeEditor(String editingCode) {
        this.editingCode = editingCode;

        //webview.setPrefSize(650, 325);
        //webview.setMinSize(650, 325);
        //webview.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //webview.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        webview.getEngine().loadContent(applyEditingTemplate());

        this.getChildren().add(webview);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                adjustHeight();
            };
        });
        
        widthProperty().addListener((ObservableValue<?> observable, Object oldValue, Object newValue) -> {
            Double width1 = (Double) newValue;
            webview.setPrefWidth(width1);
            webview.setMinWidth(width1);
            //adjustHeight();
        });

        webview.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue<? extends State> arg0, State oldState, State newState) {
                if (newState == State.SUCCEEDED) {
                    webview.setPrefWidth(getWidth());
                    webview.setMinWidth(getWidth());
                    adjustHeight();
                }
            }
        });
    }

    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(webview, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
    }

    private void adjustHeight() {
        Platform.runLater(() -> {
            try {
                webEngine.executeScript("setHeight(" + getHeight() + ")");
                /*Object result = webEngine.executeScript(
                        "getHeight()");
                if (result instanceof Integer) {
                    Integer i = (Integer) result;
                    double height1 = new Double(i);
                    height1 = height1 + 20;
                    webview.setPrefHeight(height1);
                    System.out.println("height on state: " + height1 + " prefh: " + webview.getPrefHeight());
                }*/
            }catch (JSException e) {
                // not important
                System.out.println(e.getMessage());
            }
        });
    }
}
