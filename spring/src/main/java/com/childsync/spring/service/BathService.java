package com.childsync.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.childsync.spring.dto.request.BathRequest;
import com.childsync.spring.dto.response.BathResponse;
import com.childsync.spring.mapper.BathMapper;
import com.childsync.spring.model.Bath;
import com.childsync.spring.model.User;
import com.childsync.spring.repository.BathRepository;
import com.childsync.spring.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class BathService {
    
    private final BathRepository bathRepo;
    private final BathMapper bathMapper;
    private final UserRepository userRepo;

    public BathService(BathRepository bathRepo, BathMapper bathMapper, UserRepository userRepo) {

        this.bathRepo = bathRepo;
        this.bathMapper = bathMapper;
        this.userRepo = userRepo;

    }

    public BathResponse getById(Integer id) {

        BathResponse response = bathMapper.bathToBathResponse(bathRepo.findById(id)
                                                                      .orElseThrow());

        return response;

    }

    public List<BathResponse> getAll() {

        return bathRepo.findAll()
                       .stream()
                       .map(bathMapper::bathToBathResponse)
                       .toList();

    }

    @Transactional
    public BathResponse create(BathRequest request) {

        User user = userRepo.findById(request.userId())
                            .orElseThrow();

        Bath bath = bathMapper.bathRequestToBath(request);
        bath.setUser(user);
        bathRepo.save(bath);

        BathResponse response = bathMapper.bathToBathResponse(bath);

        return response;

    }

    public String delete(Integer id) {

        if (bathRepo.findById(id).isEmpty()) {

            return "Deletion failed";

        } else {

            bathRepo.deleteById(id);
            return "Deletion successful";

        }

    }

    @Transactional
    public BathResponse update(Integer id, BathRequest request) {

        Bath bath = bathRepo.findById(id)
                            .orElseThrow();
        User user = userRepo.findById(request.userId())
                            .orElseThrow();

        bath.setDateTime(request.dateTime());
        bath.setUser(user);
        bathRepo.save(bath);

        BathResponse response = bathMapper.bathToBathResponse(bath);

        return response;

    }
}
