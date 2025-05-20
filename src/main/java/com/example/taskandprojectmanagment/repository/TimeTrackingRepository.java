package com.example.taskandprojectmanagment.repository;

import com.example.taskandprojectmanagment.model.TimeTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTrackingRepository extends JpaRepository<TimeTracking, Integer> {
}
