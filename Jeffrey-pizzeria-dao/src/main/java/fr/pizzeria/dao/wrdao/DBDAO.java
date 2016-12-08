package fr.pizzeria.dao.wrdao;

import fr.pizzeria.dao.wrdao.MySQLWR.IRunSql;
import fr.pizzeria.exception.PizzaException;

public interface DBDAO {


	void execute(IRunSql run) throws PizzaException;

	void deleteEntryFromDB(String code) throws PizzaException;

	void updateEntryFromDB(String pastCode, String pizza) throws PizzaException;

	void insertEntryFromDB(String pizza) throws PizzaException;
}
