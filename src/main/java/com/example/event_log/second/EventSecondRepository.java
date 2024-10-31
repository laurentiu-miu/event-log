package com.example.event_log.second;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventSecondRepository extends JpaRepository<EventSecond, UUID> {
}