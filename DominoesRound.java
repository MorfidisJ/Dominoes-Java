// Morfidis Ioannis AM: 5740

class DominoesRound {
    private Player human;
    private Player computer;
    private Stock stock;
    private Board board;
    
    public DominoesRound(Player human, Player computer) {
        this.human = human;
        this.computer = computer;
        this.stock = new Stock();
        this.board = new Board();
    }
    
    public void playRound() {
       
        human.initializeHand(stock);
        computer.initializeHand(stock);
        boolean humanTurn = true;
        boolean humanCanPlay = true;
        boolean computerCanPlay = true;
        
        while ((humanCanPlay || computerCanPlay) && 
               !human.emptyHand() && !computer.emptyHand()) {
            
            System.out.println(board);
            
            if (humanTurn) {
                humanCanPlay = human.play(stock, board);
                if (human.emptyHand()) {
                    System.out.println("Human won! Emptied Hand");
                    human.collectPoints(computer);
                    break;
                }
            } else {
                computerCanPlay = computer.computerPlay(stock, board);
                if (computer.emptyHand()) {
                    System.out.println("Computer won! Emptied Hand");
                    computer.collectPoints(human);
                    break;
                }
            }
            
           
            humanTurn = !humanTurn;
            
            if (!humanCanPlay && !computerCanPlay) {
                System.out.println("Game blocked!");
                
                int humanPoints = human.handPoints();
                int computerPoints = computer.handPoints();
                
                if (humanPoints < computerPoints) {
                    System.out.println("Human won! Fewer points in hand");
                    human.collectPoints(computer);
                } else {
                    System.out.println("Computer won! Fewer points in hand");
                    computer.collectPoints(human);
                }
                
                break;
            }
        }
        System.out.println(human + " points:" + human.getPoints());
        System.out.println(computer + " points:" + computer.getPoints());
    }
}