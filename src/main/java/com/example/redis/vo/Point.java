package com.example.redis.vo;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
@RedisHash("point")
public class Point {
    @Id
    private String id;
    private Long amount;
    private LocalDateTime refreshTime;

    public void refresh(Long amount, LocalDateTime refreshTime) {
        if (refreshTime.isAfter(this.refreshTime)) {
            this.amount = amount;
            this.refreshTime = refreshTime;
        }
    }
}
