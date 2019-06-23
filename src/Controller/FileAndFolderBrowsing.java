package Controller;

import Model.Library;
import Model.Music;
import org.farng.mp3.TagException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;

public class FileAndFolderBrowsing
{
    private LoadingLibrary loadingLibrary = new LoadingLibrary();
    private static final String directory = "./Library/songs.dat";

    public void addFileFolder(String path, Vector<Music> songs) throws IOException, ClassNotFoundException, TagException
    {
        if (!(new File(path)).isFile())
        {
            songs.addAll(loadingLibrary.loadFiles(path));
        }
        else
        {
            songs.add(loadingLibrary.processFile(path));
        }
        for (Music music : songs)
        {
            music.setAddDate(LocalDateTime.now());
        }
        FileInputStream fileInputStream = null;
        ArrayList<String> paths = new ArrayList<>();
        ObjectInputStream objectInputStream = null;
        try
        {
            fileInputStream = new FileInputStream(directory);
            objectInputStream = new ObjectInputStream(fileInputStream);
            while (true)
            {
                String tempPath = (String) objectInputStream.readObject();
                paths.add(tempPath);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (EOFException e)
        {

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        paths.add(path);
        try
        {
            objectInputStream.close();
            fileInputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try
        {
            fileOutputStream = new FileOutputStream(directory);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (String pth : paths)
            {
                objectOutputStream.writeObject(pth);
            }
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadFiles(Vector<Music> songs)
    {
        try
        {
            FileInputStream fileInputStream = new FileInputStream(directory);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while (true)
            {
                songs.add((Music) objectInputStream.readObject());
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public Vector<Library> loadLibraries()
    {
        Vector<Library> libraries = new Vector<>();
        Library library = null;
        ArrayList<Music> songs = null;
        File folder = new File("./Library/");
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles.length > 0)
            for (File file : listOfFiles)
            {
                if (file.getName() != "songs.dat")
                    try
                    {
                        library = new Library(file.getName().replace(".dat",""));
                        songs = new ArrayList<>();
                        FileInputStream fileInputStream = new FileInputStream(file);
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                        while (true)
                        {
                            songs.add((Music) objectInputStream.readObject());
                        }
                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    catch (EOFException e)
                    {
                        if (library!=null&& songs!=null)
                        {
                            library.addMusics(songs);
                            libraries.add(library);
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch (ClassNotFoundException e)
                    {
                        e.printStackTrace();
                    }

            }
        return libraries;
    }
}
