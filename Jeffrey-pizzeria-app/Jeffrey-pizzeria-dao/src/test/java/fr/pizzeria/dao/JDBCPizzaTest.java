package fr.pizzeria.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pizzeria.dao.pizzadao.PizzaDaoJDBCTemplate;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.Pizza.Category;

@Repository
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestConfigDao.class)
public class JDBCPizzaTest {

	@Autowired
	private PizzaDaoJDBCTemplate jdbcTemplateDao;
	
    @Test
    public void test() {
    	Pizza pizza = new Pizza("LOL","Trololol",15.25,Category.POISSON,false);
    	pizza.setImage("");
    	jdbcTemplateDao.saveNewPizza(pizza);
    	Assert.assertEquals(jdbcTemplateDao.findAllPizzas().get(Pizza.getNbPizza()-1), jdbcTemplateDao.findAllPizzas().get(Pizza.getNbPizza()-1));
    }
	
}