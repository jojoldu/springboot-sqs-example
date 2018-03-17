package com.jojoldu.aws.springbootsqs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Created by jojoldu@gmail.com on 2018. 3. 12.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Getter
@AllArgsConstructor
public class SqsMessage {
    private String name;
    private LocalDate executeDate;
}
