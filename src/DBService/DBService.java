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
    private PersonsPageRankRepository personPageRankRepository;


    // Геттер.
    public static DBService getInstance()
    {
        if (instance == null) instance = new DBService();
        return instance;
    }

    // Добавление страницы.
    public synchronized void addPage(Page page)
    {
        pageRepository.add(page);
    }

    // Добавление записи о количестве упиминаний личности на странице.
    public synchronized void addPersonsPageRank(PersonsPageRank rank)
    {
        personPageRankRepository.add(rank);
    }

    // Возврат списка личностей.
    public synchronized Collection<Person> getPersons()
    {
        return personRepository.getAll();
    }

    // Возврат ключевых слов по Id личности.
    public synchronized Collection<Keyword> getKeywordsByPersonId(int personId)
    {
        return keywordRepository.getAllKeywordsByPersonId(personId);
    }

    // Возврат сайтов без страниц.
    public synchronized Collection<Site> getSitesWthoutPages()
    {
        return siteRepository.getSitesWithoutPages();
    }

    // Возврат списка сайтов.
    public synchronized Collection<Site> getSites()
    {
        return siteRepository.getAll();
    }

    // Получение страниц сайта.
    public synchronized Collection<Page> getPages(Site site)
    {
        return pageRepository.getAllPagesBySiteId(site.getId());
    }

    // Возврат непросканированной страницы.
    public synchronized Page getUnscannedPage()
    {
        return pageRepository.getUnscannedPage();
    }

    // Возврат непросканированной страницы.
    public synchronized Collection<Page> getUnscannedPages(int count)
    {
        return pageRepository.getUnscannedPages(count);
    }

    // Воврат sitemap станиц.
    public synchronized Collection<Page> getSitemapPages(int count)
    {
        return pageRepository.getSitemapPages(count);
    }

    // Обновление даты сканирования.
    public synchronized void updatePageScanDate(Page page, String scanDate)
    {
        pageRepository.updatePageScanDate(page, scanDate);
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
        personPageRankRepository = new PersonsPageRankRepository(executor);
    }
}
