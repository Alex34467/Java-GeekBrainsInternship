package Util;

import org.jsoup.Jsoup;
import java.io.IOException;

//
public class Downloader
{
    // Загрузка.
    public String download(String url)
    {
        try
        {
            String data = Jsoup.connect(url).get().html();
            System.out.println(data);
            return data;
        }
        catch (IOException e)
        {
            return null;
        }
    }
}
