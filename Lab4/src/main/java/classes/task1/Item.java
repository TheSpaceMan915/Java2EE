package classes.task1;


public class Item
{
    private int m_id;
    private int m_category_id;
    private int m_manufacturer_id;
    private String m_name;


    public Item(int id, int category_id,int manufacturer_id, String name)
    {
        m_id = id;
        m_category_id = category_id;
        m_manufacturer_id = manufacturer_id;
        m_name = name;
    }

    public int getId() { return m_id; }

    public void setId(int m_id) { this.m_id = m_id; }

    public int getCategoryId() { return m_category_id; }

    public void setCategoryId(int m_category_id) { this.m_category_id = m_category_id; }

    public String getName() { return m_name; }

    public void setName(String m_name) { this.m_name = m_name; }

    public int getManufacturerId() { return m_manufacturer_id; }

    public void setManufacturerId(int m_manufacturer_id) { this.m_manufacturer_id = m_manufacturer_id; }


    public String categoryIdToString()
    {
        String str = switch (m_category_id)
                {
                    case 1 -> "Camera & Photo";
                    case 2 -> "Car & Vehicle Electronics";
                    case 3 -> "Cell Phones & Accessories";
                    case 4 -> "Computers & Accessories";
                    case 5 -> "GPS,Finders & Accessories";
                    default -> "";
                };

        return str;
    }

    public String manufacturerIdToString()
    {
        String str = switch (m_manufacturer_id)
                {
                    case 1 -> "Canon";
                    case 2 -> "Qifutan";
                    case 3 -> "Beats";
                    case 4 -> "Sceptre";
                    case 5 -> "Garmin";
                    default -> "";
                };

        return str;
    }
}
