package PageProcessing;

import DBService.DBService;
import Entities.Page;
import PageProcessing.XMLParser.XMLParser;
import Parsing.RobotsParser;
import Util.Util;

import java.util.Set;

// Обработчик страниц.
public class PageProcessor
{
    // Конструктор.
    public PageProcessor()
    {

    }

    // Обработка robots.txt.
    public void processRobots(Page page)
    {
        // Получение ссылки на Sitemap.
        System.out.println("   Looking for Sitemap in: " + page.getUrl());
        String url = RobotsParser.getSitemapUrl(page.getUrl());

        // Анализ.
        if (url != null)
        {
            System.out.println("   Found Sitemap at: " + url);

            // Сборка страницы.
            Page page2 = new Page(url, page.getSiteId(), Util.getCuttentDateTime(), null);

            // Добавление станицы.
            DBService.getInstance().addPage(page2);
        }
        else
        {
            System.out.println("   Sitemap not found.");
        }
    }

    // Обработка Sitemap.
    public void processSitemap(Page page)
    {
        // Получение ссылок.
        Set<String> links = XMLParser.parseXML(page.getUrl());

        // Добавление ссылок.
        System.out.println("   Found " + links.size() + " links.");
        for (String link : links)
        {
            // Сборка страницы.
            Page page2 = new Page(link, page.getSiteId(), Util.getCuttentDateTime(), null);

            // Добавление страницы.
            DBService.getInstance().addPage(page2);
        }
        System.out.println("   All links added.");
    }

    // Обработка страницы.
    public void processPage(Page page)
    {
        System.out.println("   Парсинг текста.");
    }
}
