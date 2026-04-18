package server.manager;

import common.model.StudyGroup;

import javax.xml.bind.annotation.*;
import java.util.List;

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