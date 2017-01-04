package fr.pizzeria.dao.pizzadao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import fr.pizzeria.dao.exception.PizzaException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.Pizza.Category;

public class PizzaDaoFile implements PizzaDao {

	private List<Pizza> listPizzas = new ArrayList<>();

	public PizzaDaoFile(){
		List<String> listFichiers = read("data/");
		listFichiers.forEach(s -> {
			File f = new File(s);
			if (f.canRead()) {
				try {
					InputStream ips = new FileInputStream(f);
					InputStreamReader ipsr = new InputStreamReader(ips);
					BufferedReader br = new BufferedReader(ipsr);
					String ligne = br.readLine();
					String code = f.getName().replaceAll(".txt", "");
					String a[] = ligne.split(";");
					Pizza pizza = new Pizza(code, a[0], Double.parseDouble(a[1]),
							Category.valueOf(a[2].toUpperCase().replaceAll(" ", "_")), true);
					listPizzas.add(pizza);
					br.close();
				} catch (FileNotFoundException e) {
					Logger.getLogger(PizzaDaoFile.class.getName()).severe(e.getMessage());
				} catch (IOException e) {
					Logger.getLogger(PizzaDaoFile.class.getName()).severe(e.getMessage());
				}
			}
		});
		Comparator<Pizza> comp = Comparator.comparing(Pizza::getCode);
		listPizzas = listPizzas.stream().sorted(comp).collect(Collectors.toList());
	}


	@Override
	public List<Pizza> findAllPizzas() {
		return listPizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza){
		Comparator<Pizza> comp = Comparator.comparing(Pizza::getCode);
		Optional<Pizza> p = listPizzas.stream().max(comp);
		if (p.isPresent()) {
			listPizzas.add(pizza);
			int nbPizza = Pizza.getNbPizza();
			nbPizza++;
			Pizza.setNbPizza(nbPizza);
			add(pizza);
		}
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
	public void updatePizza(String codePizza, Pizza pizza) {

		listPizzas.forEach(p -> {
			if (p.getCode().equals(codePizza)) {
				int a = listPizzas.indexOf(p);
				listPizzas.set(a, pizza);
				remove(codePizza);
				add(pizza);
			}
		});

	}

	@Override
	public void deletePizza(String codePizza) {

		Optional<Pizza> findFirst = listPizzas.stream().filter(p -> p.getCode().equals(codePizza)).findFirst();

		if (findFirst.isPresent()) {
			Pizza pizza = findFirst.get();
			listPizzas.remove(pizza);
			remove(codePizza);
		}

		int nbPizza = Pizza.getNbPizza();
		nbPizza--;
		Pizza.setNbPizza(nbPizza);

	}

	public void remove(String pizzaCode) {
		File fichier = new File("data/" + pizzaCode + ".txt");
		if (fichier.delete()) {
			System.out.println("pizza supprimée");
		} else {
			System.out.println("erreur");
		}
	}

	public void add(Pizza pizza){

		File file = new File("data/" + pizza.getCode() + ".txt");
		Path path = file.toPath();
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(pizza.getName() + ";" + pizza.getPrice() + ";" + pizza.getCategory());
		} catch (IOException e) {
			Logger.getLogger(PizzaDaoFile.class.getName()).severe(e.getMessage());
			throw new PizzaException(e);
		}
	}

	public static List<String> read(String dir){

		final List<String> files = new ArrayList<>();

		Path path = Paths.get(dir);
		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(path);
			for (Path entry : stream) {
				files.add(entry.toString());
			}
			stream.close();
		} catch (IOException e) {
			Logger.getLogger(PizzaDaoFile.class.getName()).severe(e.getMessage());
			throw new PizzaException(e);
		}
		return files;

	}

}