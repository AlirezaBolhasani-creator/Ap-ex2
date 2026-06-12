public class Reservation
{
    private String hotel_id;
    private String resource_id;
    public String id;
    public boolean isActive()
    {
        return true;
    }
    public  String getHotel_id()
    {
        return hotel_id;
    }
    public String getResource_id()
    {
        return resource_id;
    }
}
