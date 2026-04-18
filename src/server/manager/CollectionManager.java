package server.manager;

import common.model.StudyGroup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class CollectionManager {

    private final List<StudyGroup> collection = new ArrayList<>();
    private final AtomicLong currentId = new AtomicLong(1);

    public void add(StudyGroup group) {
        group.setId(currentId.getAndIncrement());
        collection.add(group);
    }

    public List<StudyGroup> getAll() {
        return collection.stream()
                .sorted(Comparator.comparing(StudyGroup::getName))
                .collect(Collectors.toList());
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

    public boolean removeAnyBySemester(Object semester) {
        return collection.removeIf(g -> g.getSemester() != null && g.getSemester().equals(semester));
    }
}