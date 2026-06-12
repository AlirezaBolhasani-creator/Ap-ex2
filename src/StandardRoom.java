public class StandardRoom extends Resource
{
    public StandardRoom(String resource_id, String number_or_name,
                        Integer capacity, Long price, String category_id, String hotel_id)
    {
        super(resource_id, number_or_name, capacity, price, category_id, hotel_id, "standard");
    }
}
