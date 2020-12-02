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

    @Override
    public void getRandoDelayReloadTIme() {
        RELOAD_TIME = 1500000000 - ((Params.getInstance().nextInt(5)+1) * 100000000) 
            - (Game.getInstance().getFase() * 0.15) * 1000000000;


        if (RELOAD_TIME < 250000000) {
            RELOAD_TIME = 250000000;
            System.out.println("Velocidade de tiro minimo alcando. fase: " + Game.getInstance().getFase());
        }
    }
}