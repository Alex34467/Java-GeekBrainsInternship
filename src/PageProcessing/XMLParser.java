package PageProcessing;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.*;
import java.io.IOException;
import java.util.*;

// XML парсер.
public class XMLParser
{
    // Парсинг XML.
    public static Set<String> parseXML(String url)
    {
        // Данные.
        Set<String> linksSet = new HashSet<>();

        try
        {
            // Подготовка.
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            CustomSAXParser customSAXParser = new CustomSAXParser();

            // Парсинг.
            parser.parse(url, customSAXParser);

            // Получение результата.
            linksSet = customSAXParser.getLinks();
        }
        catch (ParserConfigurationException | org.xml.sax.SAXException  | IOException e)
        {
            return linksSet;
        }

        // Возврат значения.
        return linksSet;
    }
}
