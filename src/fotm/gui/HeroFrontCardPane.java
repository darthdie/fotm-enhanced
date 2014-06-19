
package fotm.gui;

import fotm.Card;
import fotm.CardRenderer;
import fotm.HeroFrontCard;
import java.io.File;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HeroFrontCardPane extends Pane {
    public HeroFrontCardPane(HeroFrontCard card) {
        getStylesheets().add(CardRenderer.class.getResource("heroFrontCard.css").toExternalForm());
        
        ImageView portrait = new ImageView();
        portrait.imageProperty().bind(card.portraitImageProperty());
        portrait.fitHeightProperty().bind(this.heightProperty());
        portrait.fitWidthProperty().bind(this.widthProperty());
        getChildren().add(portrait);
        
        Text powerName = new Text(card.getPowerName());
        powerName.textProperty().bind(card.powerNameProperty());
        powerName.setId("powerName");
        powerName.setTextAlignment(TextAlignment.LEFT);
        powerName.setLayoutX(140);
        powerName.setLayoutY(898);
        getChildren().add(powerName);
        
        Text powerLabel = new Text("Power:");
        powerLabel.setLayoutX(75);
        powerLabel.setLayoutY(931);
        powerLabel.setId("powerLabel");
        powerLabel.setTextAlignment(TextAlignment.LEFT);
        getChildren().add(powerLabel);
        
        Text powerText = new Text(card.getPowerText());
        powerText.textProperty().bind(card.powerTextProperty());
        powerText.setLayoutX(175);
        powerText.setLayoutY(931);
        powerText.setId("powerText");
        getChildren().add(powerText);
        
        Text name = new Text(card.getName());
        name.textProperty().bind(card.nameProperty());
        name.setLayoutX(155);
        name.setLayoutY(105);
        name.setId("heroName");
        name.setTextAlignment(TextAlignment.CENTER);
        getChildren().add(name);
        
        Text hp = new Text(card.getHp());
        hp.textProperty().bind(card.hpProperty());
        hp.setLayoutX(70);
        hp.setLayoutY(305);
        hp.setId("hp");
        hp.setTextAlignment(TextAlignment.CENTER);
        getChildren().add(hp);
        
        ImageView publisher = new ImageView("file:images" + File.separator + "herofront" + File.separator + "comicbadge.png");
        publisher.setLayoutX(0);
        publisher.setLayoutY(0);
        getChildren().add(publisher);
        
        ImageView nemesisShine = new ImageView("file:images" + File.separator + "herofront" + File.separator + "nemesisshine.png");
        getChildren().add(nemesisShine);
        
        ImageView nemesisImage;
        if(card.getNemesisImagePath() == null || card.getNemesisImagePath().isEmpty()) {
            nemesisImage = new ImageView("file:images" + File.separator + "herofront" + File.separator + "nemesisshine.png");
        }
        else {
            nemesisImage = new ImageView();
            nemesisImage.imageProperty().bind(card.nemesisImageProperty());
            nemesisImage.setFitHeight(113);
            nemesisImage.setFitWidth(113);
        }
        
        nemesisImage.setLayoutX(584);
        nemesisImage.setLayoutY(827);
        getChildren().add(nemesisImage);
        
        ImageView nemesisBack = new ImageView("file:images" + File.separator + "herofront" + File.separator + "nemesisback.png");
        getChildren().add(nemesisBack);
        
        //ImageView powerUnderlay = new ImageView("images" + File.separator + "blank.png");
        /*img = new ImageIcon("images" + File.separator + "blank.png");
        powerUnderlay = new JLabel();
        powerUnderlay.setBounds(64, 890, 680 - 64, 998 - 890);
        powerUnderlay.setOpaque(true);
        powerUnderlay.setBackground(heroFrontCard.getColor());
        getImagePane().add(powerUnderlay);*/
        
        ImageView cardBorder = new ImageView("file:images" + File.separator + "herofront" + File.separator + "cardborder.png");
        getChildren().add(cardBorder);
    }
}