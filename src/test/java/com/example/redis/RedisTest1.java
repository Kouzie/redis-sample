package com.example.redis;

import com.example.redis.repository.PointRedisRepository;
import com.example.redis.vo.Point;
import lombok.extern.java.Log;
import org.apache.tomcat.jni.Local;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class RedisTest1 {
    @Autowired
    private PointRedisRepository repo;

    @After
    public void tearDown() throws Exception {
        repo.deleteAll();
    }

    @Test
    public void defaultInput() {
        String id = "kouzie";
        LocalDateTime refreshTime = LocalDateTime.now();
        Point point = new Point(id, 1000L, refreshTime);
        log.info("before Point : " + point);
        repo.save(point);

        Point savedPoint = repo.findById(id).get();
        log.info("after Point : " + savedPoint);
    }
}
