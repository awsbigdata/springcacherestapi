package software.spring.caching.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="cab_trip_data")
public class CabTrip {

      @Column(name="medallion")
      String medallion;
      @Column(name="hack_license")
      String hack_license;

      @Column(name="vendor_id")
      @Id
      String vendor_id;

      public String getMedallion() {
            return medallion;
      }

      public void setMedallion(String medallion) {
            this.medallion = medallion;
      }

      public String getHack_license() {
            return hack_license;
      }

      public void setHack_license(String hack_license) {
            this.hack_license = hack_license;
      }

      public String getVendor_id() {
            return vendor_id;
      }

      public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
      }

      @Override
      public String toString() {
            return "CabTrip{" +
                    "medallion='" + medallion + '\'' +
                    ", hack_license='" + hack_license + '\'' +
                    ", vendor_id='" + vendor_id + '\'' +
                    '}';
      }
}
