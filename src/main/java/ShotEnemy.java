import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;

/**
 * @author Gustavo Beche Lopes - 20106594
 */
public class ShotEnemy extends Shot{
    public ShotEnemy(int px,int py){
        super(px,py);
    }

    @Override
    public void start(){
        setDirV(1);
        setSpeed(3);
    }

    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.setFill(Paint.valueOf("#FF0000"));
        graphicsContext.fillOval(getX(), getY(), 8, 16);
    }

    @Override
    public boolean testaColisao(Character outro){
        // Não verifica colisão de um tiro com outro tiro
        if (outro instanceof Shot || outro instanceof EnemyAbstract){
            return false;
        }else{
            return super.testaColisao(outro);
        }
    }
    
}