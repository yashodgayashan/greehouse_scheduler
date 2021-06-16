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
import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.ThreadLocalRandom;

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

    private static final Logger logger = LoggerFactory.getLogger(HarvestingController.class);
    private HarvestingDbHandler harvestingDbHandler;
    LocalDate start = LocalDate.of(2019, Month.OCTOBER, 14);
    LocalDate end = LocalDate.now();

    @PostConstruct
    void setJdbcHandlers() {
        harvestingDbHandler = new HarvestingDbHandler(jdbc, namedParamJdbc);
    }

    @GetMapping("/")
    public ResponseEntity harvest()  {
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("Hiii");
    }


    @Scheduled(fixedRate = 5000)
    public void reportCurrsentTime() {
        generatePlantDiseaseBadulla(1, 2);
        System.out.println("Hiii");
    }


    // // Different range for different places

    // Different

    public  LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    public int generatePlantDiseaseBadulla(int plantId, int greenhouseId) {
        int diseaseId = generateDiseaseId(1);
        int solutionId = generateSolutionId(diseaseId);
        int date = generateDateRangeBadulla(solutionId);
        LocalDate startDate = between(start, end);
        LocalDate endDate =  startDate.plusDays(date);
        return harvestingDbHandler.addPlantDisease(plantId, greenhouseId, diseaseId, solutionId, startDate.toString(), endDate.toString());
    }

    public int generateDiseaseId(int plantId){
        if (plantId == 1) {
            return (int) ((Math.random() * (10 - 1)) + 1);
        } else if (plantId == 2) {
            return (int) ((Math.random() * (12 - 10)) + 10);
        } else {
            return 12;
        }
    }

    public int generateSolutionId(int diseaseid) {
        int num = (int) ((Math.random() * (4 - 1)) + 1);
        return ((diseaseid - 1) * 3) + num;
    }

    public int generateDateRangeBadulla(int solutionId) {
        int num = solutionId % 3;
        if (num == 0) {
            return (int) ((Math.random() * (9 - 5)) + 5);
        } else if (num == 1) {
            return (int) ((Math.random() * (7 - 4)) + 4);
        } else {
            return (int) ((Math.random() * (6 - 2)) + 2);
        }
    }

}
