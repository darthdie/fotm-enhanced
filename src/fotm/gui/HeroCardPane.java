package fotm.gui;

import fotm.CardRenderer;
import fotm.HeroCard;
import java.io.File;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public final class HeroCardPane extends Pane {
    public HeroCardPane(HeroCard card) {
        getStylesheets().add(CardRenderer.class.getResource("test.css").toExternalForm());
        
        Image backgroundImage = new Image("file:images" + File.separator + "herocard" + File.separator + "cardback.jpg");
        ImageView background = new ImageView();
        background.setImage(backgroundImage);
        getChildren().add(background);

        Text cardLabel = new Text(card.getName());
        cardLabel.textProperty().bind(card.nameProperty());
        cardLabel.setId("cardname");
        cardLabel.setTextAlignment(TextAlignment.LEFT);
        cardLabel.setLayoutX(70);
        cardLabel.setLayoutY(95);
        getChildren().add(cardLabel);

        ImageView portrait = new ImageView();
        portrait.imageProperty().bind(card.portraitImageProperty());
        portrait.setX(46);
        portrait.setY(122);
        portrait.setFitWidth(703 - 45);
        portrait.setFitHeight(608 - 122);
        getChildren().add(portrait);

        ImageView hpImage = new ImageView();
        hpImage.imageProperty().bind(card.hpImageProperty());
        hpImage.setLayoutX(568);
        hpImage.setLayoutY(47);
        hpImage.visibleProperty().bind(card.hpVisibleProperty());
        getChildren().add(hpImage);

        Text hp = new Text(card.getHp());
        hp.textProperty().bind(card.hpProperty());
        hp.setId("hptext");
        hp.setX(630);
        hp.setY(80);
        hp.setTextAlignment(TextAlignment.CENTER);
        hp.setTextOrigin(VPos.CENTER);
        hp.visibleProperty().bind(card.hpVisibleProperty());
        getChildren().add(hp);

        Text cardClass = new Text(card.getClasses());
        cardClass.textProperty().bind(card.classesProperty());
        cardClass.setId("classtext");
        cardClass.setLayoutX(90);
        cardClass.setLayoutY(635);
        getChildren().add(cardClass);

        Text cardText = new Text(card.getText());
        cardText.textProperty().bind(card.textProperty());
        cardText.setLayoutX(66);
        cardText.setLayoutY(675);
        cardText.setId("cardtext");
        getChildren().add(cardText);

        Text quoteText = new Text(card.getQuote());
        quoteText.textProperty().bind(card.quoteProperty());
        quoteText.setX(180);
        quoteText.setY(918);
        quoteText.setId("quotetext");
        quoteText.setTextAlignment(TextAlignment.CENTER);
        quoteText.setTextOrigin(VPos.CENTER);
        getChildren().add(quoteText);

        Text quoteText2 = new Text(card.getQuote2());
        quoteText2.textProperty().bind(card.quote2Property());
        quoteText2.setX(180);
        quoteText2.setY(945);
        quoteText2.setId("quotetext");
        quoteText2.setTextAlignment(TextAlignment.CENTER);
        quoteText2.setTextOrigin(VPos.CENTER);
        getChildren().add(quoteText2);

        Text issueText = new Text(card.getIssue());
        issueText.textProperty().bind(card.issueProperty());
        issueText.setX(180);
        issueText.setY(971);
        issueText.setId("issuetext");
        issueText.setTextAlignment(TextAlignment.CENTER);
        issueText.setTextOrigin(VPos.CENTER);
        getChildren().add(issueText);
    }
}
