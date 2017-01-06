package fr.pizzeria.dao.pizzadao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import fr.pizzeria.mapper.PizzaMapper;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJDBCTemplate implements PizzaDao {

	private JdbcTemplate jdbcTemplate;
	private List<Pizza> listPizza;
	@Autowired
	private DataSource dataSource;
	
	public PizzaDaoJDBCTemplate() {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Pizza> findAllPizzas() {
		Pizza.setNbPizza(0);
		String req = "SELECT * FROM PIZZA";
		listPizza = this.jdbcTemplate.query(req, new PizzaMapper());
		return listPizza;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		String req = "INSERT INTO PIZZA (CATEGORY, CODE, IMAGE, NAME, PRICE) VALUES (?, ?, ?, ?, ?)";
		this.jdbcTemplate.update(req, pizza.getCategory().toString(), pizza.getCode(), pizza.getImage(), pizza.getName(), pizza.getPrice());
		//System.out.println(this.jdbcTemplate.update(req, pizza.getCategory(), pizza.getCode(), pizza.getImage(), pizza.getName(), pizza.getPrice()));
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		String req = "UPDATE PIZZA SET CATEGORY = ?, CODE = ?, IMAGE = ?, NAME = ?, PRICE = ? WHERE ID = ?";
		this.jdbcTemplate.update(req, pizza.getCategory().toString(), pizza.getCode(), pizza.getImage(), pizza.getName(), pizza.getPrice(), pizza.getId());
	}

	@Override
	public void deletePizza(String codePizza) {
		String req = "DELETE FROM PIZZA WHERE CODE = ?";
		this.jdbcTemplate.update(req, codePizza);
	}

	@Override
	public void updatePizza(int codePizza, Pizza pizza) {
		String req = "UPDATE PIZZA SET CATEGORY = ?, CODE = ?, IMAGE = ?, NAME = ?, PRICE = ? WHERE ID = ?";
		this.jdbcTemplate.update(req, pizza.getCategory().toString(), pizza.getCode(), pizza.getImage(), pizza.getName(), pizza.getPrice(), pizza.getId());
	}

	@Override
	public void deletePizza(int codePizza) {
		String req = "DELETE FROM PIZZA WHERE CODE = ?";
		this.jdbcTemplate.update(req, listPizza.get(codePizza-1).getCode());
	}

	@Override
	public void updatePizza(Pizza lastPizzaState, Pizza newPizzaState) {
		String req = "UPDATE PIZZA SET CATEGORY = ?, CODE = ?, IMAGE = ?, NAME = ?, PRICE = ? WHERE ID = ?";
		this.jdbcTemplate.update(req, newPizzaState.getCategory().toString(), newPizzaState.getCode(), newPizzaState.getImage(), newPizzaState.getName(), newPizzaState.getPrice(), lastPizzaState.getId());
	}

	@Override
	public void deletePizza(Pizza deletedPizza) {
		String req = "DELETE FROM PIZZA WHERE ID = ?";
		this.jdbcTemplate.update(req, deletedPizza.getId());
		Pizza.setNbPizza(Pizza.getNbPizza()-1);
	}

}
