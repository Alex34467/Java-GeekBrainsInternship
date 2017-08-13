package DBService;

import java.sql.ResultSet;

// Интерфейс исполнителя.
public interface DBExecutor
{
    // Выполнение запроса.
    ResultSet executeQuery(String query);
}
