package com.codecool.hogwartshouses.DAO;

import com.codecool.hogwartshouses.model.Wand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WandRepository extends JpaRepository<Wand, Long> {
}
