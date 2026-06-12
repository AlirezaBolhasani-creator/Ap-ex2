public abstract class Resource
{
    private final String resource_id;
    private String number_or_name;
    private Integer capacity;
    private Long price;
    private String category_id;
    private final String hotel_id;
    private final String type;
    public Resource(String resource_id, String number_or_name, Integer capacity,
                    Long price, String category_id, String hotel_id,  String type)
    {
        this.resource_id = resource_id;
        this.number_or_name = number_or_name;
        this.capacity = capacity;
        this.price = price;
        this.category_id = category_id;
        this.hotel_id = hotel_id;
        this.type = type;
    }
    public void setCapacity(Integer capacity)
    {
        this.capacity = capacity;
    }
    public void setPrice(Long price)
    {
        this.price = price;
    }
    public void setCategory_id(String category_id)
    {
        this.category_id = category_id;
    }
    public void setNumber_or_name(String number_or_name)
    {
        this.number_or_name = number_or_name;
    }
    public String getResource_id()
    {
        return resource_id;
    }
    public String getHotel_id()
    {
        return hotel_id;
    }
    public  String getType()
    {
        return type;
    }
}
