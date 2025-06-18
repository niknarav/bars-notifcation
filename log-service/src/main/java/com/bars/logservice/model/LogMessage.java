package com.bars.logservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;

import java.time.Instant;

@Data
public class LogMessage {

    @Id
    private String id;

    private String message;
    private String type;

    @Field(format = DateFormat.date_time)
    private Instant timestamp;
}
