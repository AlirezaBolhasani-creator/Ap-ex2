import javax.swing.text.Caret;
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
    private boolean is_checked_in =  false;
    private boolean is_checked_out =  false;
    private boolean is_canceled =  false;
    LocalTime check_in_time;
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
        return !this.is_checked_out && !this.is_canceled;
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
    public int  getId()
    {
        return id;
    }
    public String getGuest_id()
    {
        return guest_id;
    }
    public boolean isCheckedIn()
    {
        return is_checked_in;
    }
    public void setCanceled()
    {
        this.is_canceled = true;
    }
    public void setCheckedIn()
    {
        this.is_checked_in = true;
    }
    public void setCheck_in_time(LocalTime time)
    {
        this.check_in_time = time;
    }
}
