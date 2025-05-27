// Morfidis Ioannis AM: 5740

import java.util.Random;

class Board {
    private Chain chain;
    
    public Board() {
        chain = new Chain();
    }
    
    public boolean isEmpty() {
        return chain.isEmpty();
    }
    
    public boolean matchLeft(Tile tile) {
        if (isEmpty()) {
            return true;
        }
        
        return tile.getLeftValue() == chain.getLeftValue() || 
               tile.getRightValue() == chain.getLeftValue();
    }
    
    public boolean matchRight(Tile tile) {
        if (isEmpty()) {
            return true;
        }
        
        return tile.getLeftValue() == chain.getRightValue() || 
               tile.getRightValue() == chain.getRightValue();
    }
    
   
    public boolean matchBoth(Tile tile) {
        if (isEmpty()) {
            return true;
        }
        
        boolean leftMatch = tile.getLeftValue() == chain.getLeftValue() || 
                           tile.getRightValue() == chain.getLeftValue();
        boolean rightMatch = tile.getLeftValue() == chain.getRightValue() || 
                            tile.getRightValue() == chain.getRightValue();
        return leftMatch && rightMatch;
    }
    
    public boolean match(Tile tile) {
        if (isEmpty()) {
            return true;
        }
        
        boolean leftMatch = tile.getLeftValue() == chain.getLeftValue() || 
                           tile.getRightValue() == chain.getLeftValue();
        boolean rightMatch = tile.getLeftValue() == chain.getRightValue() || 
                            tile.getRightValue() == chain.getRightValue();
        return leftMatch || rightMatch;
    }
    
    public void addLeft(Tile tile) {
        if (isEmpty()) {
            chain.insertLeft(tile);
            return;
        }
        if (tile.getLeftValue() == chain.getLeftValue()) {
            tile.rotate();
        }
        
        chain.insertLeft(tile);
    }
    
    public void addRight(Tile tile) {
        if (isEmpty()) {
            chain.insertRight(tile);
            return;
        }
        if (tile.getRightValue() == chain.getRightValue()) {
            tile.rotate();
        }
        
        chain.insertRight(tile);
    }
    
    public String toString() {
        return "Board\n" + chain.toString();
    }
    
    public static void main(String[] args) {
        Stock stock = new Stock();
        Board board = new Board();   
        
        Tile firstTile = stock.draw();
        System.out.println("First tile: " + firstTile);
        board.addLeft(firstTile);
        System.out.println(board);
        
        while (!stock.isEmpty()) {
            Tile tile = stock.draw();
            System.out.println("Drawn tile: " + tile);
            
            boolean leftMatch = board.matchLeft(tile);
            boolean rightMatch = board.matchRight(tile);
            
            if (leftMatch && rightMatch) {
                if (new Random().nextBoolean()) {
                    System.out.println("Adding to left: " + tile);
                    board.addLeft(tile);
                } else {
                    System.out.println("Adding to right: " + tile);
                    board.addRight(tile);
                }
            } else if (leftMatch) {
                System.out.println("Adding to left: " + tile);
                board.addLeft(tile);
            } else if (rightMatch) {
                System.out.println("Adding to right: " + tile);
                board.addRight(tile);
            } else {
                System.out.println("Tile doesn't match, discarding");
                continue;
            }
            
            System.out.println(board);
        }
    }
}