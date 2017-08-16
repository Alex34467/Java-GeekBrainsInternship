package Parsing;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


// Тестирование парсера ключевых слов.
class KeywordParserTest
{

    // Тестирование парсинга ключнвых слов.
    @Test
    void testCountMatchesFromString()
    {
        // Подготовка.
        String page = "word";
        String keyword = "word";
        int expected = 1;

        // Тест.
        int actual = KeywordParser.countMatches(page, keyword);

        // Проверка.
        assertEquals(expected, actual);
    }

    // Тестирование парсинга ключевых слов со страницы.
    @Test
    void testCountMatchesFromPage() throws IOException
    {
        // Подготовка.
        String url = "https://lenta.ru/news/2017/08/15/nezavis_indiya/";
        String page = Jsoup.connect(url).get().html();
        String keyword = "Путин";
        int expected = 17;

        // Текст.
        int actual = KeywordParser.countMatches(page, keyword);

        // Проверка.
        assertEquals(expected, actual);
    }

}