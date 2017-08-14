package Util;

import java.text.SimpleDateFormat;
import java.util.Date;

// Класс утилит.
public class Util
{
    // Возврат текщего времени.
    public static String getCuttentDateTime()
    {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
