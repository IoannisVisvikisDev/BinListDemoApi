package binlist.demo.api.web.services;

import binlist.demo.api.web.models.BinListResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;


@Service
public class BinListService {

    private static final Logger LOG = LoggerFactory.getLogger(BinListService.class);
    private static final String URL = "https://lookup.binlist.net/";
    
    

    @Autowired
    private WebClient.Builder webClientBuilder;

    public BinListResponseModel callBinList(String binNumberToSend) throws WebClientException{
    	
		return webClientBuilder.build().get().uri(URL + binNumberToSend)
									   .header("Host", "lookup.binlist.net")
									   .retrieve()
									   .bodyToMono(BinListResponseModel.class)
									   .block();
    }

}
