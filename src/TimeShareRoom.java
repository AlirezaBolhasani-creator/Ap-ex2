import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeShareRoom extends Resource
{
    private boolean is_sold;
    public TimeShareRoom(String resource_id, String number_or_name, Integer capacity,
                         Long price, String category_id, String hotel_id)
    {
        super(resource_id, number_or_name, capacity, price, category_id, hotel_id, "timeshare");
        is_sold = false;
    }
    @Override
    public Long cancel(LocalDate cancel_date, LocalTime cancel_time, LocalDate start_res)
    {
        return 0L;
    }
    @Override
    public Long lateCheckOut(LocalDate check_out_date, LocalTime check_out_time, LocalDate end_res)
    {
        return 0L;
    }
    public boolean getIsSold()
    {
        return is_sold;
    }
    public void setIsSold(boolean is_sold)
    {
        this.is_sold = is_sold;
    }
}