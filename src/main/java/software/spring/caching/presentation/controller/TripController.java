package software.spring.caching.presentation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.spring.caching.business.CabTripServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * it is used to read the trips details
 */
@RestController
public class TripController {

    @Autowired
    CabTripServiceImpl CabTripServiceImpl;

    @GetMapping(path="/tripsbyday", produces = "application/json")
    public Map<String, Integer> gettotalTripsBydate(@RequestParam(value = "pickup_date", defaultValue = "")
                                                                String pickup_date) {
        return  CabTripServiceImpl.getTotaltripsBydate(pickup_date);
    }


    @GetMapping(path="/tripsbymed", produces = "application/json")
    public Map<String, Integer> gettotalTripsBymedallion(
                                              @RequestParam(value = "medallions", defaultValue = "")
                                                      List<String> medallions) {
        return  CabTripServiceImpl.getTotaltripsBymedal(medallions);
    }

    @PostMapping(path="/clear", produces = "application/json")
    public String cacheclear() {
        CabTripServiceImpl.clearCache();
        return "success";
    }

}
