package mario.rm.Menu.Componenti;

import java.util.ArrayList;
import mario.rm.Menu.Specifiche;

/**
 *
 * @author LENOVO
 */
public interface Checkable {
    
    ArrayList<Specifiche> elenco = new ArrayList<>();
    
    Specifiche getChecked();
    
}
