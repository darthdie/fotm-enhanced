
package fotm.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class EditCSSDialogController implements Initializable {
    private CodeEditor codeArea;
    
    @FXML Button okButton;
    @FXML Button cancelButton;
    @FXML BorderPane borderPane;
    @FXML Pane codePane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        codeArea = new CodeEditor("");
        
        codePane.getChildren().add(codeArea);
        
        cancelButton.setOnAction((ActionEvent event) -> {
            closeWindow();
        });
    }    
    
    private void closeWindow() {
        Window w = cancelButton.getScene().getWindow();
        w.fireEvent(new WindowEvent(w, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    
    public void setCSS(String css) {
        codeArea.setCode(css);
    }
}