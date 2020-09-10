package com.example.redis.repository;

import com.example.redis.vo.Point;
import org.springframework.data.repository.CrudRepository;

public interface PointRedisRepository extends CrudRepository<Point, String> {
}
