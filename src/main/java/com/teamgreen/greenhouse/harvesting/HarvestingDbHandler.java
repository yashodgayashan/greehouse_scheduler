package com.teamgreen.greenhouse.harvesting;

import com.teamgreen.greenhouse.store.DbHandler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Random;
import java.sql.Timestamp;

import static com.teamgreen.greenhouse.utils.MiscellaneousUtils.*;
import static com.teamgreen.greenhouse.harvesting.Constants.*;

public class HarvestingDbHandler extends DbHandler {

    public HarvestingDbHandler(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    int addTemperature(long nodeSensorId)  {
        double upperLimit = 26.66;
        double lowerLimit = 23.88;

        Random rand = new Random();
        double randomNumber = lowerLimit + (upperLimit - lowerLimit)*rand.nextDouble();

        final String insertQuery =
                "INSERT INTO " + DATA_TABLE + " ("  + withComma(DATA_DATA) + encapFieldWithBackTick(DATA_NODE_SENSOR_ID) + ") VALUES "
                        + getStatementParams(2);

        return this.jdbcTemplate().update(insertQuery, Math.round(randomNumber*100.0)/100.0, nodeSensorId);
    }

    int addHumidity(long nodeSensorId)  {
        double upperLimit = 60;
        double lowerLimit = 70;

        Random rand = new Random();
        double randomNumber = lowerLimit + (upperLimit - lowerLimit)*rand.nextDouble();

        final String insertQuery =
                "INSERT INTO " + DATA_TABLE + " ("  + withComma(DATA_DATA) + encapFieldWithBackTick(DATA_NODE_SENSOR_ID) + ") VALUES "
                        + getStatementParams(2);

        return this.jdbcTemplate().update(insertQuery, Math.round(randomNumber*100.0)/100.0, nodeSensorId);
    }

    int addLightIntensity(long nodeSensorId) {
        double upperLimit = 70;
        double lowerLimit = 80;

        Random rand = new Random();
        double randomNumber = lowerLimit + (upperLimit - lowerLimit) * rand.nextDouble();

        final String insertQuery =
                "INSERT INTO " + DATA_TABLE + " (" + withComma(DATA_DATA) + encapFieldWithBackTick(DATA_NODE_SENSOR_ID) + ") VALUES "
                        + getStatementParams(2);

        return this.jdbcTemplate().update(insertQuery, Math.round(randomNumber * 100.0) / 100.0, nodeSensorId);
    }

    public int addPlantDisease(int plantId, int greenhouseId, int diseaseId, int solutionId, String appliedDate, String resolvedDate) {
        final String insertQuery =
                "INSERT INTO plant_disease ("  + withComma("plant_info_id") + withComma("greenhouse_id")
                        + withComma("disease_id") + withComma("solution_id") + withComma("applied_date")
                        + encapFieldWithBackTick("resolved_date") + ") VALUES "
                        + getStatementParams(6);

        return this.jdbcTemplate().update(insertQuery, plantId, greenhouseId, diseaseId, solutionId, appliedDate, resolvedDate);
    }
}
