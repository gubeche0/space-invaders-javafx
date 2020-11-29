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
    }

    public void Start() {
        // Repositório de personagens
        activeChars = new LinkedList<>();
        
        pontos = 0;

        // Adiciona o canhao
        canhao = new Canhao(400,550);
        activeChars.add(canhao);

        for(int i=0; i<5; i++){
            // activeChars.add(new Ball(100+(i*60),60+i*40));
        }

        activeChars.add(new Inimigo1(100, 270));
        activeChars.add(new Inimigo1(10,300));

        for(Character c:activeChars){
            c.start();
        }
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
        DrawPoints(graphicsContext);

        for(Character c:activeChars){
            c.Draw(graphicsContext);
        }
    }

    private void DrawPoints(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Paint.valueOf("#CCCCCC"));
        graphicsContext.fillText("Pontos: " + getPontos(), 10, 10);

        System.out.println("Pontos: " + getPontos());
    }

    public int getPontos() {
        return pontos;
    }

    public void incPontos(int pontos) {
        this.pontos += pontos;
    }
}
