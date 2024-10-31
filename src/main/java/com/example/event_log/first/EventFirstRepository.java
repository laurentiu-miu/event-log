package com.example.event_log.first;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventFirstRepository extends JpaRepository<EventFirst, UUID> {
}