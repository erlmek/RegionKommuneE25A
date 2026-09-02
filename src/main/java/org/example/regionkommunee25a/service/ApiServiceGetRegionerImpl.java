package org.example.regionkommunee25a.service;

import org.example.regionkommunee25a.model.Region;
import org.example.regionkommunee25a.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ApiServiceGetRegionerImpl implements ApiServiceGetRegioner {

    private final RestClient restClient;
    public ApiServiceGetRegionerImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    String regionUrl = "https://api.dataforsyningen.dk/regioner";

    @Autowired
    RegionRepository regionRepository;

    private void saveRegioner(List<Region> regioner) {
        regioner.forEach(reg -> regionRepository.save(reg));
    }

    @Override
    public List<Region> getRegioner() {
        RestClient.ResponseSpec response = restClient.get()
                .uri(regionUrl)
                .header("Accept-Encoding", "identity")
                .retrieve();

        List<Region> lst = response.body(new ParameterizedTypeReference<List<Region>>() {});

        saveRegioner(lst);

        return lst;
    }
}
