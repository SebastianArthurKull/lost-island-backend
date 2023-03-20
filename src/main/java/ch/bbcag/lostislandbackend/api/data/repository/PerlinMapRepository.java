package ch.bbcag.lostislandbackend.api.data.repository;

import ch.bbcag.lostislandbackend.api.data.entity.PerlinMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PerlinMapRepository extends JpaRepository<PerlinMap, Integer> {
    Optional<PerlinMap> findById(Integer id);

    List<PerlinMap> findAll();

}
