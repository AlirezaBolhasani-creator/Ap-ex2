import java.time.LocalDate;
import java.time.LocalTime;
public class TimeShareRoom extends Resource
{
    public TimeShareRoom(String resource_id, String number_or_name, Integer capacity,
                         Long price, String category_id, String hotel_id)
    {
        super(resource_id, number_or_name, capacity, price, category_id, hotel_id, "timeshare");
    }
    @Override
    public Long cancel(LocalDate cancel_date, LocalTime cancel_time, LocalDate start_res)
    {
        return 0L;
    }
}