package software.spring.caching.business;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import software.spring.caching.repo.TripRepository;

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
    public Map<String, Integer> getTotaltripsBydate(String pickup_datetime){
        Map<String, Integer> resultset = new HashMap<>();
        List<Object[]> results = CabTripService.findtotaltripBydate(pickup_datetime);
        for (Object[] result : results) {
            String medal = (String) result[0];
            int count = ((Number) result[1]).intValue();
            resultset.put(medal,count);
        }

        logger.debug("Reading from DB");

      return resultset;
    }

    /**
     * Return trips for given medallions
     * @param medallions
     * @return
     */
    @Cacheable(value = "getTotaltrips", key = "#medallions.toString()")
    @Override
    public Map<String, Integer> getTotaltripsBymedal(List<String> medallions){

        Map<String, Integer> resultset = new HashMap<>();
        List<Object[]> results = CabTripService.findtotaltripBymedallion(medallions);
        for (Object[] result : results) {
            String medal = (String) result[0];
            int count = ((Number) result[1]).intValue();
            resultset.put(medal,count);
        }
        logger.debug("Reading from DB");

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
