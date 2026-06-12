public abstract class Human
{
    private final String username;
    private String password;
    private String type;

    Human(String username, String password , String type)
    {
        this.username = username;
        this.password = password;
        this.type = type;
    }
    public  String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public String getType()
    {
        return type;
    }
}