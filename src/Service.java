import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
public class Service
{
    private String guest_id;
    private int usage_times;
    private LocalDate usage_date;
    private LocalTime usage_time;
    private  String service_catalog_id;
    public Service(String service_catalog_id, String guest_id, int usage_times,
                   LocalDate usage_date, LocalTime usage_time)
    {
        this.service_catalog_id = service_catalog_id;
        this.guest_id = guest_id;
        this.usage_times = usage_times;
        this.usage_date = usage_date;
        this.usage_time = usage_time;
    }
    public  String getGuest_id()
    {
        return guest_id;
    }
    public String getHotel_id()
    {
        return service_catalog_id;
    }
    public String getService_catalog()
    {
        return service_catalog_id;
    }
    public  int getUsage_times()
    {
        return usage_times;
    }
    public LocalDate getUsage_date()
    {
        return usage_date;
    }
    public LocalTime getUsage_time()
    {
        return usage_time;
    }
}
