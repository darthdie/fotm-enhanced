package fotm;

import java.util.Scanner;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Deck {
    private final ObservableList<Card> cards;
    private final DeckType type;
    private final SimpleStringProperty cssProperty;
    
    protected Deck(DeckType type) {
        this.type = type;
        cards = FXCollections.observableArrayList();

        cssProperty = new SimpleStringProperty(readFile("test.css"));
    }
    
    private String readFile(String path) {
        return new Scanner(CardRenderer.class.getResourceAsStream(path), "UTF-8").useDelimiter("\\A").next();
    }
    
    public DeckType getType() {
        return type;
    }
    
    public ObservableList<Card> getCards() {
        return cards;
    }
    
    public String getCss() {
        return cssProperty.get();
    }
    
    public void setCss(String value) {
        cssProperty.set(value);
    }
    
    public StringProperty getCssProperty() {
        return cssProperty;
    }
    
    public boolean containsName(String name) {
        return cards.stream().anyMatch(s -> s.getName().equals(name));
    }
    
    public int getNextID() {
        int id = 0;
        for(Card card : cards) {
            if(card.getId() > id) {
                id = card.getId();
            }
        }
        
        return id;
    }
    
    public abstract Card createNewCard();
}