
package fotm;

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
        
        this.classes = new SimpleStringProperty("");
        this.hp = new SimpleStringProperty("N/A");
        this.powerName = new SimpleStringProperty();
        this.powerText = new SimpleStringProperty();
        this.nemesisImagePath = new SimpleStringProperty();
        this.nemesisImage = new SimpleObjectProperty<>();
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
    
    public void setPowerName(String value) {
        powerName.set(value);
    }

    public String getPowerName() {
        return powerName.get();
    }

    public StringProperty powerNameProperty() {
        return powerName;
    }
    
    public void setPowerText(String value) {
        powerText.set(value);
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
        nemesisImagePath.set(value);
        if (value != null) {
            try {
                nemesisImage.set(new Image("file:" + value));
            } catch (Exception ex) {
            }
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