public class Hall extends Resource
{
    public Hall(String resource_id, String number_or_name, Integer capacity,
                Long price, String category_id, String hotel_id)
    {
        super(resource_id, number_or_name, capacity, price, category_id, hotel_id, "vip_hall");
    }
}
