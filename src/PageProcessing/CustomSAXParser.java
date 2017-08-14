package PageProcessing;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;

// SAX парсер.
public class CustomSAXParser extends DefaultHandler
{
    // Данные.
    private boolean processElement = false;
    private Set<String> links;


    // Конструктор.
    public CustomSAXParser()
    {
        links = new HashSet<>();
    }

    // Начало элемента.
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
    {
        if (qName.equals("loc"))
        {
            processElement = true;
        }
    }

    // Обработка элемента.
    @Override
    public void characters(char[] ch, int start, int length)
    {
        if (processElement)
        {
            // Добавление ссылки.
            String link = new String(ch, start, length);
            links.add(link);

            // Сброс флага.
            processElement = false;
        }
    }

    // Возврат ссылок.
    public Set<String> getLinks()
    {
        return links;
    }
}
