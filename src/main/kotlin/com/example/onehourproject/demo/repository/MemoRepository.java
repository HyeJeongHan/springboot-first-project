package com.example.onehourproject.demo.repository;

import com.example.onehourproject.demo.domain.Memo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoRepository {
    private final Map<Long, Memo> store = new HashMap<>();
    private long sequence = 0L;

    public Memo save(String title, String content) {
        Memo memo = new Memo(++sequence, title, content);
        store.put(memo.getId(), memo);
        return memo;
    }

    public Optional<Memo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Memo> findAll() {
        return new ArrayList<>(store.values());
    }
}
