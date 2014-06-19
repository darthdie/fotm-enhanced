/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fotm.gui;

import fotm.gui.FilterExtensionType;
import fotm.gui.FilteredFileChooser;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.control.PropertySheet.Item;
import org.controlsfx.property.editor.PropertyEditor;

public class ExtendedPropertySheet extends PropertySheet {
    public ExtendedPropertySheet() {
        insertPropertyEditorFactory();
    }

    public ExtendedPropertySheet(ObservableList<Item> items) {
        super(items);
        insertPropertyEditorFactory();
    }

    private void insertPropertyEditorFactory() {
        setPropertyEditorFactory(new CardPropertyEditorFactory(getPropertyEditorFactory()));
    }
}

class CardPropertyEditorFactory implements Callback<PropertySheet.Item, PropertyEditor<?>> {
    Callback<PropertySheet.Item, PropertyEditor<?>> delegatePropertyEditor;

    public CardPropertyEditorFactory(Callback<PropertySheet.Item, PropertyEditor<?>> delegatePropertyEditor) {
        this.delegatePropertyEditor = delegatePropertyEditor;
    }

    @Override
    public PropertyEditor<?> call(PropertySheet.Item item) {
        String name = item.getName();
        
        if (name.equals("portraitPath")) {
            return new PathEditor(item, FilterExtensionType.Image);
        }
        
        if(name.equals("text")) {
            return new MultilineEditor(item);
        }

        return delegatePropertyEditor.call(item);
    }
}

final class MultilineEditor implements PropertyEditor<String> {
    private final TextArea textArea;
    private final PropertySheet.Item item;
    
    public MultilineEditor(PropertySheet.Item item) {
        this.item = item;
        textArea = new TextArea();
        textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                item.setValue(newValue);
            }
        });
    }
    
    @Override
    public Node getEditor() {
        return textArea;
    }

    @Override
    public String getValue() {
        return textArea.getText();
    }

    @Override
    public void setValue(String t) {
        textArea.setText(t);
    }
}

final class PathEditor implements PropertyEditor<String> {

    private final TextField pathField;
    private final Button browseButton;
    private final HBox container;
    
    private final PropertySheet.Item item;
    private final FilterExtensionType filterType;
    private FilteredFileChooser fileChooser;

    public PathEditor(PropertySheet.Item item, FilterExtensionType type) {
        this.item = item;
        this.filterType = type;
        this.fileChooser = new FilteredFileChooser(type);

        pathField = new TextField();
        pathField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                item.setValue(newValue);
            }
        });
        
        browseButton = new Button("...");
        browseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                File f = fileChooser.showOpenDialog(null);
                if (f != null) {
                    pathField.setText(f.getAbsolutePath());
                }
            }
        });
        
        container = new HBox(4);
        HBox.setHgrow(pathField, Priority.ALWAYS);
        container.getChildren().addAll(pathField, browseButton);
    }

    @Override
    public Node getEditor() {
        return container;
    }

    @Override
    public String getValue() {
        return pathField.getText();
    }

    @Override
    public void setValue(String t) {
        pathField.setText(t);
    }
}
