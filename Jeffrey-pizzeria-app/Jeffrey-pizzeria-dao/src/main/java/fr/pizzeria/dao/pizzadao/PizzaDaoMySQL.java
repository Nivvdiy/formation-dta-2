package fr.pizzeria.dao.pizzadao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import fr.pizzeria.dao.exception.PizzaException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.Pizza.Category;

public class PizzaDaoMySQL implements PizzaDao {

	@FunctionalInterface
	interface IRunSql {
		void exec(Statement st, Connection conn) throws SQLException;
	}

	private List<Pizza> listPizzas = new ArrayList<>();

	public PizzaDaoMySQL(){
		System.out.println("JDBC");

		execute((st, conn) -> {

			try (ResultSet resultats = st.executeQuery("SELECT * FROM PIZZA")) {

				while (resultats.next()) {
					String code = resultats.getString("CODE");
					String name = resultats.getString("NAME");
					Double price = resultats.getDouble("PRICE");
					String cat = resultats.getString("CATEGORY");
					Pizza pizza = new Pizza(code, name, price,
							Category.valueOf(cat.toUpperCase().replaceAll(" ", "_")), true);
					listPizzas.add(pizza);
				}
				resultats.close();
			} catch (SQLException e) {
				Logger.getLogger(PizzaDaoMySQL.class.getName()).severe(e.getMessage());
			}
		});
	}

	public void execute(IRunSql run){
		String url = "jdbc:mysql://phpmyadmin.dev/pizzeria";
		try (Connection connection = DriverManager.getConnection(url, "root", "");
				Statement statement = connection.createStatement();) {

			run.exec(statement, connection);
		} catch (SQLException e) {
			Logger.getLogger(PizzaDaoMySQL.class.getName()).severe(e.getMessage());
			throw new PizzaException(e);
		}

	}


	@Override
	public List<Pizza> findAllPizzas() {
		return listPizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza){
		execute((statement, connection) -> {
			PreparedStatement addPizzaSt = connection
					.prepareStatement("INSERT INTO PIZZA (CODE, NAME, PRICE, CATEGORY) VALUES (?,?,?,?)");
			addPizzaSt.setString(1, pizza.getCode());
			addPizzaSt.setString(2, pizza.getName());
			addPizzaSt.setDouble(3, pizza.getPrice());
			addPizzaSt.setString(4, pizza.getCategory().toString());
			addPizzaSt.executeUpdate();
		});
		listPizzas.add(pizza);
	}

	@Override
	public void updatePizza(int codePizza, Pizza pizza){
		this.updatePizza(listPizzas.get(codePizza-1).getCode(), pizza);
	}

	@Override
	public void deletePizza(int codePizza){
		this.deletePizza(listPizzas.get(codePizza-1).getCode());
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza){

		listPizzas.forEach(p -> {
			if (p.getCode().equals(codePizza)) {
				int a = listPizzas.indexOf(p);
				execute((statement, connection) -> {
					PreparedStatement updatePizzaSt = connection
							.prepareStatement("UPDATE PIZZA SET CODE=?,NAME=?,PRICE=?,CATEGORY=? WHERE CODE = ?");
					updatePizzaSt.setString(1, pizza.getCode());
					updatePizzaSt.setString(2, pizza.getName());
					updatePizzaSt.setDouble(3, pizza.getPrice());
					updatePizzaSt.setString(4, pizza.getCategory().toString());
					updatePizzaSt.setString(5, codePizza);
					updatePizzaSt.executeUpdate();
				});
				listPizzas.set(a, pizza);
			}
		});
	}

	@Override
	public void deletePizza(String codePizza){

		Optional<Pizza> findFirst = listPizzas.stream().filter(p -> p.getCode().equals(codePizza)).findFirst();

		if (findFirst.isPresent()) {
			Pizza pizza = findFirst.get();
			if(pizza != null){
				execute((statement, connection) -> {
					PreparedStatement deletePizzaSt = connection.prepareStatement("DELETE FROM PIZZA WHERE CODE = ?");
					deletePizzaSt.setString(1, codePizza);
					deletePizzaSt.executeUpdate();
				});
				listPizzas.remove(pizza);
			}
		}

	}
}

