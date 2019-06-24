package Model;

import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable
{
    private String userName;
    private String ip;
    private ObjectOutputStream objectOutputStream;

    public User(String userName, String ip)
    {
        this.userName = userName;
        this.ip = ip;
    }

    public ObjectOutputStream getObjectOutputStream()
    {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream)
    {
        this.objectOutputStream = objectOutputStream;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getIp()
    {
        return ip;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof User && ((User) obj).getUserName().equals(this.getUserName()))
            return true;
        return false;
    }
}
