import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;

/**
 * @author Gustavo Beche Lopes - 20106594
 */
public class Inimigo1 extends EnemyAbstract implements DestructionPointsInterface{
    public Inimigo1(int px,int py){
        super(px,py);
        
        setVidas(1);
        setImage("Inimigo1.jpg");
        pointsBaseOnDestruction = 10;
    }

    @Override
    public void OnExitScreen() {
        // setDirH(getDirH()*-1);
        setPosX(0);
        // setPosX(getLMinH());

        return;
    }
}