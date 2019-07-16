
package boardgame;

/**
 *
 * @author Iara
 */
public class Piece {
   protected Position position;
   private Board board;

   public Piece(Board board) {
        this.board = board;
        position = null;
   }

    protected Board getBoard() {//pode ser usado apenas pelo boardgame 
        return board;
    }  
   
}
