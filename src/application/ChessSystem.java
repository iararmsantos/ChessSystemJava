
package application;

import boardgame.Board;
import boardgame.Position;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Iara
 */
public class ChessSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();
        
        while(!chessMatch.getCheckmate()){
            try{
                UI.clearScreen();
                UI.PrintMatch(chessMatch, captured);                
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(tec);
                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(tec);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                if(capturedPiece != null){
                    captured.add(capturedPiece);
                }
                if (chessMatch.getPromoted() != null) {
                    System.out.print("Enter piece for promotion (B/N/R/Q): ");
                    String type = tec.nextLine();
                    chessMatch.replacePromotedPiece(type);
                }
            }
            catch(ChessException e){
                System.out.println(e.getMessage());
                tec.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println(e.getMessage());
                tec.nextLine();
            }
        }
        UI.clearScreen();
        UI.PrintMatch(chessMatch, captured);
        
    }
    
}
