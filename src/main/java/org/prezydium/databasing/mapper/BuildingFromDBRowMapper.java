package org.prezydium.databasing.mapper;

import org.prezydium.databasing.model.BuildingFromDB;
import org.prezydium.databasing.model.BuildingType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuildingFromDBRowMapper implements RowMapper<BuildingFromDB> {
    @Override
    public BuildingFromDB mapRow(ResultSet rs, int rowNum) throws SQLException {
        BuildingFromDB buildingFromDB = new BuildingFromDB();
        buildingFromDB.setId(rs.getLong("ID"));
        buildingFromDB.setBuildingType(BuildingType.valueOf(rs.getString("BUILDING_TYPE")));
        buildingFromDB.setCity(rs.getString("CITY"));
        buildingFromDB.setStreet(rs.getString("STREET"));
        buildingFromDB.setBuildingNumber(rs.getString("NUMBER"));
        return buildingFromDB;
    }
}
