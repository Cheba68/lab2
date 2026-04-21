package model;

import java.io.Serializable;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/**
 * Класс координат.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates implements Serializable {

    private Long x; // Поле не может быть null
    private Double y; // Максимальное значение поля: 754, Поле не может быть null

    /**
     * Конструктор Coordinates.
     */
    public Coordinates(Long x, Double y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException("Coordinates не могут быть null");
        }
        if (y > 754) {
            throw new IllegalArgumentException("Y не может быть больше 754");
        }
        this.x = x;
        this.y = y;
    }

    public Coordinates() {}

    public Long getX() { return x; }
    public Double getY() { return y; }
}
