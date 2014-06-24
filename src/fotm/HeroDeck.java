package fotm;

public class HeroDeck extends Deck {
    public HeroDeck() {
        super(DeckType.Hero);
    }
    
    @Override
    public Card createNewCard() {
        return new HeroCard(getNextID(), getNewCardName());
    }
    
    private String getNewCardName() {
        int index = 0;
        while(true) {
            String name = "New Hero Card " + index++;
            if(!containsName(name)) {
                return name;
            }
        }
    }
}
