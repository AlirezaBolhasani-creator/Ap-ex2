public class Category
{
    private String category_id;
    private String category_name;
    private String category_description;
    public Category(String category_id, String category_name, String category_description)
    {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_description = category_description;
    }
    public Category(String category_id, String category_name)
    {
        this( category_id, category_name, null);
    }
    public String getCategory_id()
    {
        return category_id;
    }
    public String getCategory_name()
    {
        return category_name;
    }
    public String getCategory_description()
    {
        return category_description;
    }
}

