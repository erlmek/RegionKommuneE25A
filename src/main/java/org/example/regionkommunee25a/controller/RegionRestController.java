package org.example.regionkommunee25a.controller;

import org.example.regionkommunee25a.model.Region;
import org.example.regionkommunee25a.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regioner")
@CrossOrigin("*")
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

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Region postRegion(@RequestBody Region region) {
        System.out.println(region);
        return regionRepository.save(region);
    }

    @PostMapping("/region2")
    public ResponseEntity<String> postRegion2(@RequestParam String kode, @RequestParam String navn) {
        return ResponseEntity.ok("Received kode: " + kode + ", navn: " + navn);
    }


}
