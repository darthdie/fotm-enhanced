/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fotm;

import fotm.gui.CardPaneFactory;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 *
 * @author Briar
 */
public class CardRenderer {
    public static Image render(Card card) {
        return renderPane(CardPaneFactory.getPane(card));
    }
    
    private static Image renderPane(Pane pane) { 
        Scene scene = new Scene(pane);//, 300, 250
        return pane.snapshot(new SnapshotParameters(), null);            
    }
}
