package com.teamgreen.greenhouse.harvesting;

import com.teamgreen.greenhouse.store.DbHandler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Timestamp;

import static com.teamgreen.greenhouse.utils.MiscellaneousUtils.*;


public class HarvestingDbHandler extends DbHandler {

    public HarvestingDbHandler(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
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
