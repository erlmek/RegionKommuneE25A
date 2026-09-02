package org.example.regionkommunee25a.repositories;

import jakarta.transaction.Transactional;
import org.example.regionkommunee25a.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface KommuneRepository extends JpaRepository<Kommune, String> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM kommune WHERE region = '1084'", nativeQuery = true)
    int deleteKommunerByRegionNative();

}

