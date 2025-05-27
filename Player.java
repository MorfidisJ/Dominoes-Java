// Morfidis Ioannis AM: 5740

import java.util.Scanner;
import java.util.Random;

class Player {
    private String name;
    private int points;
    private Hand hand;
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    
    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.hand = new Hand();
    }
    
    public void initializeHand(Stock stock) {
        hand = new Hand();
        for (int i = 0; i < 7; i++) {
            Tile tile = stock.draw();
            if (tile != null) {
                hand.add(tile);
            }
        }
    }
    
    public void printHand() {
        System.out.println(name + ":");
        System.out.println(hand);
    }
    
    public boolean play(Stock stock, Board board) {
        printHand();
        
        boolean validPlay = false;
        while (!validPlay) {
            System.out.print("Select a tile to play, or -1 if no tile:");
            int position = scanner.nextInt();
            
            if (position == -1) {
                Tile drawnTile = draw(stock, board);
                if (drawnTile != null) {
                    System.out.println("Tile to be added:" + drawnTile);
                    if (board.matchBoth(drawnTile)) {
                        System.out.print("Add to the Left (L) or to the Right (R)?");
                        String choice = scanner.next();
                        if (choice.equalsIgnoreCase("L")) {
                            board.addLeft(drawnTile);
                        } else {
                            board.addRight(drawnTile);
                        }
                    } else if (board.matchLeft(drawnTile)) {
                        board.addLeft(drawnTile);
                    } else if (board.matchRight(drawnTile)) {
                        board.addRight(drawnTile);
                    }
                    return true;
                }
                return false;
            } else {
                Tile selectedTile = hand.getTile(position);
                if (selectedTile == null) {
                    System.out.println("Invalid position, please try again.");
                    continue;
                }
                
                System.out.println("Tile to be added:" + selectedTile);
                
                if (!board.match(selectedTile)) {
                    hand.add(selectedTile);
                    System.out.println("Tile doesn't match");
                    return false;
                }
                
                if (board.matchBoth(selectedTile)) {
                    System.out.print("Add to the Left (L) or to the Right (R)?");
                    String choice = scanner.next();
                    if (choice.equalsIgnoreCase("L")) {
                        board.addLeft(selectedTile);
                    } else {
                        board.addRight(selectedTile);
                    }
                } else if (board.matchLeft(selectedTile)) {
                    board.addLeft(selectedTile);
                } else if (board.matchRight(selectedTile)) {
                    board.addRight(selectedTile);
                }
                
                return true;
            }
        }
        return false;
    }
    
    public boolean computerPlay(Stock stock, Board board) {
        printHand();
        
        Tile bestTile = hand.getBestTile(board);
        
        if (bestTile == null) {
            Tile drawnTile = draw(stock, board);
            if (drawnTile != null) {
                System.out.println("Tile to be added:" + drawnTile);
                if (board.matchBoth(drawnTile)) {
                    if (random.nextBoolean()) {
                        board.addLeft(drawnTile);
                    } else {
                        board.addRight(drawnTile);
                    }
                } else if (board.matchLeft(drawnTile)) {
                    board.addLeft(drawnTile);
                } else if (board.matchRight(drawnTile)) {
                    board.addRight(drawnTile);
                }
                return true;
            }
            return false;
        } else {
            System.out.println("Tile to be added:" + bestTile);
            
            if (board.matchBoth(bestTile)) {
                if (random.nextBoolean()) {
                    board.addLeft(bestTile);
                } else {
                    board.addRight(bestTile);
                }
            } else if (board.matchLeft(bestTile)) {
                board.addLeft(bestTile);
            } else if (board.matchRight(bestTile)) {
                board.addRight(bestTile);
            }
            
            return true;
        }
    }
    
    private Tile draw(Stock stock, Board board) {
        int tilesDrawn = 0;
        Tile matchingTile = null;
        
        while (!stock.isEmpty() && matchingTile == null) {
            Tile drawnTile = stock.draw();
            tilesDrawn++;
            
            if (board.match(drawnTile)) {
                matchingTile = drawnTile;
                if (tilesDrawn > 1) {
                    System.out.println("Drew " + tilesDrawn + " tiles, found a match.");
                }
            } else {
                hand.add(drawnTile);
            }
        }
        
        if (matchingTile == null && tilesDrawn > 0) {
            System.out.println("Drew " + tilesDrawn + " tiles, no matches found.");
        }
        
        return matchingTile;
    }
    
    public boolean emptyHand() {
        return hand.isEmpty();
    }
    
    public int handPoints() {
        return hand.points();
    }
    
    public void collectPoints(Player other) {
        this.points += other.handPoints();
    }
    
    public int getPoints() {
        return points;
    }
    
    public String toString() {
        return name;
    }
}