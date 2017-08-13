package DBService;

import java.sql.*;

//
public class MySQLExecutor
{
    // Данные.
    private static MySQLExecutor instance;
    private static final String url = "jdbc:mysql://localhost:3306/gbi";
    private static  final String user = "root";
    private static  final String password = "1001";
    private ResultSet resultSet;
    private Statement statement;
    private Connection connection;


    // Геттер.
    public static MySQLExecutor getInstance()
    {
        if (instance == null) instance = new MySQLExecutor();
        return instance;
    }

    // Выполнение запроса.
    public ResultSet executeQuery(String query)
    {
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
    public int executeUpdate(String query)
    {
        return -1;
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
            connection = DriverManager.getConnection(url, user, password);
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
