package Model;

import java.util.*;

public class Sort
{
    private ArrayList<Music> musics;

    public Sort(ArrayList<Music> musics)
    {
        this.musics = musics;
    }

    public ArrayList<Music> alphabeticalAscending()
    {

        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getName().compareTo(musics.get(j).getName()) > 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public ArrayList<Music> alphabeticalDescending()
    {

        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getName().compareTo(musics.get(j).getName()) <= 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public ArrayList<Music> alphabeticalAlbumAscending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getAlbum().compareTo(musics.get(j).getAlbum()) > 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public ArrayList<Music> alphabeticalAlbumDescending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getAlbum().compareTo(musics.get(j).getAlbum()) <= 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public ArrayList<Music> alphabeticalArtistAscending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getArtist().compareTo(musics.get(j).getArtist()) > 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public ArrayList<Music> alphabeticalArtistDescending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getArtist().compareTo(musics.get(j).getArtist()) <= 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }

    public HashMap<Integer, Integer> shuffle()
    {
        int count = musics.size();
        HashMap<Integer, Integer> indexes = new HashMap<>();
        Random random = new Random();
        Integer index = 0;
        while (index < count)
        {
            int rnd = random.nextInt(count);
            if (indexes.get(rnd)==null)
            {
                indexes.put(rnd,index);
                index++;
            }
        }
        return indexes;
    }

    public ArrayList<Music> addDateAscending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getAddDate().compareTo(musics.get(j).getAddDate()) < 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public ArrayList<Music> addDateDescending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getAddDate().compareTo(musics.get(j).getAddDate()) >= 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public ArrayList<Music> recentlyPlayedDescending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getLastPlayed().compareTo(musics.get(j).getLastPlayed()) > 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public ArrayList<Music> recentlyPlayedAscending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getLastPlayed().compareTo(musics.get(j).getLastPlayed()) <= 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
}
