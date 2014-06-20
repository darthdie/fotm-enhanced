
package fotm;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Briar
 */
public class HeroBackCard extends Card {
    private final SimpleStringProperty classes;
    private final SimpleStringProperty abilityLine1;
    private final SimpleStringProperty abilityLine2;
    private final SimpleStringProperty abilityLine3;
    private final SimpleStringProperty abilityLine4;
    private final SimpleStringProperty abilityLine5;
    private final SimpleStringProperty abilityLine6;
    
    public HeroBackCard(Integer id, String name) {
        super(id, name);
        
        this.classes = new SimpleStringProperty(null, "classes");
        this.abilityLine1 = new SimpleStringProperty(null, "abilityLine1");
        this.abilityLine2 = new SimpleStringProperty(null, "abilityLine2");
        this.abilityLine3 = new SimpleStringProperty(null, "abilityLine3");
        this.abilityLine4 = new SimpleStringProperty(null, "abilityLine4");
        this.abilityLine5 = new SimpleStringProperty(null, "abilityLine5");
        this.abilityLine6 = new SimpleStringProperty(null, "abilityLine6");
        
        this.addProperties(classes, abilityLine1, abilityLine2, abilityLine3, abilityLine4, abilityLine5, abilityLine6);
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
    
    public void setAbilityLine1(String value) {
        if (!Objects.equals(abilityLine1.get(), value)) {
            abilityLine1.set(value);
            setIsDirty(true);
        }
    }

    public String getAbilityLine1() {
        return abilityLine1.get();
    }

    public StringProperty abilityLine1Property() {
        return abilityLine1;
    }
    
    public void setAbilityLine2(String value) {
        if (!Objects.equals(abilityLine2.get(), value)) {
            abilityLine2.set(value);
            setIsDirty(true);
        }
    }

    public String getAbilityLine2() {
        return abilityLine2.get();
    }

    public StringProperty abilityLine2Property() {
        return abilityLine2;
    }
    
    public void setAbilityLine3(String value) {
        if (!Objects.equals(abilityLine3.get(), value)) {
            abilityLine3.set(value);
            setIsDirty(true);
        }
    }

    public String getAbilityLine3() {
        return abilityLine3.get();
    }

    public StringProperty abilityLine3Property() {
        return abilityLine3;
    }
    
    public void setAbilityLine4(String value) {
        if (!Objects.equals(abilityLine4.get(), value)) {
            abilityLine4.set(value);
            setIsDirty(true);
        }
    }

    public String getAbilityLine4() {
        return abilityLine4.get();
    }

    public StringProperty abilityLine4Property() {
        return abilityLine4;
    }
    
    public void setAbilityLine5(String value) {
        if (!Objects.equals(abilityLine5.get(), value)) {
            abilityLine5.set(value);
            setIsDirty(true);
        }
    }

    public String getAbilityLine5() {
        return abilityLine5.get();
    }

    public StringProperty abilityLine5Property() {
        return abilityLine5;
    }
    
    public void setAbilityLine6(String value) {
        if (!Objects.equals(abilityLine6.get(), value)) {
            abilityLine6.set(value);
            setIsDirty(true);
        }
    }

    public String getAbilityLine6() {
        return abilityLine6.get();
    }

    public StringProperty abilityLine6Property() {
        return abilityLine6;
    }
}
