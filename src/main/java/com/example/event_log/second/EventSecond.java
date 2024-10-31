package com.example.event_log.second;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name="TAB_2")
public class EventSecond {
    @Id
    @Column(name = "uuid")
    UUID uuid;
    @Column(name = "mes_uuid")
    UUID mesUuid;
    @Column(name = "event_json")
    @Type(JsonType.class)
    private String eventJson;
}
