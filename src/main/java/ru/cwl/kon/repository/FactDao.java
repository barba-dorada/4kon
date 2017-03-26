package ru.cwl.kon.repository;

import ru.cwl.kon.model.Fact;

import java.time.LocalDate;
import java.util.*;

// TODO: 09.07.2016 user in query
public class FactDao {
    static long sequence = 1;

    private long nextId() {
        return sequence++;
    }

    Map<Long, Fact> map = new HashMap<>();

    List<Fact> getList(String user) {
        List<Fact> result = new ArrayList<>();
        for (Fact fact : map.values()) {
            if (fact.getUser().equals(user)) {
                result.add(fact);
            }
        }
        return result;
    }

    List<Fact> getList(String user, LocalDate from, LocalDate to) {
        List<Fact> result = new ArrayList<>();
        for (Fact fact : getList(user)) {
            LocalDate date = fact.getDate();
            if (date == null) continue;
            if (date.compareTo(from) >= 0 && date.compareTo(to) < 0) {
                result.add(fact);
            }
        }
        return result;
    }

    Fact read(Long id) {
        return map.get(id);
    }

    Fact create(Fact fact) {
        if (fact.getId() == 0) {
            fact.setId(nextId());
        }
        map.put(fact.getId(), fact);
        return fact;
    }


    Fact update(Fact fact) {
        map.put(fact.getId(), fact);
        return fact;
    }

    Fact delete(Fact fact) {
        Fact result = delete(fact.getId());
        return result;
    }

    Fact delete(Long id) {
        Fact result = map.remove(id);
        return result;
    }
}
