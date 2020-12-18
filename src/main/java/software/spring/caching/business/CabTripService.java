package software.spring.caching.business;

import java.util.List;
import java.util.Map;

public interface CabTripService {

    public Map<String, Integer> getTotaltripsBydate(String pickup_datetime);
    public Map<String, Integer> getTotaltripsBymedal(List<String> medallions);
    public void clearCache();
}
