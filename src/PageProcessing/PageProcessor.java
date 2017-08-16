package PageProcessing;

import DBService.DBService;
import Entities.*;
import PageProcessing.XMLParser.XMLParser;
import Parsing.*;
import Util.Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.*;


// Обработчик страниц.
public class PageProcessor
{
    // Конструктор.
    public PageProcessor()
    {

    }

    // Обработка страницы.
    public void processPage(Page page)
    {
        // Анализ страницы.
        System.out.println("Process page: " + page.getUrl());
        String url = page.getUrl().toLowerCase();
        if (url.endsWith("robots.txt"))
        {
            System.out.println("   Its robots.txt page.");
            processRobots(page);
        }
        else if (url.contains("sitemap") && url.endsWith(".xml"))
        {
            System.out.println("   Its Sitemap.xml page.");
            processSitemap(page);
        }
        else if (url.endsWith(".gz"))
        {
            System.out.println("   Its Sitemap archive.");
        }
        else
        {
            System.out.println("   Its usual page.");
            processUsualPage(page);
        }

        // Обновление информации о странице.
        DBService.getInstance().updatePageScanDate(page, Util.getCuttentDateTime());
        System.out.println("Page processed.");
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

    // Обработка обычной страницы.
    public void processUsualPage(Page page)
    {
        // Получение списка личностей.
        System.out.println("   Парсинг текста.");
        Collection<Person> persons = DBService.getInstance().getPersons();

        try
        {
            // Получение страницы.
            String document = Jsoup.connect(page.getUrl()).get().html();

            // Обход личностей.
            for (Person person : persons)
            {
                // Получение ключевых слов.
                Collection<Keyword> keywords = DBService.getInstance().getKeywordsByPersonId(person.getId());

                // Обход ключевых слов.
                int matches = 0;
                for (Keyword keyword : keywords)
                {
                    // Подсчет совпадеий.
                    matches += KeywordParser.countMatches(document, keyword.getName());
                }

                // Запись результата в БД.
                PersonsPageRank rank = new PersonsPageRank(person.getId(), page.getId(), matches);
                DBService.getInstance().addPersonsPageRank(rank);
            }
        }
        catch (IOException e)
        {

        }
    }
}
