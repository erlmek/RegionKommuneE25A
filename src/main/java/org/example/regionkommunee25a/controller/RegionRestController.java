package org.example.regionkommunee25a.controller;

import org.example.regionkommunee25a.model.Region;
import org.example.regionkommunee25a.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regioner")
public class RegionRestController {

    @Autowired
    RegionRepository regionRepository;

    @GetMapping
    List<Region> getRegioner() {
        return regionRepository.findAll();
    }

    @DeleteMapping("/{kode}")
    List<Region> deleteRegion(@PathVariable String kode) {
        regionRepository.deleteById(kode);
        return regionRepository.findAll();
    }


}
