import java.awt.*;

public class FontFactory
{
    public static Font createFont(int fontSize)
    {
        return new Font("SANS_SERIF", Font.PLAIN, fontSize);
    }
}
