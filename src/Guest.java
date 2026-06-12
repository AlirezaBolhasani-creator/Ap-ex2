import java.time.LocalDate;
import java.time.LocalTime;

public class Guest extends Human
{
    private String first_name;
    private String last_name;
    private String national_id;
    private String phone_number;
    private String nation;
    private Long payment;
    public Guest(String username, String password, String first_name, String last_name, String national_id
            ,String phone_number, String nation)
    {
        super(username, password, "Guest");
        this.first_name = first_name;
        this.last_name = last_name;
        this.national_id = national_id;
        this.phone_number = phone_number;
        this.nation = nation;
        this.payment = 0L;
    }
    public void setLastName(String last_name)
    {
        this.last_name = last_name;
    }
    public void setFirstName(String first_name)
    {
        this.first_name = first_name;
    }
    public void setNationalId(String national_id)
    {
        this.national_id = national_id;
    }
    public void  setPhoneNumber(String phone_number)
    {
        this.phone_number = phone_number;
    }
    public void setNation(String nation)
    {
        this.nation = nation;
    }
    public String getFirstName()
    {
        return first_name;
    }
    public String getLastName()
    {
        return last_name;
    }
    public void addReservation(String hotel_id, String resource_id, LocalDate start, LocalDate end,
                               LocalDate sign_date, LocalTime sign_time)
    {
        int count = 0;
        for(Reservation reservation: HotelSystem.getReservations())
            if(reservation.getHotel_id().equals(hotel_id))
                count++;
        if(count >= 3)
        {
            System.out.println("not-allowed");
            return;
        }
        if(this.payment > 0)
        {
            System.out.println("not-allowed");
            return;
        }
        if(start.isAfter(end))
        {
            System.out.println("not-allowed");
            return;
        }
        if(HotelSystem.findHotelById(hotel_id) == null)
        {
            System.out.println("not-found");
            return;
        }
        Resource resource = HotelSystem.findResources(resource_id, hotel_id);
        if(resource == null)
        {
            System.out.println("not-found");
            return;
        }
        else if(!resource.getType().equals("standard") && !resource.getType().equals("suite"))
        {
            System.out.println("not-allowed");
            return;
        }

        HotelSystem.getReservations().add(new Reservation(this.getUsername(), hotel_id,  resource_id,
                start, end, sign_date, sign_time));
        System.out.println("success "+ HotelSystem.getReservationsCount());

    }
}
