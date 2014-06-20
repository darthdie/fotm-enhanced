package fotm;

import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

/**
 *
 * @author Briar
 */
public class HeroFrontCard extends Card {

    private final SimpleStringProperty classes;
    private final SimpleStringProperty hp;
    private final SimpleStringProperty powerName;
    private final SimpleStringProperty powerText;
    private final SimpleStringProperty nemesisImagePath;
    private final SimpleObjectProperty<Image> nemesisImage;

    public HeroFrontCard(Integer id, String name) {
        super(id, name);

        this.classes = new SimpleStringProperty(null, "classes");
        this.hp = new SimpleStringProperty(null, "hp", "N/A");
        this.powerName = new SimpleStringProperty(null, "powerName");
        this.powerText = new SimpleStringProperty(null, "powerText");
        this.nemesisImagePath = new SimpleStringProperty(null, "nemesisImagePath");
        this.nemesisImage = new SimpleObjectProperty<>(null, "nemesisImage");
        
        this.addProperties(classes, hp, powerName, powerText, nemesisImagePath);
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

    public void setPowerName(String value) {
        if (!Objects.equals(powerName.get(), value)) {
            powerName.set(value);
            setIsDirty(true);
        }
    }

    public String getPowerName() {
        return powerName.get();
    }

    public StringProperty powerNameProperty() {
        return powerName;
    }

    public void setPowerText(String value) {
        if (!Objects.equals(powerText.get(), value)) {
            powerText.set(value);
            setIsDirty(true);
        }
    }

    public String getPowerText() {
        return powerText.get();
    }

    public StringProperty powerTextProperty() {
        return powerText;
    }

    public String getNemesisImagePath() {
        return nemesisImagePath.get();
    }

    public void setNemesisImagePath(String value) {
        if (!Objects.equals(nemesisImagePath.get(), value)) {
            nemesisImagePath.set(value);

            if (value != null) {
                try {
                    nemesisImage.set(new Image("file:" + value));
                } catch (Exception ex) {
                }
            }
            
            setIsDirty(true);
        }
    }

    public StringProperty nemesisImagePathProperty() {
        return nemesisImagePath;
    }

    public Image getNemesisImage() {
        return nemesisImage.get();
    }

    public ObjectProperty<Image> nemesisImageProperty() {
        return nemesisImage;
    }
}
