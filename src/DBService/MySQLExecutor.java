package DBService;

import java.sql.*;

// Сервис БД.
public class MySQLExecutor implements DBExecutor
{
    // Данные.
    private static MySQLExecutor instance;
    private static final String url = "jdbc:mysql://localhost:3306/gbi";
    private static  final String user = "root";
    private static  final String password = "1001";
    private Statement statement;


    // Геттер.
    public static MySQLExecutor getInstance()
    {
        if (instance == null) instance = new MySQLExecutor();
        return instance;
    }

    // Выполнение запроса.
    @Override
    public ResultSet executeQuery(String query)
    {
        // Результат.
        ResultSet resultSet = null;

        try
        {
            resultSet = statement.executeQuery(query);
            return resultSet;
        }
        catch (SQLException e)
        {
            return resultSet;
        }
        catch (NullPointerException npe)
        {
            return resultSet;
        }
    }

    // Выполнение запроса.
    @Override
    public void executeUpdate(String query)
    {
        try
        {
            statement.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    // Конструктор.
    private MySQLExecutor()
    {
        System.out.println("Starting MySQLExecutor");
        connect();
    }

    // Подключение.
    private void connect()
    {
        System.out.println("Connection to DB.");
        try
        {
            // Плдключение.
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully connected to database.");

            // Создание состояния.
            statement = connection.createStatement();
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed.");
        }
    }
}
