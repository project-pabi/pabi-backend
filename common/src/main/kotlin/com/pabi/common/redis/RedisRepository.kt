package com.pabi.common.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisRepository(
    val redisTemplate: RedisTemplate<String, String>,
) {

    val REFRESH_PREFIX: String = "RT:"
    val ACCESS_BLACK: String = "ATB:"

    fun setValue(key: String, value: String, timeout: Long, unit: TimeUnit) {
        val values = redisTemplate.opsForValue()
        values.set(key, value, timeout, unit)
    }

    fun getValue(key: String): String? {
        val values = redisTemplate.opsForValue()
        return values.get(key)
    }

    fun delValue(key: String) {
        redisTemplate.delete(key)
    }
}