import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
public class Admin extends Human
{
    Admin(String username, String password)
    {
        super(username, password, "Admin");
    }
    public void addHotel(String id, String name, String city, int stars,int capacity,String address)
    {
        Hotel hotel = HotelSystem.findHotelById(id);
        if(hotel != null)
        {
            System.out.println("duplicate-id");
            return;
        }
        System.out.println("success");
        Hotel h = new Hotel(id, name, city, stars, capacity, address);
        HotelSystem.getHotels().add(h);
    }
    public void addGuest(String id,String password, String first_name, String last_name,
                          String national_code, String phone_number, String nation)
    {
        Human h = HotelSystem.findHumanByUsername(id);
        if(h!=null)
        {
            System.out.println("duplicate-id");
            return;
        }
        Human g = new Guest(id, password, first_name, last_name, national_code, phone_number, nation);
        HotelSystem.getHuman().add(g);
        System.out.println("success");
    }
    public void editGuest(String userName, String password, String first_name, String last_name,
                          String national_code, String phone_number, String nation)
    {
        Human guest = HotelSystem.findHumanByUsername(userName);
        if(guest==null)
        {
            System.out.println("not-found");
            return;
        }
        Guest g = (Guest) guest;
        if(password != null)
            g.setPassword(password);
        if(first_name != null)
            g.setFirstName(first_name);
        if(last_name != null)
            g.setLastName(last_name);
        if(national_code != null)
            g.setNationalId(national_code);
        if(phone_number != null)
            g.setPhoneNumber(phone_number);
        if(nation != null)
            g.setNation(nation);
        System.out.println("success");
    }
    public void removeGuest(String id)
    {
        List<Human> humans = HotelSystem.getHuman();
        for(Human h : humans)
        {
            if (h.getUsername().equals(id) && h.getType().equals("Guest")) {
                humans.remove(h);
                System.out.println("success");
                return;
            }
        }
        System.out.println("not-found");
    }
    public void addStaff(String username, String password, String first_name, String last_name,
                         String nationalId, int year_of_birth, String address , String type, String hotel_id)
    {
        Human human = HotelSystem.findHumanByUsername(username);
        if(human != null)
        {
            System.out.println("duplicate-id");
            return;
        }
        Hotel hotel = HotelSystem.findHotelById(hotel_id);
        if(hotel == null)
        {
            System.out.println("not-found");
            return;
        }
        List<Human> humans = HotelSystem.getHuman();
        Staff st;
        if(type.equals("manager"))
            st = new Manager(username, password, first_name, hotel_id, last_name, nationalId, year_of_birth, address);
        else if(type.equals("receptionist"))
            st = new Receptionist(username, password, first_name, hotel_id, last_name, nationalId,
                    year_of_birth, address);
        else//not necessarily
        {
            System.out.println("invalid-type");
            return;
        }
        System.out.println("success");
        humans.add(st);
    }
    public void editStaff(String userName, String password, String first_name, String last_name,
                          String national_id, Integer year_of_birth, String address,
                          String type, String hotel_id)
    {
        Human human = HotelSystem.findHumanByUsername(userName);
        if(human == null || human.getType().equals("Admin") || human.getType().equals("Guest"))
        {
            System.out.println("not-found");
            return;
        }
        Staff st = (Staff) human;
        if(password != null)
            st.setPassword(password);
        if(first_name != null)
            st.setFirst_name(first_name);
        if(last_name != null)
            st.setLast_name(last_name);
        if(national_id != null)
            st.setNationalId(national_id);
        if(year_of_birth != null)
            st.setYear_of_birth(year_of_birth);
        if(address != null)
            st.setAddress(address);
        if(type != null)
            st.setType(type);
        if(hotel_id != null)
            st.setHotel_id(hotel_id);
        System.out.println("success");
    }
    public void removeStaff(String userName)
    {
        Human h = HotelSystem.findHumanByUsername(userName);
        if(h == null)
        {
            System.out.println("not-found");
            return;
        }
        if(!h.getType().equals("manager") && !h.getType().equals("receptionist"))
        {
            System.out.println("not-found");
            return;
        }
        HotelSystem.getHuman().remove(h);
        System.out.println("success");
    }
    public void addCategory(String id, String name, String description)
    {
        List<Category> categories = HotelSystem.getCategories();
        for(Category c : categories)
            if(c.getCategory_id().equals(id))
            {
                System.out.println("duplicate-id");
                return;
            }
        Category category;
        if(description != null)
            category = new Category(id, name, description);
        else
            category = new Category(id, name);
        categories.add(category);
        System.out.println("success");
    }
    public void reportRevenue()
    {
        Long total = 0L;
        List<Hotel> hotels = HotelSystem.getHotels();
        List<String> ans =  new ArrayList<>();
        List<Bill> bills = HotelSystem.getBills();
        for(Hotel h : hotels)
        {
            int count  = 0;
            for(Bill b : bills)
            {
                if(b.getHotel_id()!= null && b.getHotel_id().equals(h.getId()))
                {
                    count += b.getPrice();
                    total = total + b.getPrice();
                }
            }
            String s = h.getId() + " " +count;
            ans.add(s);
        }
        Collections.sort(ans);
        System.out.println("TOTAL " + total);
        for(String s : ans)
        {
            System.out.println(s);
        }
    }
    public String getUsername()
    {
        return super.getUsername();
    }
    public String getPassword()
    {
        return super.getPassword();
    }
}