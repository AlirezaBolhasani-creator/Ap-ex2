import java.time.LocalDate;
import java.time.LocalTime;

public class Bill {
    private String guest_id;
    private Long price;
    private String type;
    private LocalDate date;
    private LocalTime time;
    public Bill(String guest_id, Long price, String type , LocalDate bill_date, LocalTime bill_time) {
        this.guest_id = guest_id;
        this.price = price;
        this.type = type;
        this.date = bill_date;
        this.time = bill_time;
    }
    public String getType()
    {
        return type;
    }
    public String getGuest_id()
    {
        return guest_id;
    }
    public  Long getPrice()
    {
        return price;
    }
    public  LocalDate getDate()
    {
        return date;
    }
    public  LocalTime getTime()
    {
        return time;
    }
    @Override
    public String toString()
    {
        return this.getDate() + " " + this.getTime() + " "+ this.getType() + " = " + this.getPrice();
    }
}
