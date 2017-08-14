package Entities;

// Класс страницы.
public class Page
{
    // Данные.
    private int id;
    private String name;
    private int siteId;
    private String foundDateTime;
    private String lastScanDate;


    // Конструктор.
    public Page(final int id, final String name, final int siteId, final String foundDateTime, final String lastScanDate)
    {
        this.id = id;
        this.name = name;
        this.siteId = siteId;
        this.foundDateTime = foundDateTime;
        this.lastScanDate = lastScanDate;
    }

    // Конструктор.
    public Page(final int id, final String name, final int siteId)
    {
        this(id, name, siteId, null, null);
    }

    // Геттеры.
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getSiteId()
    {
        return siteId;
    }

    public String getFoundDateTime()
    {
        return foundDateTime;
    }

    public String getLastScanDate()
    {
        return lastScanDate;
    }

    // Строка.
    @Override
    public String toString()
    {
        return "Id: " + id + " Name: " + name + " SiteId: " + siteId + " FoundDateTime: " + foundDateTime + " LastScanDate: " + lastScanDate;
    }

    // Сравнение объектов.
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Page page = (Page) o;

        return name != null ? name.equals(page.name) : page.name == null;
    }

    // Хеш-код.
    @Override
    public int hashCode()
    {
        return name != null ? name.hashCode() : 0;
    }
}
