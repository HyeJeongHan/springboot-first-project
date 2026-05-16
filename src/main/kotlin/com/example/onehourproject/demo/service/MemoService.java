package com.example.onehourproject.demo.service;

import com.example.onehourproject.demo.domain.Memo;
import com.example.onehourproject.demo.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {
    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public Memo create(String title, String content) {
        return memoRepository.save(title, content);
    }

    public Memo getOne(Long id) {
        return memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("메모를 찾을 수 없습니다. id = " + id));
    }

    public List<Memo> getAll() {
        return memoRepository.findAll();
    }
}
