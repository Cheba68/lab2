package model;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Обертка для коллекции StudyGroup.
 */
@XmlRootElement(name = "studyGroups")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudyGroupList implements Serializable{

    @XmlElement(name = "studyGroup")
    private LinkedList<StudyGroup> studyGroups = new LinkedList<>();

    public StudyGroupList() {}

    public StudyGroupList(LinkedList<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }

    public LinkedList<StudyGroup> getStudyGroups() {
        return studyGroups;
    }

    public void setStudyGroups(LinkedList<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }
}
