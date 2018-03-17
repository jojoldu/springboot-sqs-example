package com.jojoldu.aws.springbootsqs.controller

import com.jojoldu.aws.springbootsqs.domain.Point
import com.jojoldu.aws.springbootsqs.domain.PointRepository
import com.jojoldu.aws.springbootsqs.dto.PointDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

/**
 * Created by jojoldu@gmail.com on 2018. 3. 16.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PointControllerTest extends Specification {

    @Autowired
    PointRepository pointRepository

    @Autowired
    TestRestTemplate restTemplate

    void cleanup() {
        pointRepository.deleteAllInBatch()
    }

    def "earn points through the queue."() {
        given:
        PointDto requestDto = PointDto.builder()
                .userId(10L)
                .savePoint(1000L)
                .description("buy laptop")
                .build()
        when:
        ResponseEntity<String> response = restTemplate.postForEntity("/point", requestDto, String.class)
        Thread.sleep(500L)

        then:
        response.getStatusCode() == HttpStatus.OK
        List<Point> points = pointRepository.findAll()
        !points.isEmpty()
        points.get(0).getPoint() == 1000L
    }
}
