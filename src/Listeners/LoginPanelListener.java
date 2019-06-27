package Listeners;

import java.io.IOException;
import java.util.ArrayList;

public interface LoginPanelListener
{
    void login(String user, ArrayList<String> friends) throws IOException;
}
