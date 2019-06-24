package Controller;

import javax.swing.*;
import java.util.HashMap;

public class InputMaps extends ComponentInputMap
{
    public InputMaps(JComponent component)
    {
        super(component);
    }

    public void addInputMap(KeyStroke keyStroke, String key)
    {
        this.put(keyStroke,key);
    }
    public void addInputMaps(HashMap<KeyStroke,String> strokes)
    {
        for(KeyStroke stroke:strokes.keySet())
        {
            addInputMap(stroke,strokes.get(stroke));
        }
    }
}
