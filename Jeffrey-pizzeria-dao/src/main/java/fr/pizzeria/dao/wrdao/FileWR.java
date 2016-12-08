package fr.pizzeria.dao.wrdao;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FileWR extends IWRDao {

	private String filePath;

	public FileWR(String filePath) {
		this.filePath = "../"+filePath;
		this.setLines(new ArrayList<String>());
	}

	@Override
	public void write() {

		try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get(filePath)))) {
			getLines().forEach((str) -> pw.println(str));
		} catch (IOException e) {
			Logger.getLogger(MySQLWR.class.getName()).severe(e.getMessage());
		}
	}

	@Override
	public void read() {
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			stream.forEach((str) -> super.addLine(str));

		} catch (IOException e) {
			Logger.getLogger(MySQLWR.class.getName()).severe(e.getMessage());
		}
	}

}
