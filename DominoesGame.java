// Morfidis Ioannis AM: 5740
import java.util.Scanner;

class DominoesGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to dominoes!");
        System.out.print("Give your player name: ");
        String playerName = scanner.nextLine();
        
        Player human = new Player(playerName);
        Player computer = new Player("Computer");
        
        boolean playAgain = true;
        while (playAgain) {
            DominoesRound round = new DominoesRound(human, computer);
            round.playRound();
            
            System.out.print("Do you want to play another round? (y/n) ");
            String choice = scanner.nextLine();
            playAgain = choice.equalsIgnoreCase("y");
        }
        
        System.out.println("Thank you for playing!");
    }
}