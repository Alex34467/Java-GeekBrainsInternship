package Util;

import org.jsoup.Jsoup;
import java.io.IOException;

// Загрузчик страниц.
public class Downloader
{
    // Загрузка.
    public String download(String url)
    {
        try
        {
            return Jsoup.connect(url).get().html();
        }
        catch (IOException e)
        {
            return null;
        }
    }
}
