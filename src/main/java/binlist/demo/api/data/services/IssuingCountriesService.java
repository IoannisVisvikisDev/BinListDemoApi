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

    public CardIssuingCountry createCardIssuingCountry(CardIssuingCountry countryToAdd){
        try{
            getCardIssuingCountry(countryToAdd.getIsoCode()); //will throw NOT_FOUND if country not present
            //if a NOT_FOUND is not thrown, the entry already exists. Show conflict
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        catch (ResponseStatusException rse){
            if(!(rse.getStatus() == HttpStatus.NOT_FOUND)) throw rse; //something other than not found went wrong. Throw and investigate
        }
        return countriesRepository.save(countryToAdd);
    }


    public CardIssuingCountry updateCardIssuingCountry(CardIssuingCountry countryToAdd){
        getCardIssuingCountry(countryToAdd.getIsoCode()); //make sure what was requested is present, or throw 404
        return countriesRepository.save(countryToAdd);
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
