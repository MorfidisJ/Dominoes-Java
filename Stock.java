// Morfidis Ioannis AM: 5740

import java.util.Random;

class Stock {
    private Tile[] tiles;
    private int top;
    private Random random;
    
    public Stock() {
        tiles = new Tile[28];
        top = -1;
        random = new Random();
        int index = 0;
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                tiles[index] = new Tile(i, j);
                index++;
                top++;
            }
        }
        shuffle();
    }
    
    private void shuffle() {
        for (int i = 0; i <= top; i++) {
            int j = random.nextInt(top + 1);
            
            Tile temp = tiles[i];
            tiles[i] = tiles[j];
            tiles[j] = temp;
        }
    }
    
    public Tile draw() {
        if (isEmpty()) {
            return null;
        }
        
        Tile tile = tiles[top];
        tiles[top] = null;
        top--;
        return tile;
    }
    
    public boolean isEmpty() {
        return top == -1;
    }
    
   
    public String toString() {
        String result = "";
        for (int i = 0; i <= top; i++) {
            result += tiles[i].toString();
            if (i < top) {
                result += " ";
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        Stock stock = new Stock();
        System.out.println("Initial stock: " + stock);
        
        
        Tile tile1 = stock.draw();
        System.out.println("Drawn tile: " + tile1);
        
        Tile tile2 = stock.draw();
        tile2.rotate();
        System.out.println("Drawn and rotated tile: " + tile2);
        System.out.println("Stock after drawing two tiles: " + stock);
        while (!stock.isEmpty()) {
            Tile tile = stock.draw();
            System.out.println("Drawn tile: " + tile);
        }
        System.out.println("Is stock empty? " + stock.isEmpty());
    }
}