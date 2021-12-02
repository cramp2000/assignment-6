package assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {

	File file;

	public static void main(String[] args) {

		launch();

	}

	

	@Override

	public void start(Stage primaryStage) throws Exception {

		// nodes

		Button btnLoadEmp = new Button("Load Employees From File");
		Button btnSSM = new Button("Southern Students Monthly");
		Button btnCompPay = new Button("Compute Pay For All Employees");
		TextArea txtDisplay = new TextArea();
		Button btnClose = new Button("Close");
		ScrollBar sb = new ScrollBar();
		Label lblCompPay = new Label("Test");
		EmployeeRoster obj = new EmployeeRoster();

		// event handling

		btnLoadEmp.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				FileChooser fc = new FileChooser();// create file chooser
				file = fc.showOpenDialog(null);
				if (file != null) {
					readFileContent(file, txtDisplay);

				}

				obj.loadEmployeesFromFile(file.toString());

			}

		});

		btnSSM.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				FileChooser fc = new FileChooser();// create file chooser
				file = fc.showOpenDialog(null);
				if (file != null) {
					readFileContent(file, txtDisplay);

				}

				obj.saveSouthernStudentsMonthlyOnly(file.toString());

			}

		});

		btnCompPay.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				double temp = obj.computePayrollForAllEmployees();
				String compPay = Double.toString(temp);

			}

		});

		btnClose.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				primaryStage.close();

			}

		});

		LinkedList<String> list = new LinkedList<String>();
		FileChooser fc = new FileChooser();
		file = fc.showOpenDialog(null);

		if (file != null) {

			readFileContent(file, txtDisplay);

		}

		// pane

		GridPane gp = new GridPane();
		gp.setHgap(50);
		gp.setVgap(50);
		gp.add(btnLoadEmp, 2, 1);
		gp.add(btnSSM, 2, 2);
		gp.add(btnCompPay, 2, 3);
		gp.add(lblCompPay, 2, 4);
		gp.add(btnClose, 2, 5);
		gp.add(sb, 2, 6);

		// Scene

		Scene scene = new Scene(gp, 500, 500);

		// Stage

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void readFileContent(File file, TextArea ta) {
		try {
			Scanner scan = new Scanner(file);
			String temp = "";
			while (scan.hasNextLine()) {
				temp += scan.nextLine() + "\n";

			}

			scan.close();
			ta.setText(temp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}

	}

