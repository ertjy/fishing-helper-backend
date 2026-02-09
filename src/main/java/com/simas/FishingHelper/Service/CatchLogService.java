package com.simas.FishingHelper.Service;


import com.simas.FishingHelper.CatchLog;
import com.simas.FishingHelper.Repository.CatchLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatchLogService {
    private final CatchLogRepository repository;

    public CatchLog save(CatchLog fishCatch) {
        return repository.save(fishCatch);
    }

    public List<CatchLog> getAll() {
        return repository.findAll();
    }
}
