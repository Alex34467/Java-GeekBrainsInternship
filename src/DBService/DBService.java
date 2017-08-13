package DBService;

import DBService.Repository.KeywordRepository;
import DBService.Repository.PersonRepository;
import Entities.Keyword;
import Entities.Page;
import Entities.Person;
import Entities.Site;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// БД сервис.
public class DBService
{
    // Данные.
    private static DBService instance;
    private DBExecutor executor;
    private PersonRepository personRepository;
    private KeywordRepository keywordRepository;


    // Геттер.
    public static DBService getInstance()
    {
        if (instance == null) instance = new DBService();
        return instance;
    }

    // Возврат списка личностей.
    public Collection<Person> getPersons()
    {
        // Данные.
        String query;

        // Получение личностей.
        Collection<Person> persons = personRepository.getAll();

        // Получение ключевых слов.
        for (Person person : persons)
        {
            Collection<Keyword> keywords = keywordRepository.getAllKeywordsByPersonId(person.getId());
            person.setKeywords(keywords);
        }

        // Возврат результата.
        return persons;
    }

    // Возврат списка страниц.
    public Set<Site> getSites()
    {
        try
        {
            // Данные.
            String query;
            Set<Site> sites = new HashSet<>();

            // Получение списка сайтов.
            System.out.println("Looking for sites.");
            query = "SELECT * FROM Sites";
            ResultSet resultSet = executor.executeQuery(query);

            // Обход результата.
            while (resultSet.next())
            {
                Site site = new Site(resultSet.getInt(1), resultSet.getString(2));
                sites.add(site);
                System.out.println("   Found site: " + site);
            }
            System.out.println("SiteRepository search completed.\n");

            // Обход списка сайтов.
            for (Site site : sites)
            {
                // Подготовка.
                System.out.println("Looking for pages for site: " + site.getName());
                query = "SELECT * FROM pages WHERE SiteID = " + site.getId();
                ResultSet resultSet2 = executor.executeQuery(query);

                // Подучение страниц.
                while (resultSet2.next())
                {
                    Page page = new Page(resultSet2.getInt(1), resultSet2.getString(2), resultSet2.getInt(3), resultSet2.getString(4), resultSet2.getString(5));
                    site.addPage(page);
                    System.out.println("   Found page: " + page);
                }
                System.out.println("Pages search completed.\n");
            }

            // Возврат результата.
            return sites;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    // Конструктор.
    private DBService()
    {
        // Инициализация исполнителя.
        executor = MySQLExecutor.getInstance();

        // Инициализация репозиториев.
        personRepository = new PersonRepository(executor);
        keywordRepository = new KeywordRepository(executor);
    }
}
