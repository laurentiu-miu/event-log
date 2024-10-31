package com.example.event_log.first;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TAB_1")
public class EventFirst {
    @Id
    @Column(name = "uuid")
    UUID uuid;
    @Column(name = "mes_uuid")
    UUID mesUuid;
    @Column(name = "event_json")
    @Type(JsonType.class)
    private String eventJson;
}
