package fotm;

import java.util.Scanner;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public abstract class Card {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty type;
    private final SimpleIntegerProperty index;
    private final SimpleStringProperty portraitPath;
    private final SimpleObjectProperty<Image> portraitImage;
    private final SimpleStringProperty css;
    
    public Card(Integer id, String name) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty("");
        this.index = new SimpleIntegerProperty(1);
        this.portraitPath = new SimpleStringProperty();
        this.portraitImage = new SimpleObjectProperty<>();
        this.css = new SimpleStringProperty();
        
        String str = readFile("test.css");
        css.set(str);
    }
    
    private String readFile(String path) {
        return new Scanner(CardRenderer.class.getResourceAsStream(path), "UTF-8").useDelimiter("\\A").next();
    }

    public Integer getId() {
        return id.get();
    }

    private void setId(Integer value) {
        id.set(value);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String value) {
        type.set(value);
    }

    public Integer getIndex() {
        return index.get();
    }

    public void setIndex(Integer value) {
        index.set(value);
    }

    public String getPortraitPath() {
        return portraitPath.get();
    }

    public void setPortraitPath(String value) {
        portraitPath.set(value);
        if (value != null) {
            try {
                portraitImage.set(new Image("file:" + value));
            } catch (Exception ex) {
            }
        }
    }

    public StringProperty portraitProperty() {
        return portraitPath;
    }

    public Image getPortraitImage() {
        return portraitImage.get();
    }

    public ObjectProperty<Image> portraitImageProperty() {
        return portraitImage;
    }
}