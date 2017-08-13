package DBService;

import java.sql.ResultSet;

// Интерфейс исполнителя.
interface DBExecutor
{
    // Выполнение запроса.
    ResultSet executeQuery(String query);
}
