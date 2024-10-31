package com.example.event_log.event.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ERROR_LOG")
public class ErrorLog {
    @Id
    @Column(name = "uuid")
    UUID uuid;
    @Column(name = "event_json")
    @Type(JsonType.class)
    private String eventJson;
    @Column(name = "ex_log")
    private String exceptionLog;
    @Column(name = "log_time", columnDefinition = "TIMESTAMP")
    private Timestamp logTime;
}
