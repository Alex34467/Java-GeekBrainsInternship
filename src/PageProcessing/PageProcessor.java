package PageProcessing;

import Entities.Page;
import Entities.Site;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.*;

// Обработчик страниц.
public class PageProcessor
{
    // Данные.
    private Map<Page, Boolean> pageVisited;


    // Конструктор.
    public PageProcessor()
    {
        pageVisited = new HashMap<>();
    }

    // Обработка сайтов.
    public void processSites(Set<Site> sites)
    {
        // Добавление страниц.
        addPagesFromSites(sites);

        // Обработка страниц.
        processPages();
    }

    // Добавление страниц в список.
    private void addPagesFromSites(Set<Site> sites)
    {
        System.out.println("Adding pages.");

        // Обход сайтов.
        for (Site site : sites)
        {
            System.out.println("   Adding pages for site: " + site.getName());

            // Обход страниц сайта.
            for (Page page : site.getPages())
            {
                if (true)
                {
                    pageVisited.put(page, false);
                    System.out.println("      Page " + page.getName() + " added.");
                }
                else
                {
                    System.out.println("      Page " + page.getName() + " not added.");
                }
            }
        }
        System.out.println("Pages addition completed.\n");
    }

    // Обработка страциц.
    private void processPages()
    {
        System.out.println("Processing pages.");
        for (Map.Entry<Page, Boolean> entry : pageVisited.entrySet())
        {
            if (!entry.getValue())
            {
                processPage(entry.getKey());
            }
        }
        System.out.println("Page processing completed.\n");
    }

    // Обработка страницы.
    private void processPage(Page page)
    {
        try
        {
            // Загрузка страницы.
            System.out.println("   Processing page: " + page.getName());
            Document document = Jsoup.connect(page.getName()).get();

            // Извлечение ссылок.
            System.out.println("      Extracting links.");
            Set<String> links = extractLinks(document);

            // Извлечение текста.
            System.out.println("      Extracting text.");
            String pageText = extractText(document);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Извлечение текста.
    private String extractText(Document document)
    {
        return null;
    }

    // Извлечение ссылок.
    private Set<String> extractLinks(Document document)
    {
        // Данные.
        Set<String> linksSet = new HashSet<>();

        try
        {
            // Получение ссылок.
            Elements links = document.select("a[href]");
            String baseUri = new URL(document.location()).getHost();

            // Обход ссылок.
            for (Element link : links)
            {
                // Получение хоста.
                String linkStr = link.attr("abs:href");
                if (!linkStr.isEmpty())
                {
                    String host = new URL(linkStr).getHost();

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
            return null;
        }
    }
}