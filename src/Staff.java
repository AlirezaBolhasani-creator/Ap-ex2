import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Staff extends Human
{
    private String hotel_id;
    private String type_of_staff;
    private String first_name;
    private String last_name;
    private String nationalId;
    private int year_of_birth;
    private String address;
    public Staff(String username, String password, String first_name, String hotel_id,
                 String last_name, String nationalId, int year_of_birth, String address, String type_of_staff)
    {
        super(username, password, type_of_staff);
        this.first_name = first_name;
        this.last_name = last_name;
        this.nationalId = nationalId;
        this.year_of_birth = year_of_birth;
        this.address = address;
        this.hotel_id = hotel_id;
    }
    public String getHotel_id()
    {
        return hotel_id;
    }
    public void setHotel_id(String h)
    {
        this.hotel_id = h;
    }
    public void setYear_of_birth(int year_of_birth)
    {
        this.year_of_birth = year_of_birth;
    }
    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }
    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }
    public void setNationalId(String nationalId)
    {
        this.nationalId = nationalId;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public void searchGuest(String guest_name)
    {
        List<String> userNames = new ArrayList<>();
        List<Human> humans = HotelSystem.getHuman();
        for(Human h : humans)
        {
            if(h.getType().equals("Guest"))
            {
                Guest g = (Guest)h;
                String insensitive_first = g.getFirstName().toLowerCase();
                String insensitive_last = g.getLastName().toLowerCase();
                String insensitive_name = guest_name.toLowerCase();
                if(insensitive_last.contains(insensitive_name) || insensitive_first.contains(insensitive_name))
                    {
                        userNames.add(g.getUsername());
                    }
            }
        }
        Collections.sort(userNames);
        StringBuilder s = new StringBuilder();
        for(String u : userNames)
            s.append(u).append("|");
        s.deleteCharAt(s.length()-1);
        System.out.println(s);
    }
    public void checkIn(String hotel_id, String resource_id, int reserve_id, LocalDate check_date, LocalTime check_time)
    {
        if(HotelSystem.findHotelById(hotel_id) == null)
        {
            System.out.println("not-found");
            return;
        }
        if(!this.getHotel_id().equals(hotel_id))
        {
            System.out.println("permission-denied");
            return;
        }
        if(HotelSystem.findResources(resource_id, hotel_id) == null)
        {
            System.out.println("not-found");
            return;
        }
        Reservation reservation = HotelSystem.findReservationById(reserve_id);
        if(reservation == null)
        {
            System.out.println("not-found");
            return;
        }
        if(!reservation.getResource_id().equals(resource_id))
        {
            System.out.println("not-found");
        }
        if(reservation.isCheckedIn())
        {
            System.out.println("not-allowed");
            return;
        }
        if(!reservation.isActive())
        {
            System.out.println("not-allowed");
            return;
        }
        if(!reservation.getStart().isEqual(check_date))
        {
            System.out.println("not-allowed");
            return;
        }
        System.out.println("success");
        reservation.setCheckedIn();
        reservation.setCheck_in_time(check_time);
    }
    public void checkOut(String hotel_id, String resource_id,int reserve_id, LocalDate check_out_date, LocalTime check_out_time)
    {
        if(HotelSystem.findHotelById(hotel_id) == null)
        {
            System.out.println("not-found");
            return;
        }
        if(!this.getHotel_id().equals(hotel_id))
        {
            System.out.println("permission-denied");
            return;
        }
        if(HotelSystem.findResources(resource_id, hotel_id) == null)
        {
            System.out.println("not-found");
            return;
        }
        Reservation reservation = HotelSystem.findReservationById(reserve_id);
        if(reservation == null)
        {
            System.out.println("not-found");
            return;
        }
        if(!reservation.getResource_id().equals(resource_id))
        {
            System.out.println("not-found");
        }
        if(!reservation.isCheckedIn())
        {
            System.out.println("not-allowed");
            return;
        }
        if(!reservation.isActive())
        {
            System.out.println("not-allowed");
            return;
        }
        Resource resource = HotelSystem.findResources(resource_id, hotel_id);
        Long late = resource.lateCheckOut(check_out_date, check_out_time, reservation.getEnd());
        long nights;
        if(check_out_date.isBefore(reservation.getEnd()))
        {
            nights = ChronoUnit.DAYS.between(reservation.getStart(), reservation.getEnd());
        }
        else {
            nights = ChronoUnit.DAYS.between(reservation.getStart(), check_out_date);
        }
        long calculate_price = nights * resource.getPrice();
        long calculate_services_price = 0;
        List<Service> services = HotelSystem.getServices();
        for(Service s : services)
        {
            if(s.getGuest_id().equals(reservation.getGuest_id()))
            {
                if((s.getUsage_date().isBefore(check_out_date)|| s.getUsage_date().isEqual(check_out_date))
                && (s.getUsage_date().isAfter(reservation.getStart()) || s.getUsage_date().isEqual(reservation.getStart())))
                {
                    calculate_services_price += (long) s.getUsage_times() * s.getService_catalog().getPrice();
                }
            }
        }
        long final_price = late + calculate_services_price + calculate_price;
        System.out.println(final_price);
        reservation.setCheckOut();
        Guest g = (Guest) HotelSystem.findHumanByUsername(reservation.getGuest_id());
        g.changePaymentCheckOut(final_price);
        HotelSystem.getBills().add(new Bill(reservation.getGuest_id(), final_price, "Checkout_Bill", check_out_date,
                check_out_time));
    }
}

