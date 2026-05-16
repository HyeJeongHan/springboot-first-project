package com.example.onehourproject.demo.controller;

import com.example.onehourproject.demo.domain.Memo;
import com.example.onehourproject.demo.service.MemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memos")
public class MemoController {
    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping
    public Memo create(@RequestParam String title, @RequestParam String content) {
        return memoService.create(title, content);
    }

    @GetMapping("/{id}")
    public Memo getOne(@PathVariable Long id) {
        return memoService.getOne(id);
    }

    @GetMapping
    public List<Memo> getAll() {
        return memoService.getAll();
    }
}
