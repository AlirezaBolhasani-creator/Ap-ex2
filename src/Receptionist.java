public class Receptionist extends Staff
{
    public Receptionist(String username, String password, String first_name, String hotel_id,
                        String last_name, String nationalId, int year_of_birth, String address)
    {
        super(username, password, first_name, hotel_id, last_name,
                nationalId, year_of_birth, address, "receptionist");
    }
}
