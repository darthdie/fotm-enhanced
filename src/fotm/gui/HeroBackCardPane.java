package fotm.gui;

import fotm.HeroBackCard;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HeroBackCardPane extends Pane {
    public HeroBackCardPane(HeroBackCard card) {
        createAndAddAbilityLine(80, 780, card.abilityLine1Property());
        createAndAddAbilityLine(80, 855, card.abilityLine2Property());
        createAndAddAbilityLine(80, 933, card.abilityLine3Property());
        
        createAndAddAbilityLine(110, 770, card.abilityLine4Property());
        createAndAddAbilityLine(110, 797, card.abilityLine5Property());
        createAndAddAbilityLine(110, 845, card.abilityLine6Property());
        
        /*powerText2 = new JLabel(heroBackCard.getAbilityLine2());
        powerText2.setBounds(110, 797, 650 - 110, 22);
        powerText2.setForeground(heroBackCard.getTextFontColor());
        powerText2.setFont(font);
        powerText2.setHorizontalAlignment(JLabel.LEFT);
        powerText2.setVerticalAlignment(JLabel.CENTER);
        getImagePane().add(powerText2);

        powerText3 = new JLabel(heroBackCard.getAbilityLine3());
        powerText3.setBounds(110, 845, 650 - 110, 25);
        powerText3.setForeground(heroBackCard.getTextFontColor());
        powerText3.setFont(font);
        powerText3.setHorizontalAlignment(JLabel.LEFT);
        powerText3.setVerticalAlignment(JLabel.CENTER);
        getImagePane().add(powerText3);

        powerText4 = new JLabel(heroBackCard.getAbilityLine4());
        powerText4.setBounds(110, 872, 650 - 110, 25);
        powerText4.setForeground(heroBackCard.getTextFontColor());
        powerText4.setFont(font);
        powerText4.setHorizontalAlignment(JLabel.LEFT);
        powerText4.setVerticalAlignment(JLabel.CENTER);
        getImagePane().add(powerText4);

        powerText5 = new JLabel(heroBackCard.getAbilityLine5());
        powerText5.setBounds(110, 923, 650 - 110, 25);
        powerText5.setForeground(heroBackCard.getTextFontColor());
        powerText5.setFont(font);
        powerText5.setHorizontalAlignment(JLabel.LEFT);
        powerText5.setVerticalAlignment(JLabel.CENTER);
        getImagePane().add(powerText5);

        powerText6 = new JLabel(heroBackCard.getAbilityLine6());
        powerText6.setBounds(110, 950, 650 - 110, 25);
        powerText6.setForeground(heroBackCard.getTextFontColor());
        powerText6.setFont(font);
        powerText6.setHorizontalAlignment(JLabel.LEFT);
        powerText6.setVerticalAlignment(JLabel.CENTER);
        getImagePane().add(powerText6);

        powerUnderlay = new JLabel();
        powerUnderlay.setBounds(64, 738, 680 - 64, 998 - 738);
        powerUnderlay.setOpaque(true);
        powerUnderlay.setBackground(heroBackCard.getTextColour());
        getImagePane().add(powerUnderlay);

        img = new ImageIcon("images" + File.separator + "heroback" + File.separator + "cardborder.png");
        cardborder = new JLabel(img);
        cardborder.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        getImagePane().add(cardborder);
        setImageWidth(img.getIconWidth());
        setImageHeight(img.getIconHeight());

        img = new ImageIcon(heroBackCard.getPortraitFile());
        img = new ImageIcon(getScaledImage(img.getImage(), getImageWidth(), getImageHeight()));
        portrait = new JLabel(img);
        portrait.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        getImagePane().add(portrait);*/
    }
    
    private void createAndAddAbilityLine(int x, int y, StringProperty prop) {
        Text powerLabel1 = new Text(prop.get());
        powerLabel1.textProperty().bind(prop);
        powerLabel1.setLayoutX(x);
        powerLabel1.setLayoutY(y);
        powerLabel1.setTextAlignment(TextAlignment.LEFT);
        getChildren().add(powerLabel1);
    }
}
