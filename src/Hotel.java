public class Hotel
{
    private String id;
    private String name;
    private String city;
    private int stars;
    private int capacity;
    private String address;
    public Hotel(String id, String name, String city, int stars, int capacity,String address)
    {
        this.id = id;
        this.name = name;
        this.city = city;
        this.stars = stars;
        this.capacity = capacity;
    }
    public String  getId()
    {
        return id;
    }
    public String getName() {
        return name;
    }
}
