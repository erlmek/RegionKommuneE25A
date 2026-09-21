package org.example.regionkommunee25a.controller;

import org.example.regionkommunee25a.model.Kommune;
import org.example.regionkommunee25a.model.Region;
import org.example.regionkommunee25a.repositories.KommuneRepository;
import org.example.regionkommunee25a.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kommuner")
@CrossOrigin("*")
public class KommuneRestController {

    @Autowired
    KommuneRepository kommuneRepository;

    @GetMapping
    List<Kommune> getKommuner() {
        return kommuneRepository.findAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Kommune postKommune(@RequestBody Kommune kommune) {
        System.out.println(kommune);
        return kommuneRepository.save(kommune);
    }


}
