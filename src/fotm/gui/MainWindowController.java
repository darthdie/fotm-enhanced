/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fotm.gui;

import fotm.Card;
import fotm.Deck;
import fotm.DeckFile;
import fotm.DeckType;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainWindowController implements Initializable {
    @FXML
    private TabPane tabPane;

    @FXML
    private Button toolbarAddCardButton;
    @FXML
    private Button toolbarDeleteCardButton;
    @FXML
    private Button toolbarIncrementCardButton;
    @FXML
    private Button toolbarDecrementCardButton;

    @FXML
    Button toolbarEditCSSButton;

    @FXML
    MenuItem menuOpenDeck;
    
    @FXML MenuItem newHeroDeck;
    @FXML MenuItem newVillainDeck;
    @FXML MenuItem newEnvironmentDeck;

    //private ObjectProperty<Deck> deckProperty = new SimpleObjectProperty<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //deckProperty.set(new Deck(DeckType.Hero));

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
        
        ((ImageView) toolbarAddCardButton.getGraphic()).setImage(new Image("file:Images/add.png"));
        toolbarAddCardButton.setOnAction((ActionEvent event) -> {
            addCard();
        });

        ((ImageView) toolbarDeleteCardButton.getGraphic()).setImage(new Image("file:Images/delete.png"));
        toolbarDeleteCardButton.setOnAction((ActionEvent event) -> {
            deleteCard();
        });

        ((ImageView) toolbarIncrementCardButton.getGraphic()).setImage(new Image("file:Images/arrow_up.png"));
        toolbarIncrementCardButton.setOnAction((ActionEvent event) -> {
            incrementCard();
        });

        ((ImageView) toolbarDecrementCardButton.getGraphic()).setImage(new Image("file:Images/arrow_down.png"));
        toolbarDecrementCardButton.setOnAction((ActionEvent event) -> {
            decrementCard();
        });

        ((ImageView) toolbarEditCSSButton.getGraphic()).setImage(new Image("file:Images/css.png"));
        toolbarEditCSSButton.setOnAction((ActionEvent event) -> {
            editCSS();
        });

        menuOpenDeck.setOnAction((ActionEvent event) -> {
            openDeck();
        });
    }

    public void setDeck(List<Card> cards) {
        cards.clear();
        cards.addAll(cards);
    }

    private void addCard() {

    }

    private void deleteCard() {

    }

    private void incrementCard() {

    }

    private void decrementCard() {

    }

    private void editCSS() {
        /*try {
            URL location = getClass().getResource("EditCSSDialog.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());

            Parent root = (Parent) fxmlLoader.load(location.openStream());

            ((EditCSSDialogController) fxmlLoader.getController()).setCSS(deckProperty.get().getCss());

            Scene scene = new Scene(root);

            Window w = toolbarEditCSSButton.getScene().getWindow();
            Stage dialog = new Stage();
            dialog.setWidth(w.getWidth() * .75);
            dialog.setHeight(w.getHeight() * .75);
            dialog.setScene(scene);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(w);
            dialog.showAndWait();
        } catch (IOException ex) {
            if (ex != null) {

            }
        }*/
    }

    private void openDeck() {
        FilteredFileChooser dlg = new FilteredFileChooser(FilterExtensionType.Deck);
        File f = dlg.showOpenDialog(toolbarEditCSSButton.getScene().getWindow());
        if (f == null) {
            return;
        }

        Deck d = DeckFile.loadFrom(f.getAbsolutePath());
        d.getCards().addAll(d.getCards());

        DeckOverviewTabView v = new DeckOverviewTabView(d);
        v.addEditListener(this::handleEditCardRequest);

        tabPane.getTabs().add(v.getTabProperty().get());
    }
    
    private void handleEditCardRequest(Card card) {
        if(tabWithNameExists(card.getName())) {
            return;
        }
        
        EditCardTabView tv = new EditCardTabView(card);
        addTab(tv.tabProperty().get());
    }
    
    private boolean tabWithNameExists(String name) {
        return tabPane.getTabs().stream().anyMatch((tab) -> (tab.getText().equals(name)));
    }
    
    private void createNewHeroDeck() {
        Deck d = new Deck(DeckType.Hero);
        DeckOverviewTabView v = new DeckOverviewTabView(d);
        v.addEditListener(this::handleEditCardRequest);
        addTab(v.getTabProperty().get());
    }
    
    private void createNewVillainDeck() {
        Deck d = new Deck(DeckType.Villain);
        DeckOverviewTabView v = new DeckOverviewTabView(d);
        v.addEditListener(this::handleEditCardRequest);
        addTab(v.getTabProperty().get());
    }
        
    private void createNewEnvironmentDeck() {
        Deck d = new Deck(DeckType.Environment);
        DeckOverviewTabView v = new DeckOverviewTabView(d);
        v.addEditListener(this::handleEditCardRequest);
        addTab(v.getTabProperty().get());
    }
    
    private void addTab(Tab tab) {
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().selectLast();
    }
}
