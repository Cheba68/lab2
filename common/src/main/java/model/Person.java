package model;

import java.io.Serializable;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Класс, представляющий студента.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable {

    private String name; // Поле не может быть null, Строка не может быть пустой
    private String passportID; //Строка не может быть пустой, Поле может быть null
    private Color eyeColor; // Поле может быть null
    private ColorHair hairColor; //Поле может быть null
    private Country nationality; // Поле не может быть null

    

    /**
     * Конструктор Person.
     */
    public Person(String name, String passportID, Color eyeColor, ColorHair hairColor,
                  Country nationality) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (passportID.isEmpty()){
            throw new IllegalArgumentException("паспорт не может быть пустым");
        }
        if (nationality == null) {
            throw new IllegalArgumentException("Nationality не могут быть null");
        }

        this.name = name;
        this.passportID = passportID;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.nationality = nationality;
    }

    public Person() {}

    public String getName() { return name; }
    public String getPassportID() { return passportID; }
    public Color getEyeColor() { return eyeColor; }
    public ColorHair getHairColor() { return hairColor; }
    public Country getNationality() { return nationality; }
}
