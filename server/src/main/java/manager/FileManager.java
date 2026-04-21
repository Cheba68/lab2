package manager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.StudyGroup;

public class FileManager {

    private final String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public List<StudyGroup> load() {
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                return new ArrayList<>();
            }

            JAXBContext context = JAXBContext.newInstance(StudyGroupListWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            StudyGroupListWrapper wrapper =
                    (StudyGroupListWrapper) unmarshaller.unmarshal(file);

            return wrapper.getGroups();

        } catch (Exception e) {
            System.out.println("Ошибка загрузки файла: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void save(List<StudyGroup> collection) {
        try {
            JAXBContext context = JAXBContext.newInstance(StudyGroupListWrapper.class);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StudyGroupListWrapper wrapper = new StudyGroupListWrapper();
            wrapper.setGroups(collection);

            marshaller.marshal(wrapper, new File(fileName));

        } catch (Exception e) {
            System.out.println("Ошибка сохранения файла: " + e.getMessage());
        }
    }
}