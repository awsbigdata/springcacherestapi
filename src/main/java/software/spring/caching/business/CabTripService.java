package software.spring.caching.business;

import java.util.List;
import java.util.Map;

public interface CabTripService {

    public Map<String, List<Map<String,String>>> getTotaltripsBydate(String pickup_datetime);
    public Map<String, List<Map<String,String>>> getTotaltripsBymedal(List<String> medallions);
    public void clearCache();
}
