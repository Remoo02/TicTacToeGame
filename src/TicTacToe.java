import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");
        Game game = new Game();
        while (!game.over) {
            game.playTurn();
        }
        Game.scan.close();
    }
}


class Game {
    String[][] arr = new String[3][3];
    Player[] players = new Player[2];
    Player curPlayer;
    boolean over = false;
    static Scanner scan = new Scanner(System.in);
    private static int[][][] winningPositions = {
            { { 0, 0 }, { 0, 1 }, { 0, 2 } },
            { { 1, 0 }, { 1, 1 }, { 1, 2 } },
            { { 2, 0 }, { 2, 1 }, { 2, 2 } },
            { { 0, 0 }, { 1, 0 }, { 2, 0 } },
            { { 0, 1 }, { 1, 1 }, { 2, 1 } },
            { { 0, 2 }, { 1, 2 }, { 2, 2 } },
            { { 0, 0 }, { 1, 1 }, { 2, 2 } },
            { { 2, 0 }, { 1, 1 }, { 0, 2 } } };

    Game() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = " ";
            }
        }
        this.players[0] = new Player(1);
        this.players[1] = new Player(2);
        this.curPlayer = players[0];
        drawGame();
    }

    void playTurn() {
        int num = 0;
        int[] twoDvariant;
        boolean checker = true;
        while (checker) {
            System.out.println("Where next? num 1-9");
            num = scan.nextInt();
            if (!(num <= 9 && num >= 1)) {
                System.out.println("bro, this number bad");
                continue;
            }
            twoDvariant = oneDtoTwoD(num);
            if ((!checkIfAvailable(twoDvariant))) {
                System.out.println("This aint available, son!");
                continue;
            }
            arr[twoDvariant[0]][twoDvariant[1]] = this.curPlayer.form;
            drawGame();
            if (checkForWin(twoDvariant)) {
                System.out.println(this.curPlayer.name + " has won the game!!");
                over = true;
            }
            checker = false;
        }
        this.curPlayer = this.curPlayer.playerNum == 1 ? this.players[1] : this.players[0];
    }

    private static int[] oneDtoTwoD(int num) {
        num--;
        return new int[] { num / 3, num % 3 };
    }

    public void drawGame() {
        for (int i = 0; i < 3; i++) {
            System.out.println("%s | %s | %s".formatted((Object[]) arr[i]));
            if (i != 2)
                System.out.println("---------");
        }
    }

    private boolean checkIfAvailable(int[] newNum) {
        return (arr[newNum[0]][newNum[1]].equals(" "));
    }

    private boolean checkForWin(int[] newPos) {
        for (int i = 0; i < winningPositions.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (winningPositions[i][j][0] == newPos[0] && winningPositions[i][j][1] == newPos[1]) {
                    if (arr[(winningPositions[i][0][0])][(winningPositions[i][0][1])].equals(this.curPlayer.form) &&
                            arr[(winningPositions[i][1][0])][(winningPositions[i][1][1])].equals(this.curPlayer.form) &&
                            arr[(winningPositions[i][2][0])][(winningPositions[i][2][1])].equals(this.curPlayer.form)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

class Player {
    String name;
    int playerNum;
    String form;

    public Player(int playerNum) {
        this.playerNum = playerNum;
        this.form = playerNum == 1 ? "ඞ+" : "ඉ"; // This is a so-called ternary operator, it is a shorter form
                                                // of a boring "if-else" Statement ☺
        System.out.println("OI NAME 4 Player" + playerNum + " PLEASE");
        this.name = Game.scan.nextLine();
    }
}