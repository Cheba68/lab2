package manager;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import model.StudyGroup;

@XmlRootElement(name = "studyGroups")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudyGroupListWrapper {

    @XmlElement(name = "studyGroup")
    private List<StudyGroup> groups;

    public List<StudyGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<StudyGroup> groups) {
        this.groups = groups;
    }
}