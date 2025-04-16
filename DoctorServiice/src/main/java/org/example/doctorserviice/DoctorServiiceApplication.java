package org.example.doctorserviice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DoctorServiiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorServiiceApplication.class, args);
    }

}
