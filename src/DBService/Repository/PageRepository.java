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
        throw new NotImplementedException();
    }

    // Выбор.
    @Override
    public Page getById(int id)
    {
        throw new NotImplementedException();
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
