package com.springdatajpa.app.runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springdatajpa.app.model.States;
import com.springdatajpa.app.repository.StatesRepository;

@Component
public class ReadExcelSheetRunner implements CommandLineRunner {
	@Autowired
	private StatesRepository spreadSheetRepository;

	@Override
	public void run(String... args) throws Exception {

		String fileLocation = "C:\\Users\\DELL\\Desktop\\States.xlsx";

		List<States> statesList = readExcelSheetAndReturnList(fileLocation);
		statesList.stream().forEach(System.out::println);
		//List<States> savedAllStates = spreadSheetRepository.saveAll(statesList);
			System.out.println("=======Saved records========");
		//savedAllStates.stream().forEach(System.out::println);

	}

	private List<States> readExcelSheetAndReturnList(String fileLocation) throws FileNotFoundException, IOException {
		List<States> states = new ArrayList<>();
		try (FileInputStream fileInputStream = new FileInputStream(new File(fileLocation))) {
			// open the file from a given location:
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			// Retrieve the first sheet of the file and iterate through each row:
			Sheet sheetOne = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> iterator = sheetOne.iterator();
			boolean flag = false;
			while (iterator.hasNext()) {
				States state = new States();
				Row row = (Row) iterator.next();

				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				if (flag) {// Skip the first heading row

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						// Check the cell type and format accordingly
						switch (cell.getCellType()) {
						case STRING:
							int columnIndex = cell.getColumnIndex();
							if (columnIndex == 1) {
								state.setName(cell.getStringCellValue());
							} else if (columnIndex == 2) {
								state.setCapital(cell.getStringCellValue());
							} else if (columnIndex == 3) {
								state.setCode(cell.getStringCellValue());
							}

							break;
						case NUMERIC:
							state.setId((int) cell.getNumericCellValue());
							break;

						default:
							break;

						}

					}
					states.add(state);
				}
				flag = true;
			}
			return states;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return states;
	}

}
