package com.evonem.customer;

import jakarta.persistence.Column;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Slf4j
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) {

        Customer customer = new Customer();

        try {
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setEmail(rs.getString("email"));
            customer.setAge(rs.getInt("age"));
        } catch (SQLException exception){
            exception.printStackTrace();
            log.error("Failed to map the ResultSet from {}", CustomerRowMapper.class);
        }

        return customer;
    }
}
