package Controller;

import Listeners.PlayPanelListener;
import Listeners.SongsTableListener;
import Model.Albums;
import Model.Library;
import Model.Music;
import Model.User;
import Network.Client.MainClient;
import Network.Server.MainServer;
import View.LoginMainFrame;
import View.LoginMainPanel;
import View.MainFrame;
import View.PlayPanel;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Main {
    private static Vector<Music> musics = new Vector<>();
    private static FileAndFolderBrowsing fileAndFolderBrowsing = new FileAndFolderBrowsing();
    private static MainFrame mainFrame;
    private static LoginMainFrame loginMainFrame;
    private static Vector<Library> libraries;
    private static Albums albums;
    private static SongsTableListener songsTableListener = null;
    private static LoadingLibrary loadingLibrary = new LoadingLibrary();
    public static String username;
    public static String ip;
    static int status = 0;

    public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException {
        createDirs();
        int port = 6500;
        try {
            Thread t = new Thread(new MainServer(port));
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void createAndShowGUI() throws IOException, InvalidDataException, UnsupportedTagException, InterruptedException {
        fileAndFolderBrowsing.loadFiles(musics);
        libraries = fileAndFolderBrowsing.loadLibraries();
        albums = new Albums(musics);
        loginMainFrame = new LoginMainFrame();
        mainFrame = new MainFrame(musics);
        mainFrame.getMainPanel().update();
        setLinkers();
        MainClient main = new MainClient(musics, new User("test", null));
        albums.loadAlbums();
        songsTableListener.addSongs(loadingLibrary.generateTable(musics));
        for (Library library : albums.getAlbums())
            System.out.println(library.getName() + " " + library.getMusics().size());
    }

    private static void createDirs() throws IOException {
        makeDir("./Lyrics/");
        makeDir("./Library/");
        makeDir("./SharedMusics/");
        makeDir("./Library/favourites.bin");
        makeDir("./Library/shared playlist.bin");
    }

    private static void makeDir(String path) throws IOException {
        File file = new File(path);
        if (!file.exists())
            if (file.isDirectory())
                file.mkdir();
            else
                file.createNewFile();
    }

    private static void setLinkers() {
        PlayPanelActions playPanelActions = new PlayPanelActions(musics);
        mainFrame.getMainPanel().getPlayPanel().setPlayPanelListener(playPanelActions);
        TopLeftMenuActions topLeftMenuActions = new TopLeftMenuActions(musics, albums);
        mainFrame.getMainPanel().getWestPanel().getMenuForWestPanel().setTopLeftMenuListener(topLeftMenuActions);
        songsTableListener = mainFrame.getMainPanel().getCentrePanel().getSongsMainPanel().getSongsTablePanel();
        topLeftMenuActions.setSongsTableListener(mainFrame.getMainPanel().getCentrePanel().getSongsMainPanel().getSongsTablePanel());
        mainFrame.getMainPanel().getCentrePanel().getSongsMainPanel().getSongsTablePanel().getSongsTable().setSongsTableButtons(playPanelActions);
        mainFrame.getMainPanel().getCentrePanel().getSongsMainPanel().getSongsPanel().setSongsPanelListener(playPanelActions);
    }

    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        Main.status = status;
    }

    public static MainFrame getMainFrame() {
        return mainFrame;
    }
}
