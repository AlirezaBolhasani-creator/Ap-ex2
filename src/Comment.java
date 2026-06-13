public class Comment {
    String comment;
    String resource_id;
    String hotel_id;
    public Comment(String resource_id, String hotel_id, String comment)
    {
        this.comment = comment;
        this.resource_id = resource_id;
        this.hotel_id = hotel_id;
    }
    public String getComment() {
        return comment;
    }
    public String getResource_id() {
        return resource_id;
    }
    public String getHotel_id() {
        return hotel_id;
    }
}
