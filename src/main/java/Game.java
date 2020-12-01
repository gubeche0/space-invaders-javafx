import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import java.util.List;
import java.util.LinkedList;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;



/**
 * Handles the game lifecycle and behavior
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Game {
    private static Game game = null;
    private Canhao canhao;
    private List<Character> activeChars;
    private int pontos;
    private int vidas;
    private int fase;

    private Game(){
    }

    public static Game getInstance(){
        if (game == null){
            game = new Game();
        }
        return(game);
    }

    public void addChar(Character c){
        activeChars.add(c);
        c.start();
    }

    public void eliminate(Character c){
        activeChars.remove(c);
        verifyFaseComplete();
    }

    public void Start() {
        // Repositório de personagens
        activeChars = new LinkedList<>();
        pontos = 0;
        vidas = 3;
        fase = 0;

        nextLevel();
    }

    public void Update(long currentTime, long deltaTime) {
        for(int i=0;i<activeChars.size();i++){
            Character este = activeChars.get(i);
            este.Update(deltaTime);
            for(int j =0; j<activeChars.size();j++){
                Character outro = activeChars.get(j);
                if ( este != outro){
                    este.testaColisao(outro);
                }
            }
        }
    }

    public void OnInput(KeyCode keyCode, boolean isPressed) {
        canhao.OnInput(keyCode, isPressed);
    }

    public void Draw(GraphicsContext graphicsContext) {
        drawPoints(graphicsContext);
        drawLifes(graphicsContext);
        drawFase(graphicsContext);

        if (perdeu()) {
            drawGameOver(graphicsContext);
        }
        

        for(Character c:activeChars){
            c.Draw(graphicsContext);
        }
    }

    private void drawPoints(GraphicsContext graphicsContext) {
        graphicsContext.setFont(new Font("Arial", 20));
        graphicsContext.setFill(Paint.valueOf("#000000"));
        graphicsContext.fillText("Pontos: " + getPontos(), 10, 20);
    }

    private void drawLifes(GraphicsContext graphicsContext) {
        graphicsContext.setFont(new Font("Arial", 20));
        graphicsContext.setFill(Paint.valueOf("#000000"));
        graphicsContext.fillText("Vidas: " + getVidas(), 10, 40);
    }

    private void drawFase(GraphicsContext graphicsContext) {
        graphicsContext.setFont(new Font("Arial", 40));
        graphicsContext.setFill(Paint.valueOf("#000000"));
        graphicsContext.fillText("Level: " + getFase(), Params.WINDOW_WIDTH/2 -60, perdeu()? 80: 40);
    }

    private void drawGameOver(GraphicsContext graphicsContext) {
        graphicsContext.setFont(new Font("Arial", 40));
        graphicsContext.setFill(Paint.valueOf("#000000"));
        graphicsContext.fillText("Gamer Over", 280, 40);
    }

    private void verifyFaseComplete() {
        if (activeChars.stream().filter(c -> c instanceof EnemyAbstract).count() == 0) {
            nextLevel();
        }
    }

    private void nextLevel() {
        if (perdeu()) {
            return;
        }
        fase++;
        
        activeChars.clear();

        // Adiciona o canhao
        canhao = new Canhao(400,550);
        activeChars.add(canhao);

        // activeChars.add(new Inimigo1(10, 50));
        // activeChars.add(new Inimigo1(200,50));
        activeChars.add(new Inimigo1(400,50));
        activeChars.add(new Inimigo1(600,50));

        activeChars.add(new Inimigo2(300,500));
        activeChars.add(new Inimigo2(200,500));

        for(Character c:activeChars){
            c.start();
        }
    }

    public int getPontos() {
        return pontos;
    }

    public void incPontos(int pontos) {
        this.pontos += pontos;
    }

    public int getVidas() {
        return vidas;
    }

    public boolean isAlive() {
        return vidas >= 1;
    }

    public void loseLife() {
        vidas--;

        if (perdeu()) {
            activeChars.clear();            
        }
    }

    public boolean perdeu() {
        return vidas <= 0;
    }

    public Integer getFase() {
        return fase;
    }
}
