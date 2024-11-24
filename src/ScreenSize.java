import java.awt.*;

// helpful utility class to hold information about the display size
public class ScreenSize {
    final static Dimension toolkit = Toolkit.getDefaultToolkit().getScreenSize();

    public static Point getScreenSize()
    {
        int h = toolkit.height;
        int w = toolkit.width;
        return new Point(w, h);
    }

    public static Point getCenter()
    {
        int h = toolkit.height / 2;
        int w = toolkit.width / 2;
        return new Point(w, h);
    }
}
