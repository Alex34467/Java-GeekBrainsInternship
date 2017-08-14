package DBService.Repository;

import DBService.DBExecutor;
import Entities.Page;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.sql.*;
import java.util.*;

// Репозиторий странцы сайта.
public class PageRepository implements Repository<Page>
{
    // Данные.
    private DBExecutor executor;


    // Конструктор.
    public PageRepository(DBExecutor executor)
    {
        this.executor = executor;
    }

    // Добавление.
    @Override
    public void add(Page page)
    {
        // Подготовка запроса.
        String queryTail1 = "VALUES (\"" + page.getUrl() + "\", " + page.getSiteId() + ", \"" + page.getFoundDateTime() + "\")";
        String queryTal12 = ", lastScanDate)";
        String queryTail3 = " ,\"" + page.getLastScanDate() + "\")";

        // Сборка запроса.
        String query = "INSERT INTO Pages (url, siteId, foundDateTime) ";
        query += (page.getLastScanDate() == null) ? queryTail1 : queryTal12 + queryTail1 + queryTail3;

        // Выполнение запроса.
        executor.executeUpdate(query);
    }

    // Выбор страницы gj Id..
    @Override
    public Page getById(int id)
    {
        throw new NotImplementedException();
    }

    // Выбор страницы по имени.
    public Page getByName(String name)
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Pages WHERE Name = " + name;
        ResultSet resultSet = executor.executeQuery(query);

        // Анализ результата.
        Page page = null;
        try
        {
            // Обход результата.
            while (resultSet.next())
            {
                page = new Page(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5));
            }
        }
        catch (SQLException | NullPointerException e)
        {
            return page;
        }

        // Возврат результата.
        return page;
    }

    // Выбор всех страниц по Id сайта.
    public Collection<Page> getAllPagesBySiteId(int id)
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Pages WHERE SiteId = " + id;
        ResultSet resultSet = executor.executeQuery(query);

        // Обработка результата.
        Collection<Page> pages = new HashSet<>();
        try
        {
            while (resultSet.next())
            {
                Page page = new Page(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5));
                pages.add(page);
            }
        }
        catch (SQLException e)
        {
            return pages;
        }

        // Возврат результата.
        return pages;
    }

    // Выбор всех страниц.
    @Override
    public Collection<Page> getAll()
    {
        throw new NotImplementedException();
    }

    // Удаление.
    @Override
    public void delete(Page page)
    {
        throw new NotImplementedException();
    }

    // Обновление.
    @Override
    public void update(Page page)
    {
        throw new NotImplementedException();
    }
}
