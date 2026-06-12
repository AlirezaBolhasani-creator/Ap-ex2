import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation
{
    private String guest_id;
    private String hotel_id;
    private String resource_id;
    private int id;
    private LocalDate start;
    private LocalDate end;
    private LocalDate sign_date;
    private LocalTime sign_time;
    public Reservation(String guest_id, String hotel_id, String resource_id, LocalDate start, LocalDate end,
                       LocalDate sign_date, LocalTime sign_time)
    {
        this.guest_id = guest_id;
        this.hotel_id = hotel_id;
        this.resource_id = resource_id;
        this.start = start;
        this.end = end;
        this.sign_date = sign_date;
        this.sign_time = sign_time;
        id = HotelSystem.getNewReserveId();
    }
    public boolean isActive()//not canceled, not checkout
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
    public LocalDate getStart()
    {
        return start;
    }
    public LocalDate getEnd()
    {
        return end;
    }
}
