
package fotm;

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
        
        this.classes = new SimpleStringProperty("");
        this.abilityLine1 = new SimpleStringProperty("");
        this.abilityLine2 = new SimpleStringProperty("");
        this.abilityLine3 = new SimpleStringProperty("");
        this.abilityLine4 = new SimpleStringProperty("");
        this.abilityLine5 = new SimpleStringProperty("");
        this.abilityLine6 = new SimpleStringProperty("");
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
    
    public void setAbilityLine1(String value) {
        abilityLine1.set(value);
    }

    public String getAbilityLine1() {
        return abilityLine1.get();
    }

    public StringProperty abilityLine1Property() {
        return abilityLine1;
    }
    
    public void setAbilityLine2(String value) {
        abilityLine2.set(value);
    }

    public String getAbilityLine2() {
        return abilityLine2.get();
    }

    public StringProperty abilityLine2Property() {
        return abilityLine2;
    }
    
    public void setAbilityLine3(String value) {
        abilityLine3.set(value);
    }

    public String getAbilityLine3() {
        return abilityLine3.get();
    }

    public StringProperty abilityLine3Property() {
        return abilityLine3;
    }
    
    public void setAbilityLine4(String value) {
        abilityLine4.set(value);
    }

    public String getAbilityLine4() {
        return abilityLine4.get();
    }

    public StringProperty abilityLine4Property() {
        return abilityLine4;
    }
    
    public void setAbilityLine5(String value) {
        abilityLine5.set(value);
    }

    public String getAbilityLine5() {
        return abilityLine5.get();
    }

    public StringProperty abilityLine5Property() {
        return abilityLine5;
    }
    
    public void setAbilityLine6(String value) {
        abilityLine6.set(value);
    }

    public String getAbilityLine6() {
        return abilityLine6.get();
    }

    public StringProperty abilityLine6Property() {
        return abilityLine6;
    }
}
