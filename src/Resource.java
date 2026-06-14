import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public abstract class Resource
{
    private final String resource_id;
    private String number_or_name;
    private Integer capacity;
    private Long price;
    private String category_id;
    private final String hotel_id;
    private final String type;
    public Resource(String resource_id, String number_or_name, Integer capacity,
                    Long price, String category_id, String hotel_id,  String type)
    {
        this.resource_id = resource_id;
        this.number_or_name = number_or_name;
        this.capacity = capacity;
        this.price = price;
        this.category_id = category_id;
        this.hotel_id = hotel_id;
        this.type = type;
    }
    public void setCapacity(Integer capacity)
    {
        this.capacity = capacity;
    }
    public void setPrice(Long price)
    {
        this.price = price;
    }
    public void setCategory_id(String category_id)
    {
        this.category_id = category_id;
    }
    public void setNumber_or_name(String number_or_name)
    {
        this.number_or_name = number_or_name;
    }
    public String getResource_id()
    {
        return resource_id;
    }
    public String getHotel_id()
    {
        return hotel_id;
    }
    public  String getType()
    {
        return type;
    }
    public boolean isBusy(LocalDate start, LocalDate end)
    {
        List<Reservation> reservations = HotelSystem.getReservations();
        for(Reservation reservation : reservations)
        {
            if(reservation.getResource_id().equals(this.resource_id))
            {
                if(reservation.isActive() &&
                        ((reservation.getStart().isAfter(start) || reservation.getStart().isEqual(start))
                                && reservation.getStart().isBefore(end)
                        || reservation.getEnd().isAfter(start)  &&
                                (reservation.getEnd().isBefore(end) || reservation.getEnd().isEqual(end)))
                )
                {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean doesHaveReservation()
    {
        List<Reservation> reservations = HotelSystem.getReservations();
        for(Reservation reservation : reservations)
        {
            if(reservation.getResource_id().equals(this.resource_id) && reservation.isActive())
                {
                return true;
                }
        }
        return false;
    }
    public Long  getPrice()
    {
        return price;
    }
    public String getCategory_id()
    {
        return category_id;
    }
    public String getNumber_or_name()
        {
        return number_or_name;
        }
    abstract public Long cancel(LocalDate cancel_date, LocalTime cancel_time, LocalDate start_res);
    abstract public Long lateCheckOut(LocalDate check_out_date, LocalTime check_out_time, LocalDate end_res);
}
