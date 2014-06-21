package fotm.gui;

import fotm.Card;
import fotm.Deck;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class DeckOverviewTabView {
    private final SimpleObjectProperty<Tab> tabProperty;
    private final ObjectProperty<Deck> deckProperty;
    private final List<EditCardListener> listeners;
    
    private TableView<Card> cardTableView;
    private TableColumn<Card, Integer> cardIDColumn;
    private TableColumn<Card, String> cardNameColumn;
    private TableColumn<Card, String> cardTypeColumn;
    private TableColumn<Card, String> cardClassesColumn;
    private TableColumn<Card, String> cardHPColumn;
    private TableColumn<Card, Integer> cardIndexColumn;
    
    public DeckOverviewTabView(ObjectProperty<Deck> deck) {
        listeners = new ArrayList<>();
        tabProperty = new SimpleObjectProperty<>();
        deckProperty = deck;
        
        initTab();
    }
    
    private void initTab() {
        Tab tab = new Tab("Deck Overview");
        
        cardTableView = new TableView<>();
        
        EventHandler click = (EventHandler) (Event e) -> {
            MouseEvent t = (MouseEvent) e;
            if (t.getClickCount() < 2) {
                return;
            }
            
            Card card = (Card) cardTableView.getItems().get(((TableCell) t.getSource()).getIndex());
            for(EditCardListener listener : listeners) {
                listener.handle(card);
            }
        };
        
        GenericCellFactory cellFactory = new GenericCellFactory(click, null);
        
        cardIDColumn = new TableColumn<>("ID");
        cardIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        cardIDColumn.setCellFactory(cellFactory);

        cardNameColumn = new TableColumn<>("Name");
        cardNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cardNameColumn.setCellFactory(cellFactory);

        cardTypeColumn = new TableColumn<>("Type");
        cardTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        cardTypeColumn.setCellFactory(cellFactory);

        cardClassesColumn = new TableColumn<>("Classes");
        cardClassesColumn.setCellValueFactory(new PropertyValueFactory<>("classes"));
        cardClassesColumn.setCellFactory(cellFactory);

        cardHPColumn = new TableColumn<>("HP");
        cardHPColumn.setCellValueFactory(new PropertyValueFactory<>("hp"));
        cardHPColumn.setCellFactory(cellFactory);

        cardIndexColumn = new TableColumn<>("Index");
        cardIndexColumn.setCellValueFactory(new PropertyValueFactory<>("index"));
        cardIndexColumn.setCellFactory(cellFactory);
        
        cardTableView.getColumns().addAll(cardIDColumn, cardNameColumn, cardTypeColumn, cardClassesColumn, cardHPColumn, cardIndexColumn);

        //Bindings.bindBidirectional(selectedCardImageView.imageProperty(), cardImage);

        cardTableView.setItems(deckProperty.get().getCards());
        
        tab.setContent(cardTableView);
        tabProperty.set(tab);
    }
    
    public void addEditListener(EditCardListener listener) {
        listeners.add(listener);
    }
    
    public SimpleObjectProperty<Tab> getTabProperty() {
        return tabProperty;
    }
}
