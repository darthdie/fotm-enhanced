
package fotm;

import java.io.File;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

/**
 *
 * @author Briar
 */
public class HeroCard extends Card {
    private final SimpleStringProperty classes;
    private final SimpleStringProperty hp;
    private final SimpleBooleanProperty hpVisible;
    private final SimpleStringProperty text;
    private final SimpleStringProperty quote;
    private final SimpleStringProperty quote2;
    private final SimpleStringProperty issue;
    private final SimpleStringProperty hpImagePath;
    private final SimpleObjectProperty<Image> hpImage;

    public HeroCard(Integer id, String name) {
        super(id, name);
        
        this.classes = new SimpleStringProperty("");
        this.hp = new SimpleStringProperty("N/A");
        this.hpVisible = new SimpleBooleanProperty();
        this.text = new SimpleStringProperty();
        this.quote = new SimpleStringProperty();
        this.quote2 = new SimpleStringProperty();
        this.issue = new SimpleStringProperty();
        this.hpImage = new SimpleObjectProperty<>();
        this.hpImagePath = new SimpleStringProperty();
        
        hpImagePath.set("file:images" + File.separator + "herocard" + File.separator + "hpimage.png");
    }
    
    public void setClasses(String value) {
        classes.set(value);
    }

    public String getClasses() {
        return classes.get();
    }

    public StringProperty classesProperty() {
        return classes;
    }
    
    public String getHp() {
        return hp.get();
    }

    public void setHp(String value) {
        hp.set(value);
    }

    public StringProperty hpProperty() {
        return hp;
    }
    
    public void setHpVisible(boolean value) {
        hpVisible.set(value);
    }

    public boolean getHpVisible() {
        return hpVisible.get();
    }

    public BooleanProperty hpVisibleProperty() {
        return hpVisible;
    }
    
    public String getText() {
        return text.get();
    }

    public void setText(String value) {
        text.set(value);
    }

    public StringProperty textProperty() {
        return text;
    }
    
    public String getQuote() {
        return quote.get();
    }

    public void setQuote(String value) {
        quote.set(value);
    }

    public StringProperty quoteProperty() {
        return quote;
    }

    public String getQuote2() {
        return quote2.get();
    }

    public void setQuote2(String value) {
        quote2.set(value);
    }

    public StringProperty quote2Property() {
        return quote2;
    }

    public String getIssue() {
        return issue.get();
    }

    public void setIssue(String value) {
        issue.set(value);
    }

    public StringProperty issueProperty() {
        return issue;
    }
    
    public String getHpImagePath() {
        return hpImagePath.get();
    }

    public void setHpImagePath(String value) {
        hpImagePath.set(value);
        if (value != null) {
            try {
                hpImage.set(new Image("file:" + value));
            } catch (Exception ex) {
            }
        }
    }

    public StringProperty hpImagePathProperty() {
        return hpImagePath;
    }

    public Image getHpImage() {
        return hpImage.get();
    }

    public ObjectProperty<Image> hpImageProperty() {
        return hpImage;
    }
}