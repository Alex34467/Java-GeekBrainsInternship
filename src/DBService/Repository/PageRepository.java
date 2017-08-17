package DBService.Repository;

import DBService.DBExecutor;
import Entities.Page;
import Util.Util;
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

    // Выбор страницы по Id.
    @Override
    public Page getById(int id)
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Pages WHERE Id = " + id;

        // Выполнение запроса.
        ResultSet resultSet = executor.executeQuery(query);

        // Возврат результата.
        return getPageFromResultSet(resultSet);
    }

    // Выбор страницы по имени.
    public Page getPageByUrl(String url)
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Pages WHERE Url = " + url;

        // Выполнение запроса.
        ResultSet resultSet = executor.executeQuery(query);

        // Возврат результата.
        return getPageFromResultSet(resultSet);
    }

    // Выбор непросканированной страницы.
    public Page getUnscannedPage()
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Pages WHERE lastScanDate IS NULL LIMIT 1";

        // Выполнение запроса.
        ResultSet resultSet = executor.executeQuery(query);

        // Возврат результата.
        return getPageFromResultSet(resultSet);
    }

    // Выбор непросканированных страниц.
    public Collection<Page> getUnscannedPages(int count)
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Pages WHERE lastScanDate IS NULL LIMIT " + count;

        // Выполнение запроса.
        ResultSet resultSet = executor.executeQuery(query);

        // Возврат результата.
        return getPagesFromResultSet(resultSet);
    }

    // Выбор всех страниц по Id сайта.
    public Collection<Page> getAllPagesBySiteId(int siteId)
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Pages WHERE SiteId = " + siteId;

        // Выполнение запроса.
        ResultSet resultSet = executor.executeQuery(query);

        // Возврат результата.
        return getPagesFromResultSet(resultSet);
    }

    // Выбор sitemap страниц.
    public Collection<Page> getSitemapPages(int count)
    {
        // Данные.
        int hoursDiff = 20;
        String now = Util.getCuttentDateTime();

        // Подготовка запроса.
        String query = "SELECT * FROM Pages WHERE (Url LIKE \"%sitemap%\") AND (Url LIKE \"%.xml\") AND (TIMESTAMPDIFF (HOUR,"
                + now + " , LastScanDate) <= " + hoursDiff + ") ORDER BY LastScanDate LIMIT " + count;

        // Выполнение запроса.
        ResultSet resultSet = executor.executeQuery(query);

        // Возврат результата.
        return getPagesFromResultSet(resultSet);
    }

    // Выбор всех страниц.
    @Override
    public Collection<Page> getAll()
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Pages";

        // Выполнение запроса.
        ResultSet resultSet = executor.executeQuery(query);

        // Возврат результата.
        return getPagesFromResultSet(resultSet);
    }

    // Удаление.
    @Override
    public void delete(Page page)
    {
        throw new NotImplementedException();
    }

    // Обновление информации о времени сканирования.
    public void updatePageScanDate(Page page, String scanDate)
    {
        // Подготовка запроса.
        String query = "UPDATE Pages SET LastScanDate = \"" + scanDate + "\" WHERE id = " + page.getId();

        // Выполнение запроса.
        executor.executeUpdate(query);
    }

    // Обновление.
    @Override
    public void update(Page page)
    {
        throw new NotImplementedException();
    }

    // Получение страницы из ResultSet'а.
    private Page getPageFromResultSet(ResultSet resultSet)
    {
        // Данные.
        Page page = null;

        // Заполнение.
        try
        {
            while (resultSet.next())
            {
                page = new Page(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5));
            }
        }
        catch (SQLException e)
        {
            return page;
        }

        // Возврат результата.
        return page;
    }

    // Получение сираниц из ResultSet'а.
    private Collection<Page> getPagesFromResultSet(ResultSet resultSet)
    {
        // Данные.
        Collection<Page> pages = new HashSet<>();

        // Заполнение.
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
}
