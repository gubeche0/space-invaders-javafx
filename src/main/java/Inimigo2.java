import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;

/**
 * @author Gustavo Beche Lopes - 20106594
 */
public class Inimigo2 extends EnemyAbstract implements DestructionPointsInterface{
    public Inimigo2(int px,int py){
        super(px,py);
        
        setVidas(2);
        setImage("Inimigo2.png");
        pointsBaseOnDestruction = 70;
    }
}