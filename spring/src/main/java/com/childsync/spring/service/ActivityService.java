package com.childsync.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.childsync.spring.dto.request.ActivityRequest;
import com.childsync.spring.dto.response.ActivityResponse;
import com.childsync.spring.mapper.ActivityMapper;
import com.childsync.spring.model.Activity;
import com.childsync.spring.model.User;
import com.childsync.spring.repository.ActivityRepository;
import com.childsync.spring.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class ActivityService {

    private final ActivityRepository activityRepo;
    private final ActivityMapper activityMapper;
    private final UserRepository userRepo;

    public ActivityService(ActivityRepository activityRepo, ActivityMapper activityMapper, UserRepository userRepo) {

        this.activityRepo = activityRepo;
        this.activityMapper = activityMapper;
        this.userRepo = userRepo;

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
    public ActivityResponse create(ActivityRequest request) {

        User user = userRepo.findById(request.userId())
                            .orElseThrow();
        
        Activity activity = activityMapper.activityRequestToActivity(request);
        activity.setUser(user);
        activityRepo.save(activity);

        ActivityResponse response = activityMapper.activityToActivityResponse(activity);

        return response;

    }

    public String delete(Integer id) {

        if (activityRepo.findById(id).isEmpty()) {

            return "Deletion failed";

        } else {

            activityRepo.deleteById(id);
            return "Deletion successful";

        }

    }

    @Transactional
    public ActivityResponse update(Integer id, ActivityRequest request) {

        Activity activity = activityRepo.findById(id)
                                        .orElseThrow();
        User user = userRepo.findById(request.userId())
                            .orElseThrow();

        activity.setDateTime(request.dateTime());
        activity.setName(request.name());
        activity.setUser(user);
        activityRepo.save(activity);

        ActivityResponse response = activityMapper.activityToActivityResponse(activity);

        return response;

    }
}