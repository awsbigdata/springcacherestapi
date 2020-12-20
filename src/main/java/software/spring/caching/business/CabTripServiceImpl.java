package software.spring.caching.business;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import software.spring.caching.repo.TripRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CabTripServiceImpl implements CabTripService {

    private Logger logger = LoggerFactory.getLogger(CabTripServiceImpl.class);


    @Autowired
    TripRepository CabTripService;

    @Autowired
    CacheManager cacheManager;

    /**
     * Return trips for given date
     * @param pickup_datetime
     * @return
     */
    @Cacheable(value = "getTotaltrips", key = "#pickup_datetime")
    @Override
    public Map<String, List<Map<String,String>>> getTotaltripsBydate(String pickup_datetime){
        Map<String, List<Map<String,String>>> resultset = new HashMap<>();
        List<Object[]> results = CabTripService.findtotaltripBydate(pickup_datetime);
        List<Map<String,String>> items=new ArrayList<>();
        for (Object[] result : results) {
            Map<String,String> item = new HashMap<>();
            String medal = (String) result[0];
            String count =  result[1].toString();
            item.put("medallion",medal);
            item.put("trips",count);
            items.add(item);
        }

        logger.debug("Reading from DB");
       resultset.put("medallions",items);
      return resultset;
    }

    /**
     * Return trips for given medallions
     * @param medallions
     * @return
     */
    @Cacheable(value = "getTotaltrips", key = "#medallions.toString()")
    @Override
    public Map<String, List<Map<String,String>>> getTotaltripsBymedal(List<String> medallions){
        Map<String, List<Map<String,String>>> resultset = new HashMap<>();
        List<Map<String,String>> items=new ArrayList<>();
        List<Object[]> results = CabTripService.findtotaltripBymedallion(medallions);
        for (Object[] result : results) {
            Map<String,String> item = new HashMap<>();
            String medal = (String) result[0];
            String count =  result[1].toString();
            item.put("medallion",medal);
            item.put("trips",count);
            items.add(item);
        }
        logger.debug("Reading from DB");
        resultset.put("medallions",items);
        return resultset;
    }

    /**
     * Clear all cache from cache service
     */
    public void clearCache(){

        logger.debug("Clearing cache details");
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    };

}
