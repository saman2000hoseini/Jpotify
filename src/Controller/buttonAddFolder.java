package Controller;

import java.io.*;
import java.util.ArrayList;

public class buttonAddFolder
{
    public void addFolder(String path, ArrayList<String> songs)
    {
        songs.addAll(new LoadingLibrary().loadFiles(path));
        FileInputStream fileInputStream = null;
        ArrayList<String> paths = new ArrayList<>();
        ObjectInputStream objectInputStream = null;
        try
        {
            fileInputStream = new FileInputStream("./src/Library/songs.dat");
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
            fileOutputStream = new FileOutputStream("./src/Library/songs.dat");
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
}
