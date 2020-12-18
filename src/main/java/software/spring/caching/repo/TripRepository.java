package software.spring.caching.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import software.spring.caching.model.CabTrip;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TripRepository  extends JpaRepository<CabTrip, Timestamp>{

    @Query(value="select medallion,count(1) trips from cab_trip_data where  medallion in (:medallions) group by medallion",nativeQuery = true)
    List<Object[]> findtotaltripBymedallion(@Param("medallions") List<String> medallions);

    @Query(value="select medallion,count(1) trips from cab_trip_data where date_format(pickup_datetime, '%Y-%m-%d')=:pickupdate group by medallion",nativeQuery = true)
    List<Object[]> findtotaltripBydate(@Param("pickupdate") String pickupdate);

    @Query(value="select medallion,count(1) trips from cab_trip_data group by medallion",nativeQuery = true)
    List<Object[]> gettotaltrips();


}
