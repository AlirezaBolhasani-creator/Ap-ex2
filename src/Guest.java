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
        this.payment = null;
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
}
