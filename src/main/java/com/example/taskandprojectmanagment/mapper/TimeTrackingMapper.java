package com.example.taskandprojectmanagment.mapper;

import com.example.taskandprojectmanagment.dto.TimeTrackingReq;
import com.example.taskandprojectmanagment.dto.TimeTrackingRes;
import com.example.taskandprojectmanagment.model.TimeTracking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeTrackingMapper {

    TimeTracking toTimeTracking(TimeTrackingReq timeTrackingReq);

    @Mapping(target = "taskId", source = "task.id")
    @Mapping(target = "userId", source = "user.id")
    TimeTrackingRes toTimeRes(TimeTracking timeTracking);

    List<TimeTrackingRes> toTimeRes(List<TimeTracking> timeTrackings);

}

