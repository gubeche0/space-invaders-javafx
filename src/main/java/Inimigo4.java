import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;

/**
 * @author Gustavo Beche Lopes - 20106594
 */
public class Inimigo4 extends Inimigo3 implements DestructionPointsInterface{
    public Inimigo4(int px,int py){
        super(px,py);
        
        setVidas(2);
        setImage("Inimigo4.jpg");
        pointsBaseOnDestruction = 100;
    }
}