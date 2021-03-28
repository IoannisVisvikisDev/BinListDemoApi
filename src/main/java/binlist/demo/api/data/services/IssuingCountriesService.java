package binlist.demo.api.data.services;

import binlist.demo.api.data.IssuingCountriesRepository;
import binlist.demo.api.data.entities.CardIssuingCountry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;


@Service
public class IssuingCountriesService {

    private final IssuingCountriesRepository countriesRepository;

    public List<CardIssuingCountry> getCardIssuingCountries(){
        List<CardIssuingCountry> allCountriesList = new LinkedList<>();
        countriesRepository.findAll().forEach(country -> allCountriesList.add(country));
        return allCountriesList;
    }

    public CardIssuingCountry getCardIssuingCountry(String isoCode){
        return countriesRepository.findById(isoCode).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void createCardIssuingCountry(CardIssuingCountry countryToAdd){
        countriesRepository.save(countryToAdd);
    }


    public void updateCardIssuingCountry(CardIssuingCountry countryToAdd){
        getCardIssuingCountry(countryToAdd.getIsoCode()); //make sure what was requested is present, or throw 404
        countriesRepository.save(countryToAdd);
    }

    public void deleteCardIssuingCountry(String countryToDeleteIsoCode){
        CardIssuingCountry toDelete = getCardIssuingCountry(countryToDeleteIsoCode); //make sure what was requested is present, or throw 404
        countriesRepository.delete(toDelete);
    }

    @Autowired
    public IssuingCountriesService(IssuingCountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }


}
