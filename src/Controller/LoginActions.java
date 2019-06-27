package Controller;

import Listeners.LoginPanelListener;
import Listeners.UserLoginListener;
import Model.User;
import Network.Client.MainClient;

import java.io.IOException;
import java.util.ArrayList;

public class LoginActions implements LoginPanelListener
{
    private UserLoginListener userLoginListener = null;
    @Override
    public void login(String user, ArrayList<String> friends) throws IOException
    {
        Main.mainClient = new MainClient(PlayPanelActions.playlist,new User(user, null));
        for(String ip:friends)
            Main.mainClient.addFriend(ip);
//            System.out.println(ip);
        userLoginListener.setUser(user);
    }

    public void setUserLoginListener(UserLoginListener userLoginListener)
    {
        this.userLoginListener = userLoginListener;
    }
}
