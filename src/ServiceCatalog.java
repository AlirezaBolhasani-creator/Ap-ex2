import java.time.LocalTime;

public class ServiceCatalog
{
    private String id;//can be different in other hotels
    private String name;
    private int price;
    private TimeRange timeRange;
    public static enum ServiceType {
        RESTAURANT, SPA, GYM, CONFERENCE
    }
    private ServiceType type;
    private String hotel_id;
    public ServiceCatalog(String id, String name, ServiceType type, int price,
                          TimeRange timeRange, String hotel_id)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.timeRange = timeRange;
        this.hotel_id = hotel_id;
    }
    public String getId()
    {
        return id;
    }
    public String getHotel_id()
    {
        return hotel_id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getPrice()
    {
        return price;
    }
    public void setPrice(int price)
    {
        this.price = price;
    }
    public TimeRange getTimeRange()
    {
        return timeRange;
    }
    public void setType(ServiceType type)
    {
        this.type = type;
    }
    public void setStartTime(LocalTime startTime)
    {
        this.getTimeRange().setStart(startTime);
    }
    public void setEndTime(LocalTime endTime)
    {
        this.getTimeRange().setEnd(endTime);
    }
    public static ServiceType getServiceTypeByString(String type)
    {
        return switch (type) {
            case "restaurant" -> ServiceType.RESTAURANT;
            case "spa" -> ServiceType.SPA;
            case "gym" -> ServiceType.GYM;
            case "conference" -> ServiceType.CONFERENCE;
            default -> null;
        };
    }
}