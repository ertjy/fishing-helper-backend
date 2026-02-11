package com.simas.FishingHelper.Repository;

import com.simas.FishingHelper.Model.Dtos.BaitSuggestionDto;
import com.simas.FishingHelper.Model.Dtos.CatchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatchLogRepository extends JpaRepository<CatchDto, Long> {
    List<CatchDto> findBySpecies(String specie);

    @Query("SELECT c.species, AVG(c.weight) FROM CatchDto c GROUP BY c.species")
    List<Object[]> averageWeightBySpecies();

    @Query("""
            SELECT new com.simas.FishingHelper.Model.Dtos.BaitSuggestionDto(c.baitUsed, COUNT(c))
            FROM CatchDto c 
            WHERE c.species = :specie 
            GROUP BY c.baitUsed 
            ORDER BY COUNT(c) DESC
    """)
    List<BaitSuggestionDto> getMostEffectiveBait(@Param("specie") String specie);

    @Query("""
    SELECT c.weather.description, COUNT(c)
    FROM CatchDto c
    WHERE c.species = :species
    GROUP BY c.weather.description
    ORDER BY COUNT(c) DESC
    """)
    List<Object[]> findWeatherWithMostCatches(@Param("species") String species);
}


