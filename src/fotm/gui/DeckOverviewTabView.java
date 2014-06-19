package fotm.gui;

import fotm.Card;
import fotm.Deck;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DeckOverviewTabView {
    private final SimpleObjectProperty<Tab> tabProperty;
    private final ObjectProperty<Deck> deckProperty;
    
    private TableView<Card> cardTableView;
    private TableColumn<Card, Integer> cardIDColumn;
    private TableColumn<Card, String> cardNameColumn;
    private TableColumn<Card, String> cardTypeColumn;
    private TableColumn<Card, String> cardClassesColumn;
    private TableColumn<Card, String> cardHPColumn;
    private TableColumn<Card, Integer> cardIndexColumn;
    
    public DeckOverviewTabView(ObjectProperty<Deck> deck) {
        tabProperty = new SimpleObjectProperty<>();
        deckProperty = deck;
        
        initTab();
    }
    
    private void initTab() {
        Tab tab = new Tab("Deck Overview");
        
        cardTableView = new TableView<>();
    }
    
    public SimpleObjectProperty<Tab> getTabProperty() {
        return tabProperty;
    }
}
