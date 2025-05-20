package com.example.taskandprojectmanagment.mapper;

import com.example.taskandprojectmanagment.dto.TimeTrackingRes;
import com.example.taskandprojectmanagment.dto.TimeTrackingStartReq;
import com.example.taskandprojectmanagment.dto.TimeTrackingStopReq;
import com.example.taskandprojectmanagment.model.TimeTracking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeTrackingMapper {

    TimeTracking toTimeStart(TimeTrackingStartReq timeTrackingStartReq);

    // Instead of creating a new entity for stop, update existing one
    void updateTimeFromStartReq(TimeTrackingStopReq stopReq, @MappingTarget TimeTracking entity);

    @Mapping(target = "taskId", source = "task.id")
    TimeTrackingRes toTimeRes(TimeTracking timeTracking);

    List<TimeTrackingRes> toTimeRes(List<TimeTracking> timeTrackings);

}

