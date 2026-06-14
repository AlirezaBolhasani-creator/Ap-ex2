import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Manager extends Staff
{
    public Manager(String username, String password, String first_name, String hotel_id,
                   String last_name, String nationalId, int year_of_birth, String address)
    {
        super(username, password, first_name, hotel_id, last_name,
                nationalId, year_of_birth, address, "manager");
    }
    public void addResource(String resource_id, String number_or_name, Integer capacity, Long price,
                                String category_id, String type, String hotel_id)
    {
        List<Resource> resources = HotelSystem.getResources();
        if(HotelSystem.findHotelById(hotel_id) == null )
        {
            System.out.println("not-found");
            return;
        }
        if(!this.getHotel_id().equals(hotel_id))
        {
            System.out.println("permission-denied");
            return;
        }
        if(HotelSystem.findResources(resource_id, hotel_id) != null)
        {
            System.out.println("duplicate-id");
            return;
        }
        if(category_id.equals("null"))
            category_id = "null";
        else
        {
            Category category = HotelSystem.findCategoryById(category_id);
            if(category == null)
            {
                System.out.println("not-found");
                return;
            }
        }
        switch (type) {
            case "suite" -> resources.add(new SpecialSuite(resource_id, number_or_name, capacity,
                    price, category_id, hotel_id));
            case "standard" -> resources.add(new StandardRoom(resource_id, number_or_name, capacity,
                    price, category_id, hotel_id));
            case "timeshare" -> resources.add(new TimeShareRoom(resource_id, number_or_name, capacity,
                    price, category_id, hotel_id));
            case "vip_hall" -> resources.add(new Hall(resource_id, number_or_name, capacity, price, category_id, hotel_id));
            default -> System.out.println("not-found");
        }
        System.out.println("success");
    }
    public void editResource(String resource_id, String number_or_name, Integer capacity, Long price,
                             String category_id, String type, String hotel_id)
    {
        Resource resource = HotelSystem.findResources(resource_id,this.getHotel_id());
        if(resource == null)
        {
            System.out.println("not-found");
            return;
        }
        if(type != null || hotel_id != null)
        {
            System.out.println("permission-denied");
            return;
        }
        if(capacity != null)
        {
            resource.setCapacity(capacity);
        }
        if(price != null)
        {
            resource.setPrice(price);
        }
        if(category_id != null)
        {
            resource.setCategory_id(category_id);
        }
        if(number_or_name != null)
        {
            resource.setNumber_or_name(number_or_name);
        }
        System.out.println("success");
    }
    public void removeResource(String resource_id,  String hotel_id)
    {
        Resource r = HotelSystem.findResources(resource_id, hotel_id);
        if(HotelSystem.findHotelById(hotel_id) == null)
        {
            System.out.println("not-found");
            return;
        }
        if(r == null)
        {
            System.out.println("not-found");
            return;
        } else if (!this.getHotel_id().equals(r.getHotel_id())) {
            System.out.println("permission-denied");
            return;
        }
        List<Reservation> resource_reserve = HotelSystem.findReservationByResourceId(resource_id);
        for(Reservation res : resource_reserve)
        {
            if(res.isActive() && res.getResource_id().equals(resource_id)&& res.getHotel_id().equals(hotel_id))
            {
                System.out.println("not-allowed");
            }
        }
        List<Resource> resources= HotelSystem.getResources();
        resources.remove(r);
        System.out.println("success");
    }
    public void addServiceCatalog(String service_catalog_id, String name, ServiceCatalog.ServiceType service_type,
                                  Integer price, LocalTime start_time, LocalTime end_time, String hotel_id)
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

        if(HotelSystem.findServiceCatalogById(service_catalog_id, hotel_id) != null)
        {
            System.out.println("duplicate-id");
            return;
        }
        List<ServiceCatalog> catalogs = HotelSystem.getServiceCatalogs();
        TimeRange timeRange = new TimeRange(start_time, end_time);
        catalogs.add(new ServiceCatalog(service_catalog_id, name, service_type, price, timeRange, hotel_id));
        System.out.println("success");
    }
    public void editServiceCatalog(String service_id, String name, ServiceCatalog.ServiceType type,
                                   Integer price, LocalTime start, LocalTime end, String hotel_id)
    {
        if(hotel_id != null)
        {
            System.out.println("permission-denied");
            return;
        }
        ServiceCatalog sc = HotelSystem.findServiceCatalogById(service_id, this.getHotel_id());
        if(sc == null)
        {
            System.out.println("not-found");
            return;
        }
        if(name != null)
            sc.setName(name);
        if(type != null)
            sc.setType(type);
        if(price != null)
            sc.setPrice(price);
        if(start != null)
            sc.setStartTime(start);
        if(end != null)
            sc.setEndTime(end);
        System.out.println("success");
    }
    public void removeServiceCatalog(String service_id, String hotel_id)
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
        ServiceCatalog sc = HotelSystem.findServiceCatalogById(service_id, hotel_id);
        if(sc == null)
        {
            System.out.println("not-found");
            return;
        }
        List<ServiceCatalog> catalogs = HotelSystem.getServiceCatalogs();
        catalogs.remove(sc);
        System.out.println("success");
    }
    public void categoryReport(String category_id, String hotel_id)
    {
        Hotel h = HotelSystem.findHotelById(hotel_id);
        if(h == null)
        {
            System.out.println("not-found");
            return;
        }
        if(!this.getHotel_id().equals(hotel_id))
        {
            System.out.println("permission-denied");
            return;
        }
        if(!category_id.equals("null") && HotelSystem.findCategoryById(category_id) == null)
        {
            System.out.println("not-found");
            return;
        }
        List<Resource> resources = HotelSystem.getResources();
        int count = 0;
        int active = 0;
        for(Resource r : resources)
        {
            if(r.getCategory_id().equals(category_id) && r.getHotel_id().equals(hotel_id))
            {
                count++;
                if(r.doesHaveReservation())
                    active++;
            }
        }
        System.out.println(count + " " + active);
    }
    public void hotelReport(String hotel_id)
    {
        Hotel h = HotelSystem.findHotelById(hotel_id);
        if(h == null)
        {
            System.out.println("not-found");
            return;
        }
        if(!this.getHotel_id().equals(hotel_id))
        {
            System.out.println("permission-denied");
            return;
        }
        List<Resource> resources = HotelSystem.getResources();
        int count_room = 0;
        int count_timeshare = 0;
        int count_suite = 0;
        int count_hall = 0;
        for(Resource r : resources)
        {
            if(r.getHotel_id().equals(hotel_id))
            {
                switch(r.getType())
                    {
                    case "standard":
                        count_room++;
                        break;
                    case "timeshare":
                        count_timeshare++;
                        break;
                    case "suite":
                        count_suite++;
                        break;
                    case "vip_hall":
                        count_hall++;
                        break;
                    }
            }
        }
        System.out.println("Rooms: " + count_room + ", Suites: " + count_suite+
                ", Timeshare: "+ count_timeshare + ", VIP Halls: " + count_hall);
    }
    public void checkOutOverdueReport(String hotel_id, LocalDate date, LocalTime time)
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
        List<Reservation> reservations = HotelSystem.getReservations();
        List<Integer> ans =  new ArrayList<Integer>();
        LocalDateTime datetime = LocalDateTime.of(date, time);

        for(Reservation r : reservations)
        {
            if(r.getHotel_id().equals(hotel_id))
            {
                LocalDateTime must_exit = LocalDateTime.of(r.getEnd(), LocalTime.of(12,0));
                if(r.isActive() && must_exit.isBefore(datetime))
                    ans.add(r.getId());

            }
        }
        Collections.sort(ans);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < ans.size(); i++)
        {
            stringBuilder.append(ans.get(i)).append("|");
        }
        if(!ans.isEmpty()) {
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            System.out.println(stringBuilder);
        }
        else
            System.out.println("none");
    }
}
