package fotm.gui;

import fotm.Card;
import fotm.Deck;
import fotm.DeckFile;
import fotm.DeckType;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

public class MainWindowController implements Initializable {
    @FXML private TabPane tabPane;

    @FXML MenuItem menuOpenDeck;
    
    @FXML MenuItem newHeroDeck;
    @FXML MenuItem newVillainDeck;
    @FXML MenuItem newEnvironmentDeck;

    @FXML Pane toolbarPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        
        tabPane.getSelectionModel().selectedItemProperty().addListener(
            (ObservableValue<? extends Tab> ov, Tab t, Tab t1) -> {
                if(t1 == null) {
                    return;
                }
                
                Object tv = t1.getUserData();
                
                if(tv != null && tv instanceof TabView) {
                    toolbarPane.getChildren().setAll(((TabView)tv).toolbarProperty().get());
                }
        });
        
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
        //d.getCards().addAll(d.getCards());

        DeckOverviewTabView v = new DeckOverviewTabView(d);
        v.addEditListener(this::handleEditCardRequest);

        addTabView(v);
    }
    
    private void handleEditCardRequest(Card card) {
        if(tabWithNameExists(card.getName())) {
            return;
        }
        
        EditCardTabView tv = new EditCardTabView(card);
        addTabView(tv);
    }
    
    private boolean tabWithNameExists(String name) {
        return tabPane.getTabs().stream().anyMatch((tab) -> (tab.getText().equals(name)));
    }
    
    private void createNewHeroDeck() {
        Deck d = new Deck(DeckType.Hero);
        DeckOverviewTabView v = new DeckOverviewTabView(d);
        v.addEditListener(this::handleEditCardRequest);
        addTabView(v);
    }
    
    private void createNewVillainDeck() {
        Deck d = new Deck(DeckType.Villain);
        DeckOverviewTabView v = new DeckOverviewTabView(d);
        v.addEditListener(this::handleEditCardRequest);
        addTabView(v);
    }
        
    private void createNewEnvironmentDeck() {
        Deck d = new Deck(DeckType.Environment);
        DeckOverviewTabView v = new DeckOverviewTabView(d);
        v.addEditListener(this::handleEditCardRequest);
        addTabView(v);
    }
    
    private void addTabView(TabView tab) {
        tab.tabProperty().get().setUserData(tab);
        tabPane.getTabs().add(tab.tabProperty().get());
        tabPane.getSelectionModel().selectLast();
    }
}