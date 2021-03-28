package binlist.demo.api.data.controllers;


import binlist.demo.api.data.entities.CardIssuingCountry;
import binlist.demo.api.data.models.DeleteCountryRequest;
import binlist.demo.api.data.services.IssuingCountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/countries")
public final class CardIssuingCountryController {

    @Autowired
    private final IssuingCountriesService issuingCountriesService;


    @PostMapping
    public void addCountry(@Valid @RequestBody CardIssuingCountry countryToAdd){
        issuingCountriesService.createCardIssuingCountry(countryToAdd);
    }

    @PutMapping
    public void updateCountry(@Valid @RequestBody CardIssuingCountry countryToAdd){
        issuingCountriesService.updateCardIssuingCountry(countryToAdd);
    }

    @GetMapping("/{countryIsoCode}")
    public CardIssuingCountry getCountry(@PathVariable String countryIsoCode){
        return issuingCountriesService.getCardIssuingCountry(countryIsoCode);
    }

    @GetMapping
    public List<CardIssuingCountry> getCountries(){
        return issuingCountriesService.getCardIssuingCountries();
    }

    @DeleteMapping
    public void deleteCountry(@Valid @RequestBody DeleteCountryRequest deleteRequest){
        issuingCountriesService.deleteCardIssuingCountry(deleteRequest.getIsoCode());
    }

    @Autowired
    public CardIssuingCountryController(IssuingCountriesService issuingCountriesService) {
        this.issuingCountriesService = issuingCountriesService;
    }

}
