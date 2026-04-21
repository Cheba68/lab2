package manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import model.StudyGroup;
import model.Semester; // если есть enum

public class CollectionManager {

    private final List<StudyGroup> collection = new ArrayList<>();
    private final AtomicLong currentId = new AtomicLong(1);

    public void add(StudyGroup group) {
        group.setId(currentId.getAndIncrement());

        // если есть поле даты — обязательно
        if (group.getCreationDate() == null) {
            group.setCreationDate(LocalDate.now());
        }

        collection.add(group);
    }

    // ✅ ВСЕГДА отсортированная коллекция
    public List<StudyGroup> getAll() {
        return collection.stream()
                .sorted(Comparator.comparing(StudyGroup::getName))
                .collect(Collectors.toList());
    }

    // 🔥 ВАЖНО — исправлено
    public List<StudyGroup> getCollection() {
        return getAll(); // теперь всегда отсортировано
    }

    public boolean removeById(Long id) {
        return collection.removeIf(g -> g.getId().equals(id));
    }

    public void clear() {
        collection.clear();
    }

    public int size() {
        return collection.size();
    }

    public List<StudyGroup> filterStartsWithName(String prefix) {
        return collection.stream()
                .filter(g -> g.getName() != null && g.getName().startsWith(prefix))
                .sorted(Comparator.comparing(StudyGroup::getName))
                .collect(Collectors.toList());
    }

    public long countLessThanStudentsCount(int count) {
        return collection.stream()
                .filter(g -> g.getStudentsCount() < count)
                .count();
    }

    // ✅ лучше с enum
    public boolean removeAnyBySemester(Semester semester) {
        return collection.removeIf(g ->
                g.getSemesterEnum() != null &&
                g.getSemesterEnum().equals(semester)
        );
    }
}