package com.teamgreen.greenhouse.harvesting;

import com.teamgreen.greenhouse.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Random;

import static com.teamgreen.greenhouse.constants.Constants.INTERNAL_SERVER_ERROR_MSG;

@RestController
@RequestMapping("/harvest")
public class HarvestingController {

    @Autowired
    JdbcTemplate jdbc;
    @Autowired
    NamedParameterJdbcTemplate namedParamJdbc;
    @Autowired
    RestTemplate restTemplate;

    @Value("${remote.harvest.url}")
    private String remoteUrl;

    private static final Logger logger = LoggerFactory.getLogger(HarvestingController.class);
    FileUtils fileUtils;

    private HarvestingDbHandler handler;

    @PostConstruct
    void setJdbcHandlers() {
        handler = new HarvestingDbHandler(this.jdbc, this.namedParamJdbc);
    }

    @GetMapping("/")
    public ResponseEntity harvest()  {
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Scheduled(fixedRate = 5000)
    public void feedTemperatureValue() {
        int[] nodeSensorList = {1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34};
        for (Integer nodeSensorId: nodeSensorList) {
            handler.addTemperature(nodeSensorId);
        }
    }

    @Scheduled(fixedRate = 5000)
    public void feedHumidityValue() {
        int[] nodeSensorList = {2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35};
        for (Integer nodeSensorId: nodeSensorList) {
            handler.addHumidity(nodeSensorId);
        }
    }

    @Scheduled(fixedRate = 5000)
    public void feedLightIntensityValue() {
        int[] nodeSensorList = {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36};
        for (Integer nodeSensorId: nodeSensorList) {
            handler.addLightIntensity(nodeSensorId);
        }
    }
}
