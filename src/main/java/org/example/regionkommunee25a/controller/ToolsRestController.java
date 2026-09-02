package org.example.regionkommunee25a.controller;

import org.example.regionkommunee25a.model.Region;
import org.example.regionkommunee25a.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tools")
public class ToolsRestController {

    @Autowired
    ApiServiceGetRegioner apiServiceGetRegioner;

    @GetMapping("getregioner")
    public List<Region> getRegioner() {
        return apiServiceGetRegioner.getRegioner();
    }

}
