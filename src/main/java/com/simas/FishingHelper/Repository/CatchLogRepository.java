package com.simas.FishingHelper.Repository;

import com.simas.FishingHelper.CatchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatchLogRepository extends JpaRepository<CatchLog, Long> {
        List<CatchLog> findBySpecies(String species);
}


