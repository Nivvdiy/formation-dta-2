package fr.pizzeria.dao.file;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import fr.pizzeria.dao.IWRDao;

public class FileWR extends IWRDao {

	private String filePath;

	public FileWR(String filePath) {
		this.filePath = "../"+filePath;
		this.setLines(new ArrayList<String>());
	}

	@Override
	public void write() {

		try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get(filePath)))) {
			getLines().forEach((str) -> {
				pw.println(str);
			});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void read() {
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			stream.forEach((str) -> super.addLine(str));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
