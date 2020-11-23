import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents a simple ball that crosses the screen over and over again
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Ball extends BasicElement{
    public Ball(int px,int py){
        super(px,py);
    }

    @Override
    public void start(){
        setDirH(1);
    }


    @Override
    public void Update(long deltaTime){
        if (jaColidiu()){
            deactivate();
        }else{
            setPosX(getX() + getDirH() * getSpeed());
            // Se chegou no lado direito da tela ...
            if (getX() >= getLMaxH()){
                // Reposiciona no lado esquerdo e ...
                setPosX(getLMinH());
                // Sorteia o passo de avanço [1,5]
                setSpeed(Params.getInstance().nextInt(5)+1);


                // Mover as bolas para baixo
                if (getY() < 450) {
                    setPosY(getY() + 45);
                }
            }
        }
    }

    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.setFill(Paint.valueOf("#0000FF"));
        graphicsContext.fillOval(getX(), getY(), 32, 32);
    }
}
