package fr.pizzeria.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.Pizza.Category;

public class PizzaMapper implements RowMapper<Pizza> {

	@Override
	public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pizza pizza = new Pizza();
		pizza.setId(rs.getInt("id"));
		pizza.setCode(rs.getString("code"));
		pizza.setName(rs.getString("name"));
		pizza.setPrice(rs.getDouble("price"));
		pizza.setCategory(Category.parseCategory(rs.getString("category")));
		pizza.setImage(rs.getString("image"));
		Pizza.setNbPizza(Pizza.getNbPizza()+1);
		return pizza;
	}

}
