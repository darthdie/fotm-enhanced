package fotm;

import java.util.Objects;
import java.util.Scanner;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public abstract class Card extends RevertableObject {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty type;
    private final SimpleIntegerProperty index;
    private final SimpleStringProperty portraitPath;
    private final SimpleObjectProperty<Image> portraitImage;
    private final SimpleStringProperty css;
    private final SimpleBooleanProperty isDirty;

    public Card(Integer id, String name) {
        this.id = new SimpleIntegerProperty(null, "id", id);
        this.name = new SimpleStringProperty(null, "name", name);
        this.type = new SimpleStringProperty(null, "type");
        this.index = new SimpleIntegerProperty(null, "index", 1);
        this.portraitPath = new SimpleStringProperty(null, "portraitPath");
        this.portraitImage = new SimpleObjectProperty<>(null, "portraitImage");
        this.css = new SimpleStringProperty(null, "css");
        this.isDirty = new SimpleBooleanProperty(null, "isDirty");

        String str = readFile("test.css");
        css.set(str);
        
        this.addProperties(this.id, this.name, type, index, portraitPath, css);
    }

    private String readFile(String path) {
        return new Scanner(CardRenderer.class.getResourceAsStream(path), "UTF-8").useDelimiter("\\A").next();
    }

    public Integer getId() {
        return id.get();
    }

    private void setId(Integer value) {
        if (!Objects.equals(id.get(), value)) {
            id.set(value);
            setIsDirty(true);
        }
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        if (!Objects.equals(name.get(), value)) {
            name.set(value);
            setIsDirty(true);
        }
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String value) {
        if (!Objects.equals(type.get(), value)) {
            type.set(value);
            setIsDirty(true);
        }
    }

    public Integer getIndex() {
        return index.get();
    }

    public void setIndex(Integer value) {
        if (!Objects.equals(index.get(), value)) {
            index.set(value);
            setIsDirty(true);
        }
    }

    public String getPortraitPath() {
        return portraitPath.get();
    }

    public void setPortraitPath(String value) {
        if (!Objects.equals(portraitPath.get(), value)) {
            portraitPath.set(value);
            
            if (value != null) {
                try {
                    portraitImage.set(new Image("file:" + value));
                } catch (Exception ex) {
                }
            }

            setIsDirty(true);
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

    public boolean getIsDirty() {
        return isDirty.get();
    }

    public void setIsDirty(boolean value) {
        if (!Objects.equals(isDirty.get(), value)) {
            isDirty.set(value);
        }
    }

    public BooleanProperty isDirtyProperty() {
        return isDirty;
    }
}
