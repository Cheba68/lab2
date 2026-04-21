package model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import util.LocalDateAdapter;

/**
 * Класс, представляющий учебную группу.
 * Используется как элемент коллекции.
 */
@XmlRootElement(name = "studyGroup")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudyGroup implements Comparable<StudyGroup>, Serializable{

    @XmlElement
    private Long id; // >0, уникальный, генерируется автоматически

    @XmlElement(required = true)
    private String name; // не null, не пустой

    @XmlElement(required = true)
    private Coordinates coordinates; // не null

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class) // Требуется адаптер для JAXB
    private LocalDate creationDate; // не null, генерируется автоматически

    @XmlElement
    private Integer studentsCount; // >0

    @XmlElement
    private FormOfEducation formOfEducation; // может быть null

    @XmlElement(name = "semester")
    private Semester semesterEnum; // может быть null

    @XmlElement(required = true, name = "groupAdmin")
    private Person groupAdmin; // не null

    /**
     * Пустой конструктор для JAXB.
     */
    public StudyGroup() {
    }

    /**
     * Конструктор для создания валидного объекта StudyGroup.
     *
     * @param id              уникальный идентификатор (> 0)
     * @param name            имя группы (не пустое)
     * @param coordinates     координаты (не null)
     * @param studentsCount   количество студентов (> 0)
     * @param formOfEducation форма обучения (может быть null)
     * @param semesterEnum    семестр (может быть null)
     * @param groupAdmin      администратор группы (не null)
     * @throws IllegalArgumentException если нарушены ограничения целостности
     */
    public StudyGroup(Long id,
                      String name,
                      Coordinates coordinates,
                      Integer studentsCount,
                      FormOfEducation formOfEducation,
                      Semester semesterEnum,
                      Person groupAdmin) {

        validateArguments(id, name, coordinates, studentsCount, groupAdmin);

        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    /**
     * Валидация аргументов конструктора.
     */
    private void validateArguments(Long id, String name, Coordinates coordinates,
                                   Integer studentsCount, Person groupAdmin) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id должен быть больше 0");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name не может быть пустым");
        }
        if (coordinates == null) {
            throw new IllegalArgumentException("coordinates не может быть null");
        }
        if (studentsCount == null || studentsCount <= 0) {
            throw new IllegalArgumentException("studentsCount должен быть > 0");
        }
        if (groupAdmin == null) {
            throw new IllegalArgumentException("groupAdmin не может быть null");
        }
    }

    // === Геттеры ===

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public void setId( Long id) {
        this.id = id;
    }

    // === Методы ===

    /**
     * Сравнение групп по количеству студентов (для Collections.sort).
     * Null-значения считаются "меньше" любых чисел.
     */
    @Override
    public int compareTo(StudyGroup other) {
        if (this.studentsCount == null && other.studentsCount == null) return 0;
        if (this.studentsCount == null) return -1;
        if (other.studentsCount == null) return 1;
        return this.studentsCount.compareTo(other.studentsCount);
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", studentsCount=" + studentsCount +
                ", formOfEducation=" + formOfEducation +
                ", semesterEnum=" + semesterEnum +
                ", groupAdmin=" + groupAdmin +
                '}';
    }
}