package DBService;

import DBService.Repository.*;
import Entities.*;
import Util.Util;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

// БД сервис.
public class DBService
{
    // Данные.
    private static DBService instance;
    private PersonRepository personRepository;
    private KeywordRepository keywordRepository;
    private SiteRepository siteRepository;
    private PageRepository pageRepository;


    // Геттер.
    public static DBService getInstance()
    {
        if (instance == null) instance = new DBService();
        return instance;
    }

    // Возврат списка личностей.
    public Collection<Person> getPersons()
    {
        // Получение личностей.
        Collection<Person> persons = personRepository.getAll();

        // Получение ключевых слов.
        for (Person person : persons)
        {
            Collection<Keyword> keywords = keywordRepository.getAllKeywordsByPersonId(person.getId());
            person.addKeywords(keywords);
        }

        // Возврат результата.
        return persons;
    }

    // Возврат списка страниц.
    public Collection<Site> getSites()
    {
        // Получение сайтов.
        Collection<Site> sites = siteRepository.getAll();

        // Получение страниц.
        for (Site site : sites)
        {
            Collection<Page> pages = pageRepository.getAllPagesBySiteId(site.getId());
            site.addPages(pages);
        }

        // Возврат результата.
        return sites;
    }

    // Добавление ссылок в БД и преобразует их в Page.
    public Collection<Page> addAllLinks(Set<String> links, int siteId)
    {
        // Данные.
        Collection<Page> pages = new HashSet<>();

        // Обход ссылок.
        for (String link : links)
        {
            // Созание страницы.
            Page page = createPageFromLink(link, siteId);
            pages.add(page);
        }

        // Возврат результата.
        return pages;
    }

    // Создание сираницы из ссылки.
    private Page createPageFromLink(String link, int siteId)
    {
        // Данные.
        Page page = null;

        // Поиск страницы.
        page = pageRepository.getByName(link);

        // Добавление страницы.
        if (page == null)
        {
            // Сборка страницы.
            page = new Page(link, siteId, Util.getCuttentDateTime(), null);

            // Добюавление.
            pageRepository.add(page);
        }

        // Возврат результата.
        return page;
    }

    // Конструктор.
    private DBService()
    {
        // Инициализация исполнителя.
        DBExecutor executor = MySQLExecutor.getInstance();

        // Инициализация репозиториев.
        personRepository = new PersonRepository(executor);
        keywordRepository = new KeywordRepository(executor);
        siteRepository = new SiteRepository(executor);
        pageRepository = new PageRepository(executor);
    }
}
