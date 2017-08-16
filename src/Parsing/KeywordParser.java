package Parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


// Парсер ключевых слов.
public class KeywordParser
{
    // Парсинг.
    public static int countMatches(String page, String keyword)
    {
        // Данные.
        int result = 0;

        // Компиляция регулярного выражения.
        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

        // Поиск вхождений.
        Matcher matcher = pattern.matcher(page);

        // Подсчет вхождений.
        while (matcher.find())
        {
            result++;
        }

        // Возврат значения.
        return result;
    }
}
