package fotm.gui;

import fotm.Card;
import fotm.Deck;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import org.controlsfx.property.BeanPropertyUtils;

/**
 *
 * @author Briar
 */
public class EditCardTabView {

    private final SimpleObjectProperty<Tab> tabProperty;
    private final Card cardProperty;

    public EditCardTabView(Card card) {
        tabProperty = new SimpleObjectProperty<>();
        cardProperty = card;

        cardProperty.saveState();
        
        initTab();
    }

    private void initTab() {
        SplitPane sp = new SplitPane();
        ScrollPane scrollPane = new ScrollPane();

        ExtendedPropertySheet propertySheet = new ExtendedPropertySheet(BeanPropertyUtils.getProperties(cardProperty).sorted());

        scrollPane.setContent(CardPaneFactory.getPane(cardProperty));

        sp.getItems().addAll(scrollPane, propertySheet);

        Tab tab = new Tab(cardProperty.getName());
        tab.setOnCloseRequest(new EventHandler() {
            @Override
            public void handle(Event e) {
                if(cardProperty.getIsDirty()) {
                    Action response = Dialogs.create()
                        .title("You haz unsaved changes.")
                        .message( "You haven't saved your changes, would you like to save first?")
                        .showConfirm();
                    
                    if (response == Dialog.Actions.YES) {
                        //save
                    } else if(response == Dialog.Actions.NO) {
                        cardProperty.revertToSavedState();
                    }
                    else {
                        e.consume();
                    }
                }
            }
        });
        
        cardProperty.isDirtyProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal, Object newVal) {
                if((boolean)newVal) {
                    tab.setText(cardProperty.getName() + "*");
                }
                else {
                    tab.setText(cardProperty.getName());
                }
            }
        });
        cardProperty.nameProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal, Object newVal) {              
                if(cardProperty.getIsDirty()) {
                    tab.setText(cardProperty.getName() + "*");
                }
                else {
                    tab.setText(cardProperty.getName());
                }
            }
        });
        
        tab.setContent(sp);
        tabProperty.set(tab);
        //VBox v = new VBox();

        //VBox.setVgrow(sp, Priority.ALWAYS);
        /*HBox hb = new HBox();
         VBox.setVgrow(hb, Priority.NEVER);
         Button ok = new Button("Accept");
         Button cancel = new Button("Cancel");
        
         v.getChildren().addAll(sp, hb);*/
    }

    public ObjectProperty<Tab> tabProperty() {
        return tabProperty;
    }
}
