package lk.ijse.whalewatchingcenter;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "lk.ijse.whalewatchingcenter.repo")
@SpringBootApplication(scanBasePackages = "lk.ijse.whalewatchingcenter")
@EntityScan(basePackages = "lk.ijse.whalewatchingcenter.entity")
public class WhaleWatchingCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhaleWatchingCenterApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
