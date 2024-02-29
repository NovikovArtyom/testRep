package ru.yandex.practicum.catsgram.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Post;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class PostController {
    private final static Logger log = LoggerFactory.getLogger(PostController.class);
    private final List<Post> posts = new ArrayList<>();

    @GetMapping("/posts")
    public List<Post> findAll() {
        log.debug("Запрос получен.");
        return posts;
    }

    @PostMapping(value = "/post")
    public void create(@RequestBody Post post) {
        posts.add(post);
    }
}