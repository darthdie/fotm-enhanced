package fotm.gui;

import fotm.Card;
import fotm.Deck;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class DeckOverviewTabView implements TabView {
    private final SimpleObjectProperty<Tab> tabProperty;
    private final SimpleObjectProperty<ToolBar> toolbarProperty;
    private final ObjectProperty<Deck> deckProperty;
    private final List<EditCardListener> listeners;
    
    private TableView<Card> cardTableView;
    private TableColumn<Card, Integer> cardIDColumn;
    private TableColumn<Card, String> cardNameColumn;
    private TableColumn<Card, String> cardTypeColumn;
    private TableColumn<Card, String> cardClassesColumn;
    private TableColumn<Card, String> cardHPColumn;
    private TableColumn<Card, Integer> cardIndexColumn;
    
    private ToolBar toolbar;
    private Button toolbarAddCardButton;
    private Button toolbarDeleteCardButton;
    private Button toolbarIncrementCardButton;
    private Button toolbarDecrementCardButton;
    private Button toolbarEditCSSButton;
    
    public DeckOverviewTabView(Deck deck) {
        listeners = new ArrayList<>();
        tabProperty = new SimpleObjectProperty<>();
        deckProperty = new SimpleObjectProperty<>(deck);
        toolbarProperty = new SimpleObjectProperty<>();
        
        initTab();
        initToolbar();
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
    
    private void initToolbar() {
        toolbarAddCardButton = new Button("Add Card");
        toolbarAddCardButton.setGraphic(new ImageView());
        ((ImageView) toolbarAddCardButton.getGraphic()).setImage(new Image("file:Images/add.png"));
        toolbarAddCardButton.setOnAction((ActionEvent event) -> {
            addCard();
        });

        toolbarDeleteCardButton = new Button("Delete Card");
        toolbarDeleteCardButton.setGraphic(new ImageView());
        ((ImageView) toolbarDeleteCardButton.getGraphic()).setImage(new Image("file:Images/delete.png"));
        toolbarDeleteCardButton.setOnAction((ActionEvent event) -> {
            deleteCard();
        });

        toolbarIncrementCardButton = new Button("Increment #");
        toolbarIncrementCardButton.setGraphic(new ImageView());
        ((ImageView) toolbarIncrementCardButton.getGraphic()).setImage(new Image("file:Images/arrow_up.png"));
        toolbarIncrementCardButton.setOnAction((ActionEvent event) -> {
            incrementCard();
        });

        toolbarDecrementCardButton = new Button("Decrement #");
        toolbarDecrementCardButton.setGraphic(new ImageView());
        ((ImageView) toolbarDecrementCardButton.getGraphic()).setImage(new Image("file:Images/arrow_down.png"));
        toolbarDecrementCardButton.setOnAction((ActionEvent event) -> {
            decrementCard();
        });

        toolbarEditCSSButton = new Button("Edit CSS");
        toolbarEditCSSButton.setGraphic(new ImageView());
        ((ImageView) toolbarEditCSSButton.getGraphic()).setImage(new Image("file:Images/css.png"));
        toolbarEditCSSButton.setOnAction((ActionEvent event) -> {
            editCSS();
        });
        
        toolbar = new ToolBar();
        toolbar.setPrefWidth(Double.MAX_VALUE);
        toolbar.getItems().addAll(toolbarAddCardButton, toolbarDeleteCardButton, toolbarIncrementCardButton, toolbarDecrementCardButton, toolbarEditCSSButton);
        
        toolbarProperty.set(toolbar);
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
    
    public void addEditListener(EditCardListener listener) {
        listeners.add(listener);
    }
    
    public ObjectProperty<Tab> tabProperty() {
        return tabProperty;
    }
    
    public ObjectProperty<ToolBar> toolbarProperty() {
        return toolbarProperty;
    }
}
