package com.example.tablename;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.columnnames.Course;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class DataRepository {
//====================import header columns into database================================//
    private final JdbcTemplate jdbcTemplate;

    public DataRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> fetchData(String tableName) {
        String sql = "SELECT * FROM " + tableName;
        return jdbcTemplate.queryForList(sql);
    }

    public List<String> getColumnNames(String tableName) {
        String sql = "SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = ?";
        return jdbcTemplate.queryForList(sql, String.class, tableName);
    }
    
    
    
 

}