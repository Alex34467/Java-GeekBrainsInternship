package DBService.Repository;

import DBService.DBExecutor;
import Entities.Site;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.sql.*;
import java.util.*;


// Репозиторий сайта.
public class SiteRepository implements Repository<Site>
{
    // Данные.
    private DBExecutor executor;


    // Конструктор.
    public SiteRepository(DBExecutor executor)
    {
        this.executor = executor;
    }

    // Добавление.
    @Override
    public void add(Site site)
    {
        throw new NotImplementedException();
    }

    // Выбор.
    @Override
    public Site getById(int id)
    {
        throw new NotImplementedException();
    }

    // Выбор всех сайтов.
    @Override
    public Collection<Site> getAll()
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Sites";
        ResultSet resultSet = executor.executeQuery(query);

        // Обход результата.
        Collection<Site> sites = new HashSet<>();
        try
        {
            while (resultSet.next())
            {
                Site site = new Site(resultSet.getInt(1), resultSet.getString(2));
                sites.add(site);
            }
        }
        catch (SQLException e)
        {
            return sites;
        }

        // Возврат результата.
        return sites;
    }

    // Удаление.
    @Override
    public void delete(Site site)
    {
        throw new NotImplementedException();
    }

    // Обновление.
    @Override
    public void update(Site site)
    {
        throw new NotImplementedException();
    }
}
