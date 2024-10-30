package com.example.event_log.event;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

//@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventLog {
    //@Id
    //@Column(name = "mes_uuid")
    UUID mesUuid;
    //@Column(name = "trx_uuid")
    UUID trxUuid;
    //@Column(name="event_type")
    //@Enumerated(value = EnumType.STRING)
    private EventType eventType;
    //@Column(name = "log_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime logTime;
}
