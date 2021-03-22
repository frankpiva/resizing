// adapted from: https://stackoverflow.com/questions/41267418/how-to-change-a-shape-property-using-its-border-in-javafx

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class ResizingSampleRefactor extends Application {
    private Pane root;
    private Node selectedNode;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        // create some shapes
        Ellipse ellipse = new Ellipse(100, 100, 50, 50);
        ellipse.setFill(Color.BLUE);
        Rectangle rectangle = new Rectangle(200, 250, 100, 100);
        rectangle.setFill(Color.YELLOW);
        // create a new pane with our shapes
        root = new Pane(ellipse, rectangle);
        // create a new scene with our pane
        Scene scene = new Scene(root, 400, 400, Color.RED);
        // set the scene and show it on the screen
        stage.setScene(scene);
        stage.show();

        root.setOnMouseClicked(event -> {
            final Parent parentNode = ((Node) event.getTarget()).getParent();
            if (selectedNode != null && !(parentNode instanceof ResizingControl)) {
                root.getChildren().removeIf(candidate -> candidate instanceof ResizingControl);
                selectedNode = null;
            }
        });

        makeSelectable(ellipse, rectangle);
    }

    private void makeSelectable(Node... nodes) {
        for (Node node: nodes) {
            node.setOnMouseClicked(event -> {
                if (selectedNode != node) {
                    root.getChildren().removeIf(candidate -> candidate instanceof ResizingControl);
                    selectedNode = node;

                    node.toFront();
                    ResizingControl resizingControl = new ResizingControl(node);
                    root.getChildren().add(resizingControl);
                }

                event.consume();
            });
        }
    }
}
