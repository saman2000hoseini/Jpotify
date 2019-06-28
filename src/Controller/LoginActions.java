package Controller;

import Listeners.LoginPanelListener;
import Listeners.UserLoginListener;
import Model.User;
import Network.Client.MainClient;
import View.MainFrame;

import java.io.IOException;
import java.util.ArrayList;

public class LoginActions implements LoginPanelListener
{
    private UserLoginListener userLoginListener = null;
    @Override
    public void login(String user, ArrayList<String> friends, MainFrame mainFrame) throws IOException
    {

        Main.mainClient = new MainClient(PlayPanelActions.playlist,new User(user, null),mainFrame);
        Main.mainClient.getSharing().setAddPlayingMusic(mainFrame.getMainPanel().getFriendsPanel());
        mainFrame.getMainPanel().getFriendsPanel().setRequestToGetMusic(Main.mainClient.getSharing());
        Main.mainClient.getSharing().setAddPlayingMusic(mainFrame.getMainPanel().getPlayPanel().getSongPanelForPlayPanel());

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
