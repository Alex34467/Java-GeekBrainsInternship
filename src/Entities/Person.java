package Entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// Класс личности.
public class Person
{
    // Данные.
    private int id;
    private String name;
    private Collection<Keyword> keywords;


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

    // Установка ключевых слов.
    public void addKeywords(Collection<Keyword> keywords)
    {
        this.keywords.addAll(keywords);
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

    public Collection<Keyword> getKeywords()
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
