package com.example.event_log.event.repository;

import com.example.event_log.event.model.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventLogRepository  extends JpaRepository<EventLog, UUID> {
}