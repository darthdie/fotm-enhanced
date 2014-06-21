package fotm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javafx.beans.property.Property;

public abstract class RevertableObject {
    private final List<Property> properties;
    private final Map<String, Object> values;
    
    protected RevertableObject() {
        properties = new ArrayList<>();
        values = new HashMap<>();
    }
    
    public void addProperties(Property... elements) {
        Collections.addAll(properties, elements);
    }
    
    public void saveState() {
        values.clear();
        
        for(Property p : properties) {
            values.put(p.getName(), p.getValue());
        }
    }
    
    public void clearSavedState() {
        values.clear();
    }
    
    public void revertToSavedState() {
        for(Property p : properties) {
            if(!values.containsKey(p.getName())) {
                continue;
            }
            
            Object oldValue = values.get(p.getName());
            p.setValue(oldValue);
        }
    }
}
