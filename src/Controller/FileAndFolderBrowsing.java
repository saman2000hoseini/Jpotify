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
    private static final String directory = "./Library/songs.bin";
    private static final String pathsDirectory = "./Library/Paths.bin";
    private ObjectInputStream objectInputStream;
    private FileInputStream fileInputStream;

    public void addFileFolder(String path, Vector<Music> songs) throws IOException, ClassNotFoundException, TagException
    {
        ArrayList<String> paths = new ArrayList<>();
        if (!(new File(path)).isFile())
        {
            Vector<Music> tempMusics = loadingLibrary.loadFilesFromFolders(path);
            for (Music temp : tempMusics)
                if (!songs.contains(temp))
                    songs.add(temp);
            paths.add(path);
        }
        else
        {
            Music temp = loadingLibrary.processFile(path);
            if (!songs.contains(temp))
                songs.add(temp);
        }
        for (Music music : songs)
        {
            if (music.getAddDate() == null)
                music.setAddDate(LocalDateTime.now());
        }
        try
        {
            if (new File(pathsDirectory).exists())
            {
                fileInputStream = new FileInputStream(pathsDirectory);
                objectInputStream = new ObjectInputStream(fileInputStream);
                while (true)
                {
                    String tempPath = (String) objectInputStream.readObject();
                    paths.add(tempPath);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (EOFException e)
        {
            objectInputStream.close();
            fileInputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {
            if (objectInputStream != null)
                objectInputStream.close();
            if (fileInputStream != null)
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
            fileOutputStream = new FileOutputStream(pathsDirectory);
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
        try
        {
            fileOutputStream = new FileOutputStream(directory);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (Music music : songs)
            {
                objectOutputStream.writeObject(music);
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

    public void loadFiles(Vector<Music> songs) throws IOException
    {
        try
        {
            if (new File(directory).exists())
            {
                fileInputStream = new FileInputStream(directory);
                objectInputStream = new ObjectInputStream(fileInputStream);
                while (true)
                {
                    songs.add((Music) objectInputStream.readObject());
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (EOFException e)
        {
            objectInputStream.close();
            fileInputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {
            if (new File(pathsDirectory).exists())
            {
                fileInputStream = new FileInputStream(pathsDirectory);
                objectInputStream = new ObjectInputStream(fileInputStream);
                while (true)
                {
                    String path = (String) objectInputStream.readObject();
                    Vector<Music> tempMusics = loadingLibrary.loadFilesFromFolders(path);
                    for (Music music : tempMusics)
                        if (!songs.contains(music))
                            songs.add(music);
                }
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (EOFException e)
        {

        }
        catch (TagException e)
        {
            e.printStackTrace();
        }
//        System.out.println(songs.size());
    }

    public Vector<Library> loadLibraries() throws IOException
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
                        library = new Library(file.getName().replace(".dat", ""));
                        songs = new ArrayList<>();
                        fileInputStream = new FileInputStream(file);
                        objectInputStream = new ObjectInputStream(fileInputStream);
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
                        if (library != null && songs != null)
                        {
                            library.addMusics(songs);
                            libraries.add(library);
                        }
                        objectInputStream.close();
                        fileInputStream.close();
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
