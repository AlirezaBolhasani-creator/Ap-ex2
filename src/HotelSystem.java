import java.util.ArrayList;
import java.util.List;

public class HotelSystem
{
    //fields
    private static List<Resource> resources = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static List<Human> humans = new ArrayList<>();
    private static List<Hotel> hotels = new ArrayList<>();
    private static List<Category> categories = new ArrayList<>();
    private static List<ServiceCatalog>  serviceCatalogs = new ArrayList<>();
    private static List<Service> services = new ArrayList<>();
    private static int reservationsCount = 0;
    private static List<Bill> bills = new ArrayList<>();
    private static List<Comment> comments = new ArrayList<>();
    //setting admin
    public static void setAdmin(String username, String password)
    {
        Human h = new Admin(username, password);
        humans.add(h);
    }
    //getter
    public static List<Hotel> getHotels()
    {
        return hotels;
    }
    public static List<Resource> getResources()
    {
        return resources;
    }
    public static List<Reservation>  getReservations()
    {
        return reservations;
    }
    public static List<Human> getHuman()
    {
        return humans;
    }
    public static List<Category> getCategories()
    {
        return categories;
    }
    public static List<ServiceCatalog> getServiceCatalogs()
    {
        return serviceCatalogs;
    }
    public static List<Service> getServices()
    {
        return services;
    }
    public static List<Bill> getBills()
    {
        return bills;
    }
    public static List<Comment> getComments()
    {
        return comments;
    }
    public static int   getNewReserveId()
    {
        HotelSystem.reservationsCount++;
        return HotelSystem.reservationsCount;
    }
    public static int getReservationsCount()
    {
        return reservationsCount;
    }
    //finder
    public static Hotel findHotelById(String id)
    {
        for (Hotel hotel : hotels)
            if(hotel.getId().equals(id))
                return hotel;
        return null;
    }
    public static Human findHumanByUsername(String username)
    {
        for(Human h : humans)
            if(h.getUsername().equals(username))
                return h;
        return null;
    }
    public static List<Reservation> findReservationByResourceId(String resource_id)
    {
        List<Reservation> reservations_here = new ArrayList<>();
        for (Reservation reservation : reservations)
        {
            if(reservation.getResource_id().equals(resource_id))
            {
                reservations_here.add(reservation);
            }
        }
        return reservations_here;
    }
    public static Resource findResources(String resource_id, String hotel_id)
    {
        for(Resource r : resources)
        {
            if(r.getHotel_id().equals(hotel_id) &&  r.getResource_id().equals(resource_id))
            {
                return r;
            }
        }
        return null;
    }
    public static ServiceCatalog findServiceCatalogById(String service_catalog_id, String hotel_id)
    {
        for(ServiceCatalog sc : serviceCatalogs)
        {
            if(sc.getId().equals(service_catalog_id) && sc.getHotel_id().equals(hotel_id))
            {
                return sc;
            }
        }
        return null;
    }
    public static Category findCategoryById(String category_id)
    {
        for(Category c : categories)
            if(c.getCategory_id().equals(category_id))
                return c;
        return null;
    }
    public static Reservation findReservationById(int reservation_id)
    {
        for(Reservation r : reservations)
            if(r.getId() == reservation_id)
                return r;
        return null;
    }
    //auth
    public static boolean auth(String username, String password, String type)
    {
        Human h = findHumanByUsername(username);
        if(h == null)
        {
            System.out.println("not-found");
            return false;
        }
        if(!h.getPassword().equals(password))
        {
           System.out.println("invalid-pass");
           return false;
        }
        if(type.equals("Staff"))
        {
            if(!h.getType().equals("manager") && !h.getType().equals("receptionist"))
            {
                System.out.println("permission-denied");
                return false;
            }
        }
        else if(!h.getType().equals(type))
        {
            System.out.println("permission-denied");
            return false;
        }
        return true;
    }
}
