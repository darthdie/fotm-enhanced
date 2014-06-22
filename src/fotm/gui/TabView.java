package fotm.gui;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.ToolBar;

public interface TabView {
    public ObjectProperty<Tab> tabProperty();
    public ObjectProperty<ToolBar> toolbarProperty();
}