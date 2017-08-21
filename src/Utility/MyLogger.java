package Utility;

import java.util.logging.Level;


// Логгер.
public class MyLogger
{
    // Данные.
    private static java.util.logging.Logger logger;
    private final static String name = "Logger";

    // Иницилизация.
    static
    {
        logger = java.util.logging.Logger.getLogger(name);
    }

    // Логировнние.
    public static void log(Level level, String message)
    {
        logger.log(level, message);
    }
}
