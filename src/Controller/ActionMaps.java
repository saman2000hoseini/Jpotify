package Controller;

import javax.swing.*;
import java.util.HashMap;

public class ActionMaps extends ActionMap
{
    public void addActionMap(String key,AbstractAction abstractAction)
    {
        this.put(key,abstractAction);
    }
    public void addActionMaps(HashMap<String,AbstractAction> actions)
    {
        for(String key:actions.keySet())
        {
            addActionMap(key,actions.get(key));
        }
    }
}
