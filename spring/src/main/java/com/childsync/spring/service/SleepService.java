package com.childsync.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.childsync.spring.dto.request.SleepRequest;
import com.childsync.spring.dto.response.SleepResponse;
import com.childsync.spring.mapper.SleepMapper;
import com.childsync.spring.model.Sleep;
import com.childsync.spring.model.User;
import com.childsync.spring.repository.SleepRepository;
import com.childsync.spring.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class SleepService {
    
    private final SleepRepository sleepRepo;
    private final SleepMapper sleepMapper;
    private final UserRepository userRepo;

    public SleepService(SleepRepository sleepRepo, SleepMapper sleepMapper, UserRepository userRepo) {

        this.sleepRepo = sleepRepo;
        this.sleepMapper = sleepMapper;
        this.userRepo = userRepo;

    }

    public SleepResponse getById(Integer id) {

        SleepResponse response = sleepMapper.sleepToSleepResponse(sleepRepo.findById(id)
                                                                           .orElseThrow());

        return response;

    }

    public List<SleepResponse> getAll() {

        return sleepRepo.findAll()
                        .stream()
                        .map(sleepMapper::sleepToSleepResponse)
                        .toList();

    }

    @Transactional
    public SleepResponse create(SleepRequest request) {

        User user = userRepo.findById(request.userId())
                            .orElseThrow();

        Sleep sleep = sleepMapper.sleepRequestToSleep(request);
        sleep.setUser(user);
        sleepRepo.save(sleep);

        SleepResponse response = sleepMapper.sleepToSleepResponse(sleep);

        return response;

    }

    public String delete(Integer id) {

        if (sleepRepo.findById(id).isEmpty()) {

            return "Deletion failed";

        } else {

            sleepRepo.deleteById(id);
            return "Deletion successful";

        }

    }

    @Transactional
    public SleepResponse update(Integer id, SleepRequest request) {

        Sleep sleep = sleepRepo.findById(id)
                               .orElseThrow();
        User user = userRepo.findById(request.userId())
                            .orElseThrow();

        sleep.setStart(request.start());
        sleep.setEnd(request.end());
        sleep.setUser(user);
        sleepRepo.save(sleep);

        SleepResponse response = sleepMapper.sleepToSleepResponse(sleep);

        return response;

    }

}
