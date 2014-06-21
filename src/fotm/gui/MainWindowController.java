package fotm.gui;

import fotm.Card;
import fotm.Deck;
import fotm.DeckFile;
import fotm.DeckType;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainWindowController implements Initializable {
    @FXML private TabPane tabPane;

    @FXML MenuItem menuOpenDeck;
    
    @FXML MenuItem newHeroDeck;
    @FXML MenuItem newVillainDeck;
    @FXML MenuItem newEnvironmentDeck;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        
        newHeroDeck.setOnAction((ActionEvent event) -> {
            createNewHeroDeck();
        });

        newVillainDeck.setOnAction((ActionEvent event) -> {
            createNewVillainDeck();
        });
                
        newEnvironmentDeck.setOnAction((ActionEvent event) -> {
            createNewEnvironmentDeck();
        });

        menuOpenDeck.setOnAction((ActionEvent event) -> {
            openDeck();
        });
    }

    public void setDeck(List<Card> cards) {
        cards.clear();
        cards.addAll(cards);
    }

    private void openDeck() {
        FilteredFileChooser dlg = new FilteredFileChooser(FilterExtensionType.Deck);
        File f = dlg.showOpenDialog(tabPane.getScene().getWindow());
        if (f == null) {
            return;
        }

        Deck d = DeckFile.loadFrom(f.getAbsolutePath());
        d.getCards().addAll(d.getCards());

        DeckOverviewTabView v = new DeckOverviewTabView(d);
        v.addEditListener(this::handleEditCardRequest);

        tabPane.getTabs().add(v.tabProperty().get());
    }
    
    private void handleEditCardRequest(Card card) {
        if(tabWithNameExists(card.getName())) {
            return;
        }
        
        EditCardTabView tv = new EditCardTabView(card);
        addTabView(tv.tabProperty().get());
    }
    
    private boolean tabWithNameExists(String name) {
        return tabPane.getTabs().stream().anyMatch((tab) -> (tab.getText().equals(name)));
    }
    
    private void createNewHeroDeck() {
        Deck d = new Deck(DeckType.Hero);
        DeckOverviewTabView v = new DeckOverviewTabView(d);
        v.addEditListener(this::handleEditCardRequest);
        addTabView(v.tabProperty().get());
    }
    
    private void createNewVillainDeck() {
        Deck d = new Deck(DeckType.Villain);
        DeckOverviewTabView v = new DeckOverviewTabView(d);
        v.addEditListener(this::handleEditCardRequest);
        addTabView(v.tabProperty().get());
    }
        
    private void createNewEnvironmentDeck() {
        Deck d = new Deck(DeckType.Environment);
        DeckOverviewTabView v = new DeckOverviewTabView(d);
        v.addEditListener(this::handleEditCardRequest);
        addTabView(v.tabProperty().get());
    }
    
    private void addTabView(TabView tab) {
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().selectLast();
    }
}