package org.example.regionkommunee25a.controller;

import org.example.regionkommunee25a.model.Kommune;
import org.example.regionkommunee25a.model.Region;
import org.example.regionkommunee25a.repositories.KommuneRepository;
import org.example.regionkommunee25a.repositories.RegionRepository;
import org.example.regionkommunee25a.service.ApiServiceGetKommuner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @DeleteMapping("/{kode}")
    public ResponseEntity<String> deleteKommune(@PathVariable String kode) {
        if (!kommuneRepository.existsById(kode)) {
            return ResponseEntity.notFound().build();
        }
        kommuneRepository.deleteById(kode);
        return ResponseEntity.ok("Kommune slettet: " + kode);
    }

    @GetMapping("kommunepage")
    public ResponseEntity<List<Kommune>> getPageOfCounties() {
        int page = 4;
        int size = 5;
        PageRequest countyPage = PageRequest.of(page, size);
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(kommuneRepository.findAll(paging).getContent());
    }

    @GetMapping("/kommunepageparm")
    public ResponseEntity<Map<String, Object>> getPageOfKommuner(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Kommune> pageKommune = kommuneRepository.findAll(paging);

        List<Kommune> kommuner = pageKommune.getContent();

        if (kommuner.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        Map<String, Object> response = new HashMap<>();
        response.put("kommuner", kommuner);
        response.put("currentPage", pageKommune.getNumber());
        response.put("totalItems", pageKommune.getTotalElements());
        response.put("totalPages", pageKommune.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/kommunesortxx")
    public ResponseEntity<List<Kommune>> getPageOfKommunerxx(@RequestParam(defaultValue = "id,desc") String[] sort) {
        for (String s: sort) {
            System.out.println(s);
        }

        List kommuner = kommuneRepository.findAll();
        return new ResponseEntity<>(kommuner, HttpStatus.OK);
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }


    @GetMapping("/kommunesort")
    public ResponseEntity<List<Kommune>> getPageOfKommuner(@RequestParam(defaultValue = "id,desc") String[] sort) {
        for (String s: sort) {
            System.out.println(s);
        }

        List<Sort.Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            for (String sortOrder: sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }

        List kommuner = kommuneRepository.findAll(Sort.by(orders));
        if (kommuner.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(kommuner, HttpStatus.OK);
    }



}
