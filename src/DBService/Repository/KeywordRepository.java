package DBService.Repository;

import DBService.DBExecutor;
import Entities.Keyword;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;


// Репозиторий ключевых слов.
public class KeywordRepository implements Repository<Keyword>
{
    // Данные.
    private DBExecutor executor;


    // Конструктор.
    public KeywordRepository(DBExecutor executor)
    {
        this.executor = executor;
    }

    // Добавление.
    @Override
    public void add(Keyword keyword)
    {
        throw new NotImplementedException();
    }

    // Выбор.
    @Override
    public Keyword getById(int id)
    {
        throw new NotImplementedException();
    }

    // Выбор всех ключевых слов по Id личности.
    public Collection<Keyword> getAllKeywordsByPersonId(int id)
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Keywords WHERE PersonId = " + id;
        ResultSet resultSet = executor.executeQuery(query);

        // Обработка результата.
        Collection<Keyword> keywords = new HashSet<>();
        try
        {
            while (resultSet.next())
            {
                Keyword keyword = new Keyword(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
                keywords.add(keyword);
            }
        }
        catch (SQLException e)
        {
            return keywords;
        }

        // Возврат результата.
        return keywords;
    }

    // Выбор всех ключевых слов.
    @Override
    public Collection<Keyword> getAll()
    {
        throw new NotImplementedException();
    }

    // Удаление.
    @Override
    public void delete(Keyword keyword)
    {
        throw new NotImplementedException();
    }

    // Обновление.
    @Override
    public void update(Keyword keyword)
    {
        throw new NotImplementedException();
    }
}
