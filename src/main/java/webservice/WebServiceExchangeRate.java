/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author arnea
 */
public class WebServiceExchangeRate {
    
    private ExchangeRate exchange;
    
    public WebServiceExchangeRate(){
        this.exchange = new ExchangeRate();
    }
    
    public void retrieveAllExchanges(){
        RestTemplate restTemplate = new RestTemplate();
        this.setExchange(restTemplate.getForObject("http://api.fixer.io/latest", ExchangeRate.class));
    }

    public ExchangeRate getExchange() {
        return exchange;
    }
    
    public Double getRate(String key){
        double val = getExchange().getRates().get(key);
        val = val*100;
        val = Math.round(val);
        return val/100;
    }

    private void setExchange(ExchangeRate exchange) {
        this.exchange = exchange;
    }    
    
    public List<String> getRates(){
        return new ArrayList<String>(exchange.getRates().keySet());
    }
}
