// adapted from: https://stackoverflow.com/questions/41267418/how-to-change-a-shape-property-using-its-border-in-javafx

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class Anchor extends Circle {
    // a draggable anchor displayed around a point
    Anchor(Color color, boolean canDragX, boolean canDragY, DragHandler dragHandler) {
        super(0, 0, 5);
        setFill(color.deriveColor(1, 1, 1, 0.5));
        setStroke(color);
        setStrokeWidth(2);
        setStrokeType(StrokeType.OUTSIDE);
        Util.enableDrag(this, canDragX, canDragY, dragHandler);
    }
}
