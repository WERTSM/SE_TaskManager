package ru.khmelev.tm.service.util;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Sort;
import ru.khmelev.tm.entity.SortEntity;

import java.util.*;

public class SortedEntity<T extends SortEntity> {
    public void sort(@NotNull List<T> list, @NotNull Sort sort) {
        if (sort == Sort.CREATE) {
            Collections.sort(list, new Comparator<T>() {
                @Override
                public int compare(T entity1, T entity2) {
                    if (entity1.equals(entity2)) {
                        return 0;
                    }
                    return entity1.getDateCreate().compareTo(entity2.getDateCreate());
                }
            });
        }
        if (sort == Sort.START) {
            Collections.sort(list, new Comparator<T>() {
                @Override
                public int compare(T entity1, T entity2) {
                    if (entity1.equals(entity2)) {
                        return 0;
                    }
                    return entity1.getDateStart().compareTo(entity2.getDateStart());
                }
            });
        }
        if (sort == Sort.FINISH) {
            Collections.sort(list, new Comparator<T>() {
                @Override
                public int compare(T entity1, T entity2) {
                    if (entity1.equals(entity2)) {
                        return 0;
                    }
                    return entity1.getDateFinish().compareTo(entity2.getDateFinish());
                }
            });
        }
        if (sort == Sort.STATUS) {
            Collections.sort(list, new Comparator<T>() {
                @Override
                public int compare(T entity1, T entity2) {
                    if (entity1.equals(entity2)) {
                        return 0;
                    }
                    return entity1.getStatus().compareTo(entity2.getStatus());
                }
            });
        }
    }

    @NotNull
    @Override
    public Collection<T> findAllName(String findParameter, String userId) {
        @NotNull final List<T> list = new ArrayList<>(entityRepository.findAll(userId));
        final Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getName().contains(findParameter)) {
                iterator.remove();
            }
        }
        return list;
    }

    @NotNull
    @Override
    public Collection<T> findAllDescription(String findParameter, String userId) {
        @NotNull final List<T> list = new ArrayList<>(entityRepository.findAll(userId));
        final Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getDescription().contains(findParameter)) {
                iterator.remove();
            }
        }
        return list;
    }
}