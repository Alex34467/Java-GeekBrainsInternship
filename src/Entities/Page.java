package Entities;

// Класс страницы.
public class Page
{
    // Данные.
    private int id;
    private String url;
    private int siteId;
    private String foundDateTime;
    private String lastScanDate;


    // Конструктор.
    public Page(final int id, final String url, final int siteId, final String foundDateTime, final String lastScanDate)
    {
        this.id = id;
        this.url = url;
        this.siteId = siteId;
        this.foundDateTime = foundDateTime;
        this.lastScanDate = lastScanDate;
    }

    // Конструктор.
    public Page(final int id, final String name, final int siteId)
    {
        this(id, name, siteId, null, null);
    }

    // Конструктор.
    public Page(final String name, final int siteId, final String foundDateTime, final String lastScanDate)
    {
        this(-1, name, siteId, foundDateTime, lastScanDate);
    }

    // Геттеры.
    public int getId()
    {
        return id;
    }

    public String getUrl()
    {
        return url;
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
        return "Id: " + id + " Url: " + url + " SiteId: " + siteId + " FoundDateTime: " + foundDateTime + " LastScanDate: " + lastScanDate;
    }
}
