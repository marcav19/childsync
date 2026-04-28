package com.childsync.spring.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.childsync.spring.dto.request.ActivityRequest;
import com.childsync.spring.dto.response.ActivityResponse;
import com.childsync.spring.mapper.ActivityMapper;
import com.childsync.spring.model.Activity;
import com.childsync.spring.repository.ActivityRepository;
import jakarta.transaction.Transactional;

@Service
public class ActivityService {

    private final ActivityRepository activityRepo;
    private final ActivityMapper activityMapper;

    public ActivityService(ActivityRepository activityRepo, ActivityMapper activityMapper) {

        this.activityRepo = activityRepo;
        this.activityMapper = activityMapper;

    }

    public ActivityResponse getById(Integer id) {

        ActivityResponse response = activityMapper.activityToActivityResponse(activityRepo.findById(id)
                                                                                          .orElseThrow());

        return response;

    }

    public List<ActivityResponse> getAll() {
        
        return activityRepo.findAll()
                           .stream()
                           .map(activityMapper::activityToActivityResponse)
                           .toList();

    }
    
    @Transactional
    public ActivityRequest create(ActivityRequest request) {
        
        Activity activity = activityMapper.activityRequestToActivity(request);
        activityRepo.save(activity);

        return request;

    }

    public String delete(Integer id) {

        if (activityRepo.findById(id).equals(Optional.empty())) {

            return "not found";

        } else {

            activityRepo.deleteById(id);
            return "deleted";

        }

    }

    @Transactional
    public ActivityRequest update(Integer id, ActivityRequest request) {

        Activity activity = activityRepo.findById(id)
                                        .orElseThrow();

        activity.setDatetime(request.datetime());
        activity.setName(request.name());
        activity.setUserid(request.userid());
        activityRepo.save(activity);

        return request;

    }
}