/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fotm.gui;

import fotm.Card;
import fotm.gui.ExtendedPropertySheet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import org.controlsfx.property.BeanPropertyUtils;

public class EditHeroCardDialogController implements Initializable {
    @FXML SplitPane splitPane;
    @FXML ScrollPane leftPane;

    private ExtendedPropertySheet propertySheet;

    private Card card;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setCard(Card card) {
        this.card = card;
        loadCard();
    }

    private void loadCard() {       
        propertySheet = new ExtendedPropertySheet(BeanPropertyUtils.getProperties(card).sorted());
        splitPane.getItems().add(propertySheet);
        
        leftPane.setContent(CardPaneFactory.getPane(card));
        //cardImageView.setImage(CardRenderer.render(card));
    }
}