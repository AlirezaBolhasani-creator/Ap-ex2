import java.util.ArrayList;
import java.util.Collection;
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
    public void setType_of_staff(String type)
    {
        this.type_of_staff = type;
        super.setType(type);
    }
    public String getType_of_staff()
    {
        return type_of_staff;
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
        return;
    }
}

