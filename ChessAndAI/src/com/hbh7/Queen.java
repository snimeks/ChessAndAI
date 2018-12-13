package com.hbh7;

import static com.hbh7.Util.*;

public class Queen extends ChessPiece{

    private String pieceType = "Queen";
    private String owner;

    public Queen(String owner, String position) {
        super("Queen", owner, position);
        this.owner = owner;
        this.pieceValue = 6;
    }

    public boolean checkValidMove_movePatternValidCheck(int playerNum, int originalRow, String originalColumn, int newRow, String newColumn, ChessPiece[][] boardArray) {

        // Check that the move pattern requested is valid
        // Ex: Queen can move in any direction forwards, backwards, left, right, or diagonal an unlimited number of spaces
        // until it hits a border or another piece. It cannot move like a Knight.


        // Rook bit

        if((originalColumn.equals(newColumn)) ^ (originalRow == newRow)) { // bitwise XOR, must change only one to be true
            if(originalColumn.equals(newColumn)) {
                // Row is changing, piece is moving vertical
                if (toArrayIndex(originalRow) < toArrayIndex(newRow)) { // If moving downwards
                    boolean error = false;
                    for(int i = toArrayIndex(originalRow)+1; i < toArrayIndex(newRow); i++) {
                        if(boardArray[i][toArrayIndex(originalColumn)] != null) {
                            error = true;
                        }
                    }
                    if (error) {
                        System.out.println("Queen: Invalid Move. Piece(s) obstructing path.");
                        return false;
                    } else {
                        return true;
                    }
                } else { // if moving upwards
                    boolean error = false;
                    for(int i = toArrayIndex(originalRow)-1; i > toArrayIndex(newRow); i--) {
                        if(boardArray[i][toArrayIndex(originalColumn)] != null) {
                            error = true;
                        }
                    }
                    if (error) {
                        System.out.println("Queen: Invalid Move. Piece(s) obstructing path.");
                        return false;
                    } else {
                        return true;
                    }
                }

            } else {
                // Column is changing, piece is moving horizontal
                if (toArrayIndex(originalColumn) < toArrayIndex(newColumn)) { // If moving right
                    boolean error = false;
                    for(int i = toArrayIndex(originalColumn)+1; i < toArrayIndex(newColumn); i++) {
                        if(boardArray[toArrayIndex(originalRow)][i] != null) {
                            error = true;
                        }
                    }
                    if (error) {
                        System.out.println("Queen: Invalid Move. Piece(s) obstructing path.");
                        return false;
                    } else {
                        return true;
                    }
                } else { // if moving left
                    boolean error = false;
                    for (int i = toArrayIndex(originalColumn) - 1; i > toArrayIndex(newColumn); i--) {
                        if (boardArray[toArrayIndex(originalRow)][i] != null) {
                            error = true;
                        }
                    }
                    if (error) {
                        System.out.println("Queen: Invalid Move. Piece(s) obstructing path.");
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        } else if(Math.abs(toArrayIndex(originalRow)-toArrayIndex(newRow)) == Math.abs(toArrayIndex(originalColumn)-toArrayIndex(newColumn))) { // Bishop Bit

            int distance = Math.abs(toArrayIndex(originalRow) - toArrayIndex(newRow));

            if (toArrayIndex(newRow) < toArrayIndex(originalRow) && toArrayIndex(newColumn) > toArrayIndex(originalColumn)) { // going to top right
                for (int i = 1; i < distance; i++) {
                    if (boardArray[toArrayIndex(originalRow) - i][toArrayIndex(originalColumn) + i] != null) {
                        System.out.println("Queen: Invalid Move. Piece(s) obstructing path.");
                        return false;
                    }
                }
                return true;

            } else if (toArrayIndex(newRow) < toArrayIndex(originalRow) && toArrayIndex(newColumn) < toArrayIndex(originalColumn)) { // going to top left
                for (int i = 1; i < distance; i++) {
                    if (boardArray[toArrayIndex(originalRow) - i][toArrayIndex(originalColumn) - i] != null) {
                        System.out.println("Queen: Invalid Move. Piece(s) obstructing path.");
                        return false;
                    }
                }
                return true;

            } else if (toArrayIndex(newRow) > toArrayIndex(originalRow) && toArrayIndex(newColumn) > toArrayIndex(originalColumn)) { // going to bottom right
                for (int i = 1; i < distance; i++) {
                    if (boardArray[toArrayIndex(originalRow) + i][toArrayIndex(originalColumn) + i] != null) {
                        System.out.println("Queen: Invalid Move. Piece(s) obstructing path.");
                        return false;
                    }
                }
                return true;

            } else if (toArrayIndex(newRow) > toArrayIndex(originalRow) && toArrayIndex(newColumn) < toArrayIndex(originalColumn)) { // going to bottom left
                for (int i = 1; i < distance; i++) {
                    if (boardArray[toArrayIndex(originalRow) + i][toArrayIndex(originalColumn) - i] != null) {
                        System.out.println("Queen: Invalid Move. Piece(s) obstructing path.");
                        return false;
                    }
                }
                return true;

            } else {
                System.out.println("Queen: Honestly I have no idea how you got here.");
                return false;
            }
        } else {
            return false;
        }



    }


    public PieceData aiFindSpacesToAttack(ChessPiece[][] boardArray) {
        // Pawn code, revise for specific moves
        if(boardArray[arrayPosRow + 1][arrayPosColumn+1] != null) {
            if(boardArray[arrayPosRow + 1][arrayPosColumn+1].getOwner().equals("White")) {
                return new PieceData(arrayPosRow+1, arrayPosColumn+1, this.pieceValue);
            }

        } else if(boardArray[arrayPosRow + 1][arrayPosColumn-1] != null) {
            if(boardArray[arrayPosRow + 1][arrayPosColumn-1].getOwner().equals("White")) {
                return new PieceData(arrayPosRow+1, arrayPosColumn-1, this.pieceValue);
            }
        } else {
            return null;
        }
        return null;
    }

}
