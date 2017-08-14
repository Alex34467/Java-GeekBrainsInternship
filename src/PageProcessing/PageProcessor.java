package PageProcessing;

import DBService.DBService;
import Entities.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

// Обработчик страниц.
public class PageProcessor
{
    // Данные.
    private Map<String, Boolean> pageVisited;
    private int currentSiteId = -1;


    // Конструктор.
    public PageProcessor()
    {
        pageVisited = new HashMap<>();
    }

    // Обработка сайтов.
    public void processSites(Collection<Site> sites)
    {
        for (Site site: sites)
        {
            processSite(site);
        }
    }

    // Обработка сайта.
    private void processSite(Site site)
    {
        // Получение информации об Id сайта.
        currentSiteId = site.getId();

        // Добавление страниц.
        pageVisited = addPagesFromSite(site);

        // Обработка страниц.
        processPages();
    }

    // Добавление страниц в список.
    private Map<String, Boolean> addPagesFromSite(Site site)
    {
        // Данные.
        Map<String, Boolean> pagesMap = new HashMap<>();

        // Обход страниц сайта.
        for (Page page : site.getPages())
        {
            if (true)
            {
                pagesMap.put(page.getUrl(), false);
                System.out.println("      Page " + page.getUrl() + " added.");
            }
            else
            {
                System.out.println("      Page " + page.getUrl() + " not added.");
            }
        }

        // Возврат результата.
        return pagesMap;
    }

    // Обработка страциц.
    private void processPages()
    {
        System.out.println("Processing pages.");
        for (Map.Entry<String, Boolean> entry : pageVisited.entrySet())
        {
            if (!entry.getValue())
            {
                processPage(entry.getKey());
            }
        }
        System.out.println("Page processing completed.\n");
    }

    // Обработка страницы.
    private void processPage(String page)
    {
        try
        {
            // Загрузка страницы.
            System.out.println("   Processing page: " + page);
            Document document = Jsoup.connect(page).get();

            // Извлечение ссылок.
            System.out.println("      Extracting links.");
            Set<String> links = extractLinks(document);
            System.out.println("      Found " + links.size() + " links.");

            // Извлечение текста.
            //System.out.println("      Extracting text.");
            //String pageText = extractText(document);

            // Добавление ссылок в список и БД.
            processLinks(links);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Извлечение текста.
    private String extractText(Document document)
    {
        throw new NotImplementedException();
    }

    // Извлечение ссылок.
    private Set<String> extractLinks(Document document)
    {
        // Данные.
        Set<String> linksSet = new HashSet<>();

        try
        {
            // Получение ссылок.
            Elements links = document.select("a[href]").unwrap();
            String baseUri = new URL(document.location()).getHost();

            // Обход ссылок.
            for (Element link : links)
            {
                // Получение хоста.
                String linkStr = link.attr("abs:href");
                if (!linkStr.isEmpty())
                {
                    String host = new URL(linkStr).getHost();

                    // Очистка от якоря.
                    if (linkStr.contains("#"))
                    {
                        linkStr = linkStr.substring(0, linkStr.indexOf("#"));
                    }

                    // Добавление.
                    if (host.equals(baseUri))
                    {
                        linksSet.add(linkStr);
                    }
                }
            }

            // Возврат результата.
            return linksSet;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return linksSet;
        }
    }

    // Добавление ссылок в список и БД.
    private void processLinks(Set<String> links)
    {
        // Фильтрация ссылок.
        links.removeAll(pageVisited.keySet());

        // Добавление ссылок в БД.
        Collection<Page> pages = DBService.getInstance().addAllLinks(links, currentSiteId);
    }
}