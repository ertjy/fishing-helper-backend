package com.simas.FishingHelper.Service;


import com.simas.FishingHelper.Model.Dtos.CatchDto;
import com.simas.FishingHelper.Repository.CatchLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatchLogService {
    private final CatchLogRepository repository;

    public CatchDto saveCatch(CatchDto fishCatch) {
        return repository.save(fishCatch);
    }

    public List<CatchDto> getAllCatches() {
        return repository.findAll();
    }
}
