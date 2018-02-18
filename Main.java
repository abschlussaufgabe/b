package edu.kit.informatik;
public class Main {
    static Game game = new Game(18);
    static int currentTurn = 1;
    static boolean running = true;
    static boolean gameOver = false;
    public static void main(String[] args){
        //String[] commands = args[0].split("\\s");
        //if(commands.length < 0 || commands.length > 3){}
        //String mode = commands[0];
        //int boardSize = Integer.parseInt(commands[1]);
        //int numberOfPlayers = Integer.parseInt(commands[2]);
        while (running) {
            String[] input = Terminal.readLine().split(" ", 2);
            String[] params = null;
            if (input.length > 1) {
                params = input[1].split(";");
            }
            switch (input[0]) {
            case "place": {
                if(params.length > 4){
                    Terminal.printError("wrong number of arguments");
                    break;
                }
                if(gameOver){
                    Terminal.printError("game already over");
                    break;
                }
                 try {
                    game.instantiatePlayers(4);
                    Player currentPlayer = game.determineCurrentPlayer(currentTurn, 4);
                    boolean won = game.playTurn(Integer.parseInt(params[0]), Integer.parseInt(params[1]),
                                                     Integer.parseInt(params[2]) , Integer.parseInt(params[3]),
                                                        currentPlayer);
                    if (won) {
                        Terminal.printLine(currentPlayer.getName() + " " + "wins");
                        gameOver = true;
                    } else {
                        Terminal.printLine("OK");
                        currentTurn++;
                    }
                    gameOver = game.checkDraw(currentTurn, won, gameOver);
            }catch(IllegalArgumentException iae){
                Terminal.printError("slot already full");
                
            }catch(Exception e){
                    Terminal.printError("unexisting slot");
                }
                break;
            }
            case "print": {
                if(input.length > 1){
                    Terminal.printError("no arguments allowed with the print command");
                    break;
                }
                game.printGrid();
                break;
            }
            case "rowprint": {
                game.printRow(Integer.parseInt(params[0]));
                break;
            }
            case "colprint" : {
                game.printColumn(Integer.parseInt(params[0]));
                break;
            }
            case "state": {
                if(params.length > 2){
                    Terminal.printError("unexisting slot");
                    break;
                }
                try {
                    game.printSlot(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
                } catch (Exception e) {
                    Terminal.printError("unexisting slot");
                }
                break;
            }
            case "quit": {
                if(input.length > 1){
                    Terminal.printError("no arguments allowed with the quit command ");
                    break;
                }
                running = false;
                break;
            }
            case "reset": {
                game = new Game(18);
                currentTurn = 1;
                Terminal.printLine("OK");
                break;
            }
            default:
                Terminal.printError("unknown command");
            }
        }
    }
    }
    


