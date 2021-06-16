package com.teamgreen.greenhouse.harvesting;

import com.teamgreen.greenhouse.store.DbHandler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static com.teamgreen.greenhouse.utils.MiscellaneousUtils.*;

public class HarvestingDbHandler extends DbHandler {

    public HarvestingDbHandler(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    int addTemperature(long nodeSensorId)  {
        final String insertQuery =
                "INSERT INTO " + GREENHOUSES_TABLE + " ("  + withComma(GREENHOUSE_NAME) + withComma(GREENHOUSE_LOCATION)
                        + withComma(GREENHOUSE_LOCATIONS_ID) + withComma(GREENHOUSE_HEIGHT) + withComma(GREENHOUSE_LENGTH)
                        + withComma(GREENHOUSE_WIDTH) + encapFieldWithBackTick(GREENHOUSE_IMAGE_URL) + ") VALUES "
                        + getStatementParams(7);

        return this.jdbcTemplate().update(insertQuery, greenhouse.getName(), greenhouse.getLocation(),
                greenhouse.getLocationId(), greenhouse.getHeight(), greenhouse.getLength(), greenhouse.getWidth(),
                greenhouse.getImageURL());
    }

    int addTemperature(long nodeSensorId)  {
        final String insertQuery =
                "INSERT INTO " + GREENHOUSES_TABLE + " ("  + withComma(GREENHOUSE_NAME) + withComma(GREENHOUSE_LOCATION)
                        + withComma(GREENHOUSE_LOCATIONS_ID) + withComma(GREENHOUSE_HEIGHT) + withComma(GREENHOUSE_LENGTH)
                        + withComma(GREENHOUSE_WIDTH) + encapFieldWithBackTick(GREENHOUSE_IMAGE_URL) + ") VALUES "
                        + getStatementParams(7);

        return this.jdbcTemplate().update(insertQuery, greenhouse.getName(), greenhouse.getLocation(),
                greenhouse.getLocationId(), greenhouse.getHeight(), greenhouse.getLength(), greenhouse.getWidth(),
                greenhouse.getImageURL());
    }

    int addTemperature(long nodeSensorId)  {
        final String insertQuery =
                "INSERT INTO " + GREENHOUSES_TABLE + " ("  + withComma(GREENHOUSE_NAME) + withComma(GREENHOUSE_LOCATION)
                        + withComma(GREENHOUSE_LOCATIONS_ID) + withComma(GREENHOUSE_HEIGHT) + withComma(GREENHOUSE_LENGTH)
                        + withComma(GREENHOUSE_WIDTH) + encapFieldWithBackTick(GREENHOUSE_IMAGE_URL) + ") VALUES "
                        + getStatementParams(7);

        return this.jdbcTemplate().update(insertQuery, greenhouse.getName(), greenhouse.getLocation(),
                greenhouse.getLocationId(), greenhouse.getHeight(), greenhouse.getLength(), greenhouse.getWidth(),
                greenhouse.getImageURL());
    }
}
