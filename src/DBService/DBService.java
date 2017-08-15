package DBService;

import DBService.Repository.*;
import Entities.*;
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
        return personRepository.getAll();
    }

    // Возврат клюсывах слов.
    public Collection<Keyword> getKeywords(Person person)
    {
        return keywordRepository.getAllKeywordsByPersonId(person.getId());
    }

    // Возврат сайтов без страниц.
    public Collection<Site> getSitesWthoutPages()
    {
        return siteRepository.getSitesWithoutPages();
    }

    // Возврат списка сайтов.
    public Collection<Site> getSites()
    {
        return siteRepository.getAll();
    }

    // Добавление страницы.
    public void addPage(Page page)
    {
        pageRepository.add(page);
    }

    // Получение страниц сайта.
    public Collection<Page> getPages(Site site)
    {
        return pageRepository.getAllPagesBySiteId(site.getId());
    }

    // Возврат непросканированной страницы.
    public Page getUnscannedPage()
    {
        return pageRepository.getUnscannedpage();
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
