package com.simas.FishingHelper.Repository;

import com.simas.FishingHelper.Model.Dtos.CatchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatchLogRepository extends JpaRepository<CatchDto, Long> {
}


