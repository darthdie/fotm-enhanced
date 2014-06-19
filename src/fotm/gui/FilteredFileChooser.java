
package fotm.gui;

import fotm.ApplicationPreferences;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.imageio.ImageIO;

public class FilteredFileChooser {
    private final FilterExtensionType filterType;
    private final FileChooser fileChooser;
    
    public FilteredFileChooser(FilterExtensionType type) {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        fileChooser.setInitialDirectory(ApplicationPreferences.getLastPath());
        
        filterType = type;
        
        setupChooserFilters();
    }
    
    private void setupChooserFilters() {
        fileChooser.getExtensionFilters().clear();
        
        switch(filterType) {
            case Image:
                List<String> extensions = new ArrayList<>();
                for(String ext : ImageIO.getReaderFileSuffixes()) {
                    extensions.add("*." + ext);
                }
                
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", extensions));
                break;
            case Deck:
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
                break;
        }
    }
    
    public File showOpenDialog(Window ownerWindow) {
        File f = fileChooser.showOpenDialog(ownerWindow);
        ApplicationPreferences.setLastPath(f);
        return f;
    }
}
