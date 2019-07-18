
package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

/**
 *
 * @author Iara
 */
public class ChessMatch {
    private Board board;
    
    public ChessMatch(){
        board = new Board(8,8);
        initialSetup();
    }
    
    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRow()][board.getColumn()];
        for(int i = 0; i < board.getRow(); i++) {
            for(int j = 0; j < board.getColumn(); j++) {
                    mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
	}
    
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        //convertendo para Position
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        //validar posição atual
        validateSourcePosition(source);
        //validar posição pretendida
        validateTargetPosition(source, target);
        //movimentar peça
        Piece capturedPiece = makeMove(source, target);
        //para movimentar uma peça(piece) ela precisa ser convertida para ChessPiece
        return (ChessPiece)capturedPiece;
    }
    
    //validar posição
    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new ChessException("There is no piece on source position.");
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("There is no moves for the chosen piece.");
        }
    }
    //validar posição pretendida
    private void validateTargetPosition(Position source, Position target){
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("The chosen piece can't move to target position");
        }
    }
    
    //método para fazer o movimento
    private Piece makeMove(Position source, Position target){
        //pegar a peça
        Piece p = board.removePiece(source);
        //leva a peça até o destino(target)
        Piece capturedPiece = board.removePiece(target);
        //colocar a peça no destino
        board.placePiece(p, target);
        return capturedPiece;
    }
    
    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }
    private void initialSetup(){
        //white pieces
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        //black pieces
        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
    }

