package binlist.demo.api.web.services;

import binlist.demo.api.web.handlers.RestTemplateResponseErrorHandler;
import binlist.demo.api.web.models.BinListResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class BinListService {

    private static final Logger LOG = LoggerFactory.getLogger(BinListService.class);
    private static final String URL = "https://lookup.binlist.net/";

    private RestTemplate restTemplate;

    public ResponseEntity<BinListResponseModel> callBinList(String binNumberToSend) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Host", "lookup.binlist.net");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<BinListResponseModel> responseEntity;

        try
        {
            responseEntity = restTemplate.exchange(URL + binNumberToSend, HttpMethod.GET, httpEntity, BinListResponseModel.class);
            return responseEntity;
        }
        catch(RestClientException ex){
            LOG.error(ex.getMessage());
        }
        return null;
    }


    public BinListService(){
        this.restTemplate = new RestTemplate();
        this.restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
    }

}
