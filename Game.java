package edu.kit.informatik;
import java.util.Arrays;
import java.util.LinkedList;

public class Game {
    private LinkedList<Player> players = new LinkedList<Player>();
    private static String[][] board;
    public Game(int n){
        board = new String[n][n];
        for(int i = 0 ; i<board.length;i++){
            for(int j = 0 ; j<board.length;j++){
                board[i][j] = "**";
            }
        }
    }
    public boolean playTurnTorus(int row1,int col1,int row2, int col2 , Player player) throws IllegalArgumentException{
        if(row1 >= board.length || row1 < 0){
            row1 = row1 % board.length;
        }
        if(row2 >= board.length || row2 < 0){
            row2 = row2 % board.length;
        }
        if(col1 >= board.length || col1 < 0){
            col1 = col1 % board.length;
        }
        if(col2 >= board.length || col2 < 0){
            col2 = col2 % board.length;
        }
        if(
           !board[row1][col1].equals("**")  ||
           !board[row2][col2].equals("**")
           ){
            throw new IllegalArgumentException();
        }else{
            board[row1][col1] = player.getName();
            board[row2][col2] = player.getName();
        }
        return winner(player) || winTorusRow(player) ;
    }
    public boolean playTurn(int row1,int col1,int row2, int col2 , Player player) throws IllegalArgumentException{
        
        if( 
           !board[row1][col1].equals("**")  ||
           !board[row2][col2].equals("**")
           ){
            throw new IllegalArgumentException();
        }else{
            board[row1][col1] = player.getName();
            board[row2][col2] = player.getName();
        }
        return winner(player);
    }
    private static boolean checkRow(int r0, int c0, int dr, int dc, int len, String name) {
        for (int k = 0; k != len; k++) {
            int r = r0 + k * dr;
            int c = c0 + k * dc;
            
            //ArrayOutOfBoundException in 17;17 position
            if (r < 0 || c < 0 || (r >= board.length) || (c >= board[r].length) || !board[r][c].equals(name)) {
                return false;
            }
        }
        return true;
    }
    private boolean winner(Player currentPlayer) {
        String x = currentPlayer.getName();
        for (int r = 0; r != board.length; r++) {
            for (int c = 0; c != board[r].length; c++) {
                if (checkRow(r, c, 0, 1, 6, x)) {
                    return true;
                }
                if (checkRow(r, c, 1, 0, 6, x)) {
                    return true;
                }
                if (checkRow(r, c, 1, 1, 6, x)) {
                    return true;
                }
                if (checkRow(r, c, 1, -1, 6, x)) {
                    return true;
                }
            }
        }
        return false;
    }
    public void printGrid() {
        for (String[] row : board) {
            Terminal.printLine(Arrays.toString(row).replace("[", "").replace("]", "").replace(",", ""));
        }
    }
    public void printRow(int rowNumber) {
        String[] row = board[rowNumber];
        if (!row[rowNumber].equals("P1") && 
                !row[rowNumber].equals("P2") &&
                !row[rowNumber].equals("P3") &&
                !row[rowNumber].equals("P4")) {
                row[rowNumber] = "**";
            }
        Terminal.printLine(Arrays.toString(row).replace("[", "").replace("]", "").replace(",", "")); 
    }
    public void printColumn(int columnNumber){
        String column = "";
        for(int i = 0 ; i<board.length;i++){
             column += board[i][columnNumber]+" ";
        }
        Terminal.printLine(column);
    }
    public void printSlot(int row, int col) {
       Terminal.printLine(board[row][col]);
        }
    public void instantiatePlayers(int numberOfPlayers){
        Player playerOne = new Player("P1");
        Player playerTwo = new Player("P2");
        Player playerThree = new Player("P3");
        Player playerFour = new Player("P4");
        switch(numberOfPlayers){
           case 1 :{ 
            players.add(playerOne);
            break;
        }
           case 2 :{
             players.add(playerOne);
             players.add(playerTwo);
             break;
           }
           case 3 :{
               players.add(playerOne);
               players.add(playerTwo);
               players.add(playerThree);
              break;
           }
           case 4 :{
               players.add(playerOne);
               players.add(playerTwo);
               players.add(playerThree);
               players.add(playerFour);
               break;
           }
           
        }
        
    }
    
    public LinkedList<Player> getPlayers(){
        return players;
    }
    
    public Player determineCurrentPlayer(int turn,int numberOfPlayers){
       if(turn == numberOfPlayers + 1){
           turn = 1;
       }
       return players.get(turn-1);
       
    }
    
    public boolean winTorusRow(Player player){
        boolean won = false;
        for(int i = 0 ; i < board.length ; i++){
                if((board[i][0]+board[i][17]+board[i][16]+board[i][15]+board[i][14]).equals(player.getName())){
                    won = true;
                }
                else if ((board[i][0]+board[i][1]+board[i][2]+board[i][3]+board[i][17])
                        .equals(player.getName()+player.getName()+player.getName()+player.getName()
                                 +player.getName()+player.getName())){
                    won = true;
                }
                else if((board[i][0]+board[i][1]+board[i][17]+board[i][16]+board[i][15]+board[i][14])
                        .equals(player.getName()+player.getName()+player.getName()
                                +player.getName()+player.getName()+player.getName())){
                    won = true;
                }
                else if((board[i][0]+board[i][1]+board[i][2]+board[i][3]+board[i][16]+board[i][17])
                        .equals(player.getName()+player.getName()+player.getName()
                                +player.getName()+player.getName()+player.getName())){
                    won = true;
                }
                else if((board[i][0]+board[i][1]+board[i][2]+board[i][17]+board[i][16]+board[i][15])
                        .equals(player.getName()+player.getName()+player.getName()
                                +player.getName()+player.getName()+player.getName())){
                    won = true;
                }
        }
        return won;
    }
    
    public boolean checkDraw(int currentTurn , boolean won , boolean gameOver){
         gameOver = false;
        if(currentTurn == 324 && won == false){
            Terminal.printLine("draw");
            gameOver = true;
        }
        
        return gameOver;
    }
    
    
    
}
