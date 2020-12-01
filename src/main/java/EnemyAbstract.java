import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;


/**
 * @author Gustavo Beche Lopes - 20106594
 */
abstract class EnemyAbstract extends BasicElement implements DestructionPointsInterface{
    private Image image;
    private String imageSrc = "Inimigo1.jpg";
    private int vidas = 2;
    protected int pointsBaseOnDestruction = 50;

    public EnemyAbstract(int px,int py){
        super(px,py);

    }

    protected void createImage() {
        try{
            image =  new Image(imageSrc,0,40,true,true);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start(){
        createImage();
        setDirH(1);
    }


    @Override
    public void Update(long deltaTime){
        if (jaColidiu()){
            if (vidas > 1) {
                vidas--;
                System.out.println("Perdeu uma vida. " + vidas);
                setColidiu(false);
            } else {
                System.out.println("Perdeu todas vida. " + vidas);
                deactivate();
                return;
            }
        }
        setPosX(getX() + getDirH() * getSpeed());
        // Se chegou na borda da tela...
        if (getX() >= getLMaxH() - getLargura() || getX() < getLMinH()){
            OnExitScreen();
            
            // Posiciona inimigo na borda da tela para impedir que saia da tela
            if (getX() < getLMinH()) {
                setPosX(getLMinH());
            } else if (getX() >= getLMaxH() - getLargura()){
                setPosX(getLMaxH() - getLargura());
            }
        }

        if (getY() >= getLMaxV() - getAltura()) {
            Game.getInstance().loseLife();
            deactivate();
        }
    }

    public void updateVelocidade() {
        // Sorteia o passo de avanço [1,5]
        // setSpeed(Params.getInstance().nextInt(5)+1);
    }

    public void OnExitScreen() {
        // Inverte a direção
        setDirH(getDirH()*-1);

        updateVelocidade();

        // Mover as bolas para baixo
        // if (getY() < 450) {
        setPosY(getY() + 45);
        // }
    }

    
    

    @Override
    public int getPointsDestruction() {
        return pointsBaseOnDestruction;
    }

    @Override
    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.drawImage(image, getX(),getY());
    }

    @Override
    public boolean testaColisao(Character outro){
        if (outro instanceof EnemyAbstract){
            return false;
        }else{
            boolean colidiu = super.testaColisao(outro);
            if (colidiu && outro instanceof Canhao) {
                // Se for canhao o inimigo é destruido náo importando quantas vidas possui
                deactivate();
                Game.getInstance().loseLife();
            } else if (colidiu && outro instanceof Shot) {
                BasicElement el = (Shot) outro;
                el.deactivate();
            }
            return colidiu;
        }
    }

    @Override
    public void deactivate(){
        Game.getInstance().incPontos(this.getPointsDestruction());
        super.deactivate();
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getVidas() {
        return vidas;
    }

    public void setImage(String img) {
        imageSrc = img;
    }
}
