import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Human
{
    private final String username;
    private String password;
    private String type;

    Human(String username, String password , String type)
    {
        this.username = username;
        this.password = password;
        this.type = type;
    }
    public  String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public String getType()
    {
        return type;
    }
    public static void search(String name) {
        name = name.toLowerCase();
        //name could be a hotel name or category name or resource name
        List<Hotel>  hotels = HotelSystem.getHotels();
        List<Category>  categories = HotelSystem.getCategories();
        List<Resource>  resources = HotelSystem.getResources();
        List<String> outputs = new ArrayList<String>();
        for(Hotel h : hotels)
        {
            if(h.getName().toLowerCase().contains(name))
            {
                for(Resource r : resources)
                    if(r.getHotel_id().equals(h.getId()))
                        outputs.add(h.getId() + "-" + r.getResource_id());
            }
        }
        for(Category c : categories)
        {
            if(c.getCategory_name().toLowerCase().contains(name))
                for(Resource r : resources)
                    if(r.getCategory_id().equals(c.getCategory_id()))
                        outputs.add(r.getHotel_id() + "-" + r.getResource_id());
        }
        for(Resource r : resources)
        {
            if(r.getNumber_or_name().toLowerCase().contains(name))
                outputs.add(r.getHotel_id() + "-" + r.getResource_id());
        }
        if(outputs.isEmpty())
        {
            System.out.println("not-found");
            return;
        }
        Collections.sort(outputs);
        StringBuilder builder = new StringBuilder();
        for(String s : outputs)
        {
            builder.append(s).append("|");
        }
        builder.deleteCharAt(builder.length()-1);
        System.out.println(builder);
    }
}