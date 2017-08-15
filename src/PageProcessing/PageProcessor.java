package PageProcessing;

import DBService.DBService;
import Entities.Keyword;
import Entities.Page;
import Entities.Person;
import Entities.PersonsPageRank;
import PageProcessing.XMLParser.XMLParser;
import Parsing.KeywordParser;
import Parsing.RobotsParser;
import Util.Util;

import java.util.Collection;
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
        // Получение списка личностей.
        System.out.println("   Парсинг текста.");
        Collection<Person> persons = DBService.getInstance().getPersons();

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
                matches += KeywordParser.countMatches(page.getUrl(), keyword.getName());
            }

            // Запись результата в БД.
            PersonsPageRank rank = new PersonsPageRank(person.getId(), page.getId(), matches);
            DBService.getInstance().addPersonsPageRank(rank);
        }
    }
}
