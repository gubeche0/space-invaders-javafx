import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;

/**
 * @author Gustavo Beche Lopes - 20106594
 */
public class Inimigo3 extends EnemyAbstract implements DestructionPointsInterface{
    private double RELOAD_TIME = 2000000000; // Time is in nanoseconds
    private double shot_timer = 0;

    public Inimigo3(int px,int py){
        super(px,py);
        setLargAlt(45, 40);

        
        setVidas(1);
        setImage("Inimigo3.png");
        pointsBaseOnDestruction = 70;
        getRandoDelayReloadTIme();
    }

    @Override
    public void Update(long deltaTime){
        super.Update(deltaTime);

        if (shot_timer > 0){
            shot_timer -= deltaTime;
        } else {
            Game.getInstance().addChar(new ShotEnemy(getX()+16,getY()+32));
            shot_timer = RELOAD_TIME;
        }
    }

    public void getRandoDelayReloadTIme() {
        RELOAD_TIME = 1500000000 - ((Params.getInstance().nextInt(5)+1) * 100000000) 
            - (Game.getInstance().getFase() * 0.15) * 1000000000;


        if (RELOAD_TIME < 250000000) {
            RELOAD_TIME = 250000000;
            System.out.println("Velocidade de tiro minimo alcando. fase: " + Game.getInstance().getFase());
        }

    }
}