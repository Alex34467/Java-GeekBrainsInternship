package DBService;

import Entities.Keyword;
import Entities.Page;
import Entities.Person;
import Entities.Site;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

// БД сервис.
public class DBService
{
    // Данные.
    private static DBService instance;


    // Геттер.
    public static DBService getInstance()
    {
        if (instance == null) instance = new DBService();
        return instance;
    }

    // Возврат списка личностей.
    public Set<Person> getPersons()
    {
        // Данные.
        String query;
        Set<Person> persons = new HashSet<>();

        try
        {
            // Получение личностей.
            System.out.println("Looking for persons.");
            query = "SELECT * FROM persons";
            ResultSet resultSet = DBExecutor.getInstance().executeQuery(query);

            // Обход результата.
            while (resultSet.next())
            {
                Person person = new Person(resultSet.getInt(1), resultSet.getString(2));
                persons.add(person);
                System.out.println("   Found person: " + person);
            }
            System.out.println("Personality search completed.\n");

            // Поиск ключевых слов.
            for (Person person : persons)
            {
                // Подготовка.
                System.out.println("Looking for keywords for person: " + person.getName());
                query = "SELECT * FROM keywords WHERE PersonID = " + person.getId();
                ResultSet resultSet2 = DBExecutor.getInstance().executeQuery(query);

                // Добавление ключевых слов.
                while (resultSet2.next())
                {
                    Keyword keyword = new Keyword(resultSet2.getInt(1), resultSet2.getString(2), resultSet2.getInt(3));
                    person.addKeyword(keyword);
                    System.out.println("   Found keyword: " + keyword);
                }
                System.out.println("Keyword search completed.\n");
            }
        }
        catch (SQLException e)
        {
            System.out.println("SQLException.");
        }
        finally
        {
            return persons;
        }

        // Возврат результата.
        //return persons;
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
            ResultSet resultSet = DBExecutor.getInstance().executeQuery(query);

            // Обход результата.
            while (resultSet.next())
            {
                Site site = new Site(resultSet.getInt(1), resultSet.getString(2));
                sites.add(site);
                System.out.println("   Found site: " + site);
            }
            System.out.println("Site search completed.\n");

            // Обход списка сайтов.
            for (Site site : sites)
            {
                // Подготовка.
                System.out.println("Looking for pages for site: " + site.getName());
                query = "SELECT * FROM pages WHERE SiteID = " + site.getId();
                ResultSet resultSet2 = DBExecutor.getInstance().executeQuery(query);

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

    }
}
