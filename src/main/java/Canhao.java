import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

/**
 * Represents the game Gun
 * @author Bernardo Copstein, Rafael Copstein
 */
public class Canhao extends BasicElement implements KeyboardCtrl{
    private int RELOAD_TIME = 250000000; // Time is in nanoseconds
    private int shot_timer = 0;

    public Canhao(int px,int py){
        super(px,py);
        setSpeed(3);
    }

    @Override
    public void start() {
        setLimH(-16,Params.WINDOW_WIDTH-21);
        setLimV(Params.WINDOW_HEIGHT-100,Params.WINDOW_HEIGHT);
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()) {
            Game.getInstance().loseLife();
            setColidiu(false);
        }

        int newPos = getX() + getDirH() * getSpeed();
        if (newPos > getLMaxH() ) {
            setPosX(getLMaxH());
        } else if(newPos < getLMinH()) {
            setPosX(getLMinH());
        } else {
            setPosX(newPos);
        }

        if (shot_timer > 0) shot_timer -= deltaTime;
    }
    
    @Override
    public void OnInput(KeyCode keyCode, boolean isPressed) {
        if (keyCode == KeyCode.LEFT){
            int dh = isPressed ? -1 : 0;
            setDirH(dh);
        }
        if (keyCode == KeyCode.RIGHT){
            int dh = isPressed ? 1 : 0;
            setDirH(dh);
        }
        if (keyCode == KeyCode.SPACE){
            if (shot_timer <= 0) {
                Game.getInstance().addChar(new Shot(getX()+16,getY()-32));
                shot_timer = RELOAD_TIME;
            }
        }
        if (keyCode == KeyCode.U && !isPressed) {
            // Tecla de debug para passar de level
            Game.getInstance().nextLevel();
        }

        if (keyCode == KeyCode.L && !isPressed) {
            // Tecla de debug para ganhar uma vida
            Game.getInstance().winLife();
        }
        //if (keyCode == KeyCode.DOWN) do nothing
    }

    @Override
    public int getAltura(){
        return 80;
    }

    @Override
    public int getLargura(){
        return 32;
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Paint.valueOf("#FF0000"));
        graphicsContext.fillRect(getX(), getY()+16, 32, 32); //base 
        graphicsContext.fillRect(getX()+8, getY()-16, 16, 48); // cano
    }

    @Override
    public boolean testaColisao(Character outro){
        // Não verifica colisão de um tiro com outro tiro
        if (outro instanceof Shot && !(outro instanceof ShotEnemy)){
            return false;
        }else{
            return super.testaColisao(outro);
        }
    }
}
