package binlist.demo.api.web.controlers;

import binlist.demo.api.web.models.BinListResponseModel;
import binlist.demo.api.web.models.MyRequestBody;
import binlist.demo.api.web.models.MyResponse;
import binlist.demo.api.data.entities.CardIssuingCountry;
import binlist.demo.api.data.services.IssuingCountriesService;

import binlist.demo.api.web.services.BinListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RestController
@RequestMapping("/payment-cards-cost")
public class BinListApiController {

    private final IssuingCountriesService issuingCountriesService;
    private final BinListService binListService;

    private static final Logger LOG = LoggerFactory.getLogger(BinListApiController.class);
    private static final String OTHERS = "10.00";


    @PostMapping
    public MyResponse callBinListAndRespond(@Valid @RequestBody MyRequestBody requestBody){
        String binNumberToSend = requestBody.getCardNumber().substring(0, 7);
        ResponseEntity<BinListResponseModel> binListResponseEntity =  binListService.callBinList(binNumberToSend);
//        if (binListResponseEntity == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //populate my response
        BinListResponseModel binListResponseBody = binListResponseEntity.getBody();
        LOG.info(binListResponseBody.toString());
        String responseCoutryIsoCode = binListResponseBody.getCountry().getAlpha2();
        CardIssuingCountry country;
        //query H2 database for any country entry having this iso code
        try{
            country = issuingCountriesService.getCardIssuingCountry(responseCoutryIsoCode);
        }
        catch (ResponseStatusException rsEx){
            if(rsEx.getStatus() == HttpStatus.NOT_FOUND){
                //Not found in Countries database. Set response value to default
                country = new CardIssuingCountry(responseCoutryIsoCode, OTHERS);
            }
            else{
                //something else happened, throw exception to investigate
                throw rsEx;
            }
        }

        MyResponse myResponse = new MyResponse();
        myResponse.setCountry(country.getIsoCode());
        myResponse.setCost(Double.parseDouble(country.getClearingCostUSD()));

        return myResponse;
    }



    @Autowired
    public BinListApiController(IssuingCountriesService issuingCountriesService, BinListService binListService) {
        this.issuingCountriesService = issuingCountriesService;
        this.binListService = binListService;
    }

}
