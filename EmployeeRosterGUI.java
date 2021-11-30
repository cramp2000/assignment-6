package assignment;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Diamond extends Application implements EventHandler<Event> {

	// Constants for width and height for canvas
	private static final int CANVAS_WIDTH = 500;
	private static final int CANVAS_HEIGHT = 600;

	// Instance variables
	private Canvas canvas;
	private Button drawBtn;
	private Button exitBtn;
	private GraphicsContext gc;
	private int mouseClickCount;
	private Point2D point1;
	private Point2D point2;

	/**
	 * Default constructor
	 */
	public Diamond() {
		// Instantiate canvas and buttons
		this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		this.drawBtn = new Button("Draw");
		this.exitBtn = new Button("Exit");

		// Get GraphicsContext of canvas
		this.gc = this.canvas.getGraphicsContext2D();

		// Add listener to the buttons
		this.drawBtn.addEventHandler(ActionEvent.ACTION, this);
		this.exitBtn.addEventHandler(ActionEvent.ACTION, this);

		// Add mouse listener for canvas
		this.canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

		// Initalize mouseClickCount to zero
		this.mouseClickCount = 0;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Generate UI
		VBox root = getUI();

		// Create scene to hold the user interface
		Scene scene = new Scene(root);

		// Set scene on primaryStage
		primaryStage.setScene(scene);

		// Show primaryStage
		primaryStage.show();
	}

	/**
	 * This method creates and returns a VBox containing the user interface for the
	 * program.
	 */
	private VBox getUI() {
		// Create a VBox object
		VBox vBox = new VBox();

		// Add canvas to the vBox
		vBox.getChildren().add(this.canvas);

		// Create Hbox to hold the buttons
		HBox buttonBox = new HBox();

		// Add draw and exit buttons to buttonBox
		buttonBox.getChildren().addAll(this.drawBtn, this.exitBtn);

		// Add buttonBox to vBox
		vBox.getChildren().add(buttonBox);

		// Return vBox
		return vBox;
	}

	// This method performs action based on the button clicked.
	@Override
	public void handle(Event event) {
		// Get event type
		EventType eventType = event.getEventType();

		// Check which event is triggered
		if (eventType == ActionEvent.ACTION)
			handleButtonEvent((ActionEvent) event);
		else if (eventType == MouseEvent.MOUSE_CLICKED)
			handleMouseClickEvent((MouseEvent) event);
	}

	/**
	 * This method handles the button click events.
	 */
	private void handleButtonEvent(ActionEvent ae) {
		// Get which button is clicked
		Button srcBtn = (Button) ae.getSource();

		// Check if srcBtn is exitBtn
		if (srcBtn == this.exitBtn) {
			// Exit program
			System.exit(0);

		} else if (srcBtn == this.drawBtn) { // Draw
			// Check if mouse click count is greater than or equal 2
			if (this.mouseClickCount >= 2) {

				// Draw diamond
				drawDiamond();

				// Set mouseClickCount to zero
				this.mouseClickCount = 0;

				// Set point1 and point2 to null
				this.point1 = null;
				this.point2 = null;
			}
		}
	}

	/**
	 * This method handles teh mouse click event.
	 */
	private void handleMouseClickEvent(MouseEvent me) {
		// Increment mouseClickCount by 1
		this.mouseClickCount += 1;

		// Set point1 for odd mouseClickCount
		if ((this.mouseClickCount % 2) == 1)
			this.point1 = new Point2D(me.getX(), me.getY());
		else // Set point2 for even mouseClickCount
			this.point2 = new Point2D(me.getX(), me.getY());
	}

	/**
	 * This method draws a diamond on the canvas.
	 */
	private void drawDiamond() {
		// Check if point1 and point2 are on the same horizontal or vertical
		// line
		if ((this.point1.getY() == this.point2.getY()) || (this.point1.getX() == this.point2.getX())) {

			// Draw a horizontal/vertical line from point1 to point2
			this.gc.strokeLine(this.point1.getX(), this.point1.getY(), this.point2.getX(), this.point2.getY());

		} else { // Draw diamond
			// Variables for point 3 and 4
			Point2D point3, point4;

			// Check if point1 is above point2
			if (this.point1.getY() < this.point2.getY()) {

				// Get point3 and point 4
				point3 = new Point2D(this.point1.getX(), (2 * this.point2.getY()) - this.point1.getY());
				point4 = new Point2D((2 * this.point1.getX()) - this.point2.getX(), this.point2.getY());

			} else { // If point1 is below point2
				// Get point3 and point 4
				point3 = new Point2D((2 * this.point2.getX()) - this.point1.getX(), this.point1.getY());
				point4 = new Point2D(this.point2.getX(), (2 * this.point1.getY()) - this.point2.getY());
			}

			point3 = new Point2D(this.point1.getX(), (2 * this.point2.getY()) - this.point1.getY());
			point4 = new Point2D((2 * this.point1.getX()) - this.point2.getX(), this.point2.getY());

			// Draw symmetric diamond
			this.gc.strokeLine(this.point1.getX(), this.point1.getY(), this.point2.getX(), this.point2.getY());
			this.gc.strokeLine(this.point2.getX(), this.point2.getY(), point3.getX(), point3.getY());
			this.gc.strokeLine(point3.getX(), point3.getY(), point4.getX(), point4.getY());
			this.gc.strokeLine(point4.getX(), point4.getY(), this.point1.getX(), this.point1.getY());
		}
	}

	public static void main(String[] args) {
		// Start application
		launch(args);
	}
}
