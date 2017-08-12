package Entities;

import java.util.HashSet;
import java.util.Set;

// Класс личности.
public class Person
{
    // Данные.
    private int id;
    private String name;
    private Set<Keyword> keywords;


    // Конструктор.
    public Person(final int id, final String name)
    {
        this.id = id;
        this.name = name;
        keywords = new HashSet<>();
    }

    // Добавление ключевого слова.
    public void addKeyword(Keyword keyword)
    {
        keywords.add(keyword);
    }

    // Геттеры.
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Set<Keyword> getKeywords()
    {
        return keywords;
    }

    // Строка.
    @Override
    public String toString()
    {
        return "Id: " + id + " Name: " + name;
    }
}
