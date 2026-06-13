import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
public class SpecialSuite extends Resource
{
    public SpecialSuite(String resource_id, String number_or_name, Integer capacity
            , Long price, String category_id, String hotel_id)
    {
        super(resource_id, number_or_name, capacity, price, category_id, hotel_id, "suite");
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
            double ans =super.getPrice() * 1.5;
            return (Long) (long) ans;
        }
        return 0L;
    }
    public Long lateCheckOut(LocalDate check_out_date, LocalTime check_out_time, LocalDate end_res)
    {
        LocalDateTime check_out = LocalDateTime.of(check_out_date, check_out_time);
        LocalDateTime end = LocalDateTime.of(end_res, LocalTime.of(12, 0));
        long hours = ChronoUnit.HOURS.between(check_out, end);
        if(hours >= 1 && end.isBefore(check_out))
        {
            return (Long)(long) (2) * super.getPrice();
        }
        return 0L;
    }
}