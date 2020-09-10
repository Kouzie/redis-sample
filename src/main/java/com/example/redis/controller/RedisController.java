package com.example.redis.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@Log
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/sample")
    public void sample(Model model) {
        log.info("sample called()....");
        Set<String> keys = stringRedisTemplate.keys("*");

        model.addAttribute("keys", new ArrayList<>(keys));
    }

    @GetMapping("/insert")
    public String insert(
            @Param("key") String key,
            @Param("value") String value
    ) {
        log.info("insert called()....");
        log.info(key + ":" + value);
        stringRedisTemplate.opsForValue().set(key, value);
        stringRedisTemplate.opsForValue().get(key);
        return "redirect:/redis/sample";
    }


    @GetMapping("/deleteAll")
    public String deleteAll(Model model) {
        log.info("deleteAll called()....");
        stringRedisTemplate.delete(stringRedisTemplate.keys("*"));
        return "redirect:/redis/sample";
    }
}
