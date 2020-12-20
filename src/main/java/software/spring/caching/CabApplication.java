package software.spring.caching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"software.spring"})
@EnableCaching
public class CabApplication {
    public static void main(String[] args) {
        SpringApplication.run(CabApplication.class, args);
    }
}
