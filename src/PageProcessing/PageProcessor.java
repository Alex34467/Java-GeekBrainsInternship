package PageProcessing;

import DBService.DBService;
import Entities.*;
import PageProcessing.XMLParser.XMLParser;
import Parsing.*;
import Util.Util;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.*;


// Обработчик страниц.
public class PageProcessor implements Runnable
{
    // Данные.
    private String name;
    private Collection<Person> persons;
    private CrawlerManager manager;
    private KeywordParser parser;
    private int sleepTime = 5000;


    // Конструктор.
    public PageProcessor(CrawlerManager manager, String name)
    {
        this.name = name;
        this.manager = manager;
        persons = manager.getPersons();
        this.parser = new KeywordParser();
    }

    // Выполнение.
    @Override
    public void run()
    {
        while (!Thread.currentThread().isInterrupted())
        {
            try
            {
                // Получение страницы.
                Page page = manager.getPage();

                if (page != null)
                {
                    // Обработка станицы.
                    System.out.println(name + ": take page " + page.getUrl());
                    processPage(page);
                }
                else
                {
                    // Пауза.
                    System.out.println("No page found. Sleeping for " + sleepTime + " mills.");
                    Thread.sleep(sleepTime);
                }
            }
            catch (InterruptedException e)
            {
                System.out.println("Stopping.");
            }
        }
    }

    // Обработка страницы.
    public void processPage(Page page)
    {
        // Анализ страницы.
        System.out.println(name +": process page: " + page.getUrl());
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
        manager.completePageProcessing(page);
        System.out.println(name + ": page processed.");
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
        try
        {
            // Получение страницы.
            String document = Jsoup.connect(page.getUrl()).get().html();
            int pageSize = document.length() / 1000;
            System.out.println("   Парсинг текста: ~" + pageSize + "k chars.");

            // Обход личностей.
            for (Person person : persons)
            {
                // Получение ключевых слов.
                Collection<Keyword> keywords = person.getKeywords();

                // Обход ключевых слов.
                int matches = 0;
                for (Keyword keyword : keywords)
                {
                    // Подсчет совпадеий.
                    matches += parser.countMatches(document, keyword.getName());
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
