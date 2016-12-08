package fr.pizzeria.dao.wrdao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import fr.pizzeria.exception.PizzaException;

public class MySQLWR extends IWRDao implements DBDAO {

	@FunctionalInterface
	interface IRunSql {
		void exec(Connection conn, Statement st) throws SQLException;
	}

	private List<String> updateAction;

	public MySQLWR() {
		this.setLines(new ArrayList<String>());
		updateAction = new ArrayList<>();
	}

	@Override
	public void write() throws PizzaException {
		execute((conn, st) -> {

		});

	}

	@Override
	public void read() throws PizzaException {

		execute((conn, st) -> {

			try (ResultSet resultats = st.executeQuery("SELECT * FROM PIZZA")) {

				while (resultats.next()) {
					String code = resultats.getString("CODE");
					String name = resultats.getString("NOM");
					double price = resultats.getDouble("PRIX");
					String category = resultats.getString("CATEGORY");
					String line = new StringBuilder(code).append(";").append(name).append(";").append(price).append(";")
							.append(category).toString();
					super.addLine(line);
				}
				resultats.close();
			} catch (SQLException e) {
				Logger.getLogger(MySQLWR.class.getName()).severe(e.getMessage());
			}
		});

	}

	@Override
	public void deleteEntryFromDB(String code) throws PizzaException {
		execute((conn, st) -> st.executeUpdate("DELETE FROM PIZZA WHERE CODE = " + code));
	}

	@Override
	public void updateEntryFromDB(String pastCode, String pizza) throws PizzaException {
		execute((conn, st) -> {
			String[] pizzaArray = pizza.split(";");
			PreparedStatement updatePizzaSt = (PreparedStatement) conn
					.prepareStatement("UPDATE PIZZA SET PRICE=? NAME=? CODE=? CATEGORY=? WHERE CODE=?");
			updatePizzaSt.setDouble(1, Double.parseDouble(pizzaArray[2]));
			updatePizzaSt.setString(2, pizzaArray[1]);
			updatePizzaSt.setString(3, pizzaArray[0]);
			updatePizzaSt.setString(4, pizzaArray[3]);
			updatePizzaSt.setString(5, pastCode);
			updatePizzaSt.executeUpdate();
		});
	}

	@Override
	public void insertEntryFromDB(String pizza) throws PizzaException {
		execute((conn, st) -> {
			String[] pizzaArray = pizza.split(";");
			PreparedStatement insertPizzaSt = (PreparedStatement) conn
					.prepareStatement("INSERT INTO PIZZA (CODE, NOM, PRIX, CATEGORY) VALUES ('?', '?', '?', '?')");
			insertPizzaSt.setString(1, pizzaArray[0]);
			insertPizzaSt.setString(2, pizzaArray[1]);
			insertPizzaSt.setString(3, pizzaArray[2]);
			insertPizzaSt.setString(4, pizzaArray[3]);
			insertPizzaSt.executeUpdate();
		});
	}

	@Override
	public void execute(IRunSql run) throws PizzaException {
		ResourceBundle bundle = ResourceBundle.getBundle("mysql");
		String url = bundle.getString("mysql.url");
		String user = bundle.getString("mysql.user");
		String pass = bundle.getString("mysql.pass");

		try (Connection connection = (Connection) DriverManager.getConnection(url, user, pass);
				Statement statement = connection.createStatement();) {

			run.exec(connection, statement);
		} catch (SQLException e) {
			Logger.getLogger(MySQLWR.class.getName()).severe(e.getMessage());
		}

	}

	public void saveNewPizza(String pizza) {
		updateAction.add("null/SAVE/" + pizza);
	}

	public void updatePizza(String pastPizza, String pizza) {
		updateAction.add(pastPizza + "/UPDATE/" + pizza);
	}

	public void removePizza(String pastPizza) {
		updateAction.add(pastPizza + "/UPDATE/null");
	}

}
