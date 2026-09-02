package org.example.regionkommunee25a.service;

import org.example.regionkommunee25a.model.Kommune;
import org.example.regionkommunee25a.model.Region;
import org.example.regionkommunee25a.repositories.KommuneRepository;
import org.example.regionkommunee25a.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ApiServiceGetKommunerImpl implements ApiServiceGetKommuner{

    private final RestClient restClient;
    public ApiServiceGetKommunerImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    String kommuneUrl = "https://api.dataforsyningen.dk/kommuner";

    @Autowired
    KommuneRepository kommuneRepository;

    private void saveKommuner(List<Kommune> kommuner) {
        kommuner.forEach(kom -> kommuneRepository.save(kom));
    }

    @Override
    public List<Kommune> getKommuner() {
        RestClient.ResponseSpec response = restClient.get()
                .uri(kommuneUrl)
                .header("Accept-Encoding", "identity")
                .retrieve();

        List<Kommune> lst = response.body(new ParameterizedTypeReference<List<Kommune>>() {});
        saveKommuner(lst);
        return lst;
    }
}
