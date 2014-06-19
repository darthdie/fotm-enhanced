package fotm.gui;

import fotm.Card;
import fotm.HeroBackCard;
import fotm.HeroCard;
import fotm.HeroFrontCard;
import javafx.scene.layout.Pane;

public final class CardPaneFactory {
    public static Pane getPane(Card card) {
        if(card instanceof HeroCard) {
            return new HeroCardPane((HeroCard)card);
        }
        else if(card instanceof HeroFrontCard) {
            return new HeroFrontCardPane((HeroFrontCard)card);
        }
        else if(card instanceof HeroBackCard) {
            return new HeroBackCardPane((HeroBackCard)card);
        }
        
        return null;
    }
}