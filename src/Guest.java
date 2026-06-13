import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
    public void addReservation(String hotel_id,String resource_id,LocalDate start,LocalDate end,LocalDate sign_date,LocalTime sign_time)
    {
        int count = 0;
        for(Reservation reservation: HotelSystem.getReservations())
            if(reservation.getGuest_id().equals(this.getUsername()) )
                count++;
        if(count >= 3)
        {
            System.out.println("not-allowed");
            return;
        }
        if(this.payment < 0)
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
        else if(resource.isBusy(start, end))
        {
            System.out.println("not-allowed");
            return;
        }
        HotelSystem.getReservations().add(new Reservation(this.getUsername(), hotel_id,  resource_id,
                start, end, sign_date, sign_time));
        System.out.println("success "+ HotelSystem.getReservationsCount());

    }
    public void cancelReservation(int reservation_id, LocalDate cancel_date, LocalTime cancel_time)
    {
        Reservation reservation = HotelSystem.findReservationById(reservation_id);
        if(reservation == null)
        {
            System.out.println("not-found");
            return;
        }
        if(!reservation.getGuest_id().equals(this.getUsername()))
        {
            System.out.println("not-found");
            return;
        }
        if(!reservation.isActive() || reservation.isCheckedIn())
        {
            System.out.println("not-allowed");
            return;
        }
        String hotel_id = reservation.getHotel_id();
        String resource_id = reservation.getResource_id();
        Resource resource = HotelSystem.findResources(resource_id, hotel_id);

        assert resource != null;
        Long cancel_penalty = resource.cancel(cancel_date, cancel_time, reservation.getStart());
        if(cancel_penalty == 0L)
        {
            System.out.println("success");
        }
        else
        {
            System.out.println(cancel_penalty);
        }
        reservation.setCanceled();
        this.payment -=  cancel_penalty;
        HotelSystem.getBills().add(new Bill(this.getUsername(), cancel_penalty, "Penalty_Cancellation", cancel_date,
                cancel_time));
    }
    public void viewBalance()
    {
        System.out.println(this.payment * -1);
        List<Bill> bills = HotelSystem.getBills();
        for(Bill bill: bills)
            if(bill.getGuest_id().equals(this.getUsername()))
                System.out.println(bill);
    }
    public void pay(long amount, LocalDate payment_date, LocalTime payment_time)
    {
        this.payment -= amount;
        System.out.println("success");
        HotelSystem.getBills().add(new Bill(this.getUsername(), amount, "Pay", payment_date, payment_time));
    }
    public void changePaymentCheckOut(long amount)
    {
        this.payment += amount;
    }
    public void addCommentForResource(String hotel_id, String resource_id, String comment)
    {
        Resource resource = HotelSystem.findResources(resource_id, hotel_id);
        if(resource == null)
        {
            System.out.println("not-found");
        }
        HotelSystem.getComments().add(new Comment(hotel_id, resource_id, comment));
        System.out.println("success");
    }
    public void useService(String hotel_id, String service_id, int usage_times, LocalDate usage_date, LocalTime usage_time)
    {
        boolean can_use = false;
        if(HotelSystem.findHotelById(hotel_id) == null)
        {
            System.out.println("not-found");
            return;
        }
        ServiceCatalog serviceCatalog = HotelSystem.findServiceCatalogById(service_id, hotel_id);
        if(serviceCatalog== null)
        {
            System.out.println("not-found");
            return;
        }
        LocalTime start = serviceCatalog.getTimeRange().getStart();
        LocalTime end = serviceCatalog.getTimeRange().getEnd();
        boolean isBetween;

        if (start.isBefore(end)) {
            isBetween = !usage_time.isBefore(start) && !usage_time.isAfter(end);
        } else {
            isBetween = !usage_time.isBefore(start) || !usage_time.isAfter(end);
        }
        if(!isBetween)
        {
            System.out.println("not-allowed");
            return;
        }
        for(Reservation reservation: HotelSystem.getReservations())
        {
            if(reservation.getGuest_id().equals(this.getUsername()) && reservation.isCheckedIn() && reservation.isActive())
            {
                can_use = true;
            }
        }
        if(!can_use)
        {
            System.out.println("not-allowed");
        }
        if(usage_times <= 0)
        {
            System.out.println("not-allowed");
            return;
        }
        System.out.println("success");
        HotelSystem.getServices().add(new Service(service_id, this.getUsername(), usage_times, usage_date, usage_time));
    }
    public void buyTimeShare(String hotel_id, String resource_id, LocalDate date)
    {
        Resource resource = HotelSystem.findResources(resource_id, hotel_id);
        if(resource == null)
        {
            System.out.println("not-found");
            return;
        }
        if(!resource.getType().equals("timeshare"))
        {
            System.out.println("not-allowed");
            return;
        }
        TimeShareRoom room = (TimeShareRoom)resource;
        if(room.getIsSold())
        {
            System.out.println("not-allowed");
            return;
        }
        if(this.payment * (-1) < room.getPrice())
        {
            System.out.println("not-allowed");
            return;
        }
        this.payment -= room.getPrice();
        HotelSystem.getBills().add(new Bill(this.getUsername(), room.getPrice(), "Bill_Timeshare", date,
                LocalTime.of(12, 0)));
        System.out.println("success");
    }
}
