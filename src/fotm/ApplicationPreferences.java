package fotm;

import java.io.File;
import java.util.prefs.Preferences;

public class ApplicationPreferences {
    private static final Preferences prefs;
    
    static {
        prefs = Preferences.userRoot().node(ApplicationPreferences.class.getName());
    }
    
    public static File getLastPath() {
        return new File(prefs.get("lastPath", "."));
    }
    
    public static void setLastPath(File file) {
        if(file == null) {
            return;
        }
        
        String path;
        if(file.isFile()) {
            path = file.getParent();
        }
        else {
            path = file.getAbsolutePath();
        }
        
        prefs.put("lastPath", path);
    }
}