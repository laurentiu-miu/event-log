package com.example.event_log.third;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventThirdRepository extends JpaRepository<EventThird, UUID> {
}