package com.childsync.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.childsync.spring.dto.request.PottyRequest;
import com.childsync.spring.dto.response.PottyResponse;
import com.childsync.spring.mapper.PottyMapper;
import com.childsync.spring.model.Potty;
import com.childsync.spring.model.User;
import com.childsync.spring.repository.PottyRepository;
import com.childsync.spring.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class PottyService {
    
    private final PottyRepository pottyRepo;
    private final PottyMapper pottyMapper;
    private final UserRepository userRepo;

    public PottyService(PottyRepository pottyRepo, PottyMapper pottyMapper, UserRepository userRepo) {

        this.pottyRepo = pottyRepo;
        this.pottyMapper = pottyMapper;
        this.userRepo = userRepo;

    }

    public PottyResponse getById(Integer id) {

        PottyResponse response = pottyMapper.pottyToPottyResponse(pottyRepo.findById(id)
                                                                           .orElseThrow());

        return response;

    }

    public List<PottyResponse> getAll() {

        return pottyRepo.findAll()
                        .stream()
                        .map(pottyMapper::pottyToPottyResponse)
                        .toList();

    }

    @Transactional
    public PottyResponse create(PottyRequest request) {

        User user = userRepo.findById(request.userId())
                            .orElseThrow();
        
        Potty potty = pottyMapper.pottyRequestToPotty(request);
        potty.setUser(user);
        pottyRepo.save(potty);

        PottyResponse response = pottyMapper.pottyToPottyResponse(potty);

        return response;

    }

    public String delete(Integer id) {

        if (pottyRepo.findById(id).isEmpty()) {

            return "Deletion failed";

        } else {

            pottyRepo.deleteById(id);
            return "Deletion successful";

        }

    }

    @Transactional
    public PottyResponse update(Integer id, PottyRequest request) {

        Potty potty = pottyRepo.findById(id)
                               .orElseThrow();
        User user = userRepo.findById(request.userId())
                            .orElseThrow();

        potty.setDateTime(request.dateTime());
        potty.setDescription(request.description());
        potty.setUser(user);
        pottyRepo.save(potty);

        PottyResponse response = pottyMapper.pottyToPottyResponse(potty);

        return response;

    }

}
