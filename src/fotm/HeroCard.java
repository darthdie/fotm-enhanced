package fotm;

import java.io.File;
import java.util.Objects;
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

        this.classes = new SimpleStringProperty(null, "classes");
        this.hp = new SimpleStringProperty(null, "hp", "N/A");
        this.hpVisible = new SimpleBooleanProperty(null, "hpVisible");
        this.text = new SimpleStringProperty(null, "text");
        this.quote = new SimpleStringProperty(null, "quote");
        this.quote2 = new SimpleStringProperty(null, "quote2");
        this.issue = new SimpleStringProperty(null, "issue");
        this.hpImage = new SimpleObjectProperty<>(null, "hpImage");
        this.hpImagePath = new SimpleStringProperty(null, "hpImagePath");

        hpImagePath.set("file:images" + File.separator + "herocard" + File.separator + "hpimage.png");
        
        this.addProperties(classes, hp, hpVisible, text, quote, quote2, issue, hpImagePath);
    }

    public void setClasses(String value) {
        if (!Objects.equals(classes.get(), value)) {
            classes.set(value);
            setIsDirty(true);
        }
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
        if (!Objects.equals(hp.get(), value)) {
            hp.set(value);
            setIsDirty(true);
        }
    }

    public StringProperty hpProperty() {
        return hp;
    }

    public void setHpVisible(boolean value) {
        if (!Objects.equals(hpVisible.get(), value)) {
            hpVisible.set(value);
            setIsDirty(true);
        }
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
        if (!Objects.equals(text.get(), value)) {
            text.set(value);
            setIsDirty(true);
        }
    }

    public StringProperty textProperty() {
        return text;
    }

    public String getQuote() {
        return quote.get();
    }

    public void setQuote(String value) {
        if (!Objects.equals(quote.get(), value)) {
            quote.set(value);
            setIsDirty(true);
        }
    }

    public StringProperty quoteProperty() {
        return quote;
    }

    public String getQuote2() {
        return quote2.get();
    }

    public void setQuote2(String value) {
        if (!Objects.equals(quote2.get(), value)) {
            quote2.set(value);
            setIsDirty(true);
        }
    }

    public StringProperty quote2Property() {
        return quote2;
    }

    public String getIssue() {
        return issue.get();
    }

    public void setIssue(String value) {
        if (!Objects.equals(issue.get(), value)) {
            issue.set(value);
            setIsDirty(true);
        }
    }

    public StringProperty issueProperty() {
        return issue;
    }

    public String getHpImagePath() {
        return hpImagePath.get();
    }

    public void setHpImagePath(String value) {
        if (!Objects.equals(hpImagePath.get(), value)) {
            hpImagePath.set(value);
            
            if (value != null) {
                try {
                    hpImage.set(new Image("file:" + value));
                } catch (Exception ex) {
                }
            }

            setIsDirty(true);
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
