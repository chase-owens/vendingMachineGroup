/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author chaseowens
 */
public class ItemMapper implements RowMapper<Item>{

    @Override
    public Item mapRow(ResultSet rs, int i) throws SQLException {
        Item item = new Item();
        
        item.setName(rs.getString("title"));
        item.setPrice(rs.getBigDecimal("price"));
        item.setInventoryCount(rs.getInt("inventoryCount"));
        
        return item;
    }
    
}
