import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class StandardRoom extends Resource
{
    public StandardRoom(String resource_id, String number_or_name,
                        Integer capacity, Long price, String category_id, String hotel_id)
    {
        super(resource_id, number_or_name, capacity, price, category_id, hotel_id, "standard");
    }
    @Override
    public Long cancel(LocalDate cancel_date, LocalTime cancel_time, LocalDate start_res)
    {
        LocalDateTime cancel =  LocalDateTime.of(cancel_date, cancel_time);
        LocalTime start_time = LocalTime.of(14, 0);
        LocalDateTime start = LocalDateTime.of(start_res, start_time);
        long hours = ChronoUnit.HOURS.between(cancel, start);
        if(hours <= 48)
        {
            return super.getPrice();
        }
        return 0L;
    }
    @Override
    public Long lateCheckOut(LocalDate check_out_date, LocalTime check_out_time, LocalDate end_res)
    {
        LocalDateTime check_out = LocalDateTime.of(check_out_date, check_out_time);
        LocalDateTime end = LocalDateTime.of(end_res, LocalTime.of(14, 0));
        long hours = ChronoUnit.HOURS.between(check_out, end);
        if(hours >= 0)
        {
            return (Long)(long) (1.5) * super.getPrice();
        }
        return 0L;
    }

}
