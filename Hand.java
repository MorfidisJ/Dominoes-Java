// Morfidis Ioannis AM: 5740
import java.util.ArrayList;

class Hand {
    private ArrayList<Tile> tiles;
    
    public Hand() {
        tiles = new ArrayList<>();
    }
    
    public void add(Tile tile) {
        tiles.add(tile);
    }
    
    public Tile getTile(int position) {
        if (position < 0 || position >= tiles.size()) {
            return null;
        }
        
        Tile tile = tiles.get(position);
        tiles.remove(position);
        return tile;
    }
    
    public Tile getBestTile(Board board) {
        Tile bestTile = null;
        int maxPoints = -1;
        int bestPosition = -1;
        
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            if (board.match(tile) && tile.points() > maxPoints) {
                bestTile = tile;
                maxPoints = tile.points();
                bestPosition = i;
            }
        }
        
        if (bestPosition != -1) {
            tiles.remove(bestPosition);
        }
        
        return bestTile;
    }
    
    public boolean isEmpty() {
        return tiles.isEmpty();
    }
    
    public int points() {
        int totalPoints = 0;
        for (Tile tile : tiles) {
            totalPoints += tile.points();
        }
        return totalPoints;
    }
    
    public String toString() {
        String[] tileStrings = new String[tiles.size()];
        String[] positionStrings = new String[tiles.size()];
        
        for (int i = 0; i < tiles.size(); i++) {
            tileStrings[i] = tiles.get(i).toString();
            int tileWidth = tileStrings[i].length();
            
            // Calculate position width 
            String positionStr = "" + i;  // Convert integer to string 
            int positionWidth = positionStr.length();
            
            int leadingSpaces = (tileWidth - positionWidth) / 2;
            int trailingSpaces = tileWidth - leadingSpaces - positionWidth;
            
            // Create spaces 
            String leadingSpacesStr = "";
            for (int j = 0; j < leadingSpaces; j++) {
                leadingSpacesStr += " ";
            }
            
            String trailingSpacesStr = "";
            for (int j = 0; j < trailingSpaces; j++) {
                trailingSpacesStr += " ";
            }
            
            positionStrings[i] = leadingSpacesStr + i + trailingSpacesStr;
        }
        
        String topLine = String.join(" ", tileStrings);
        String bottomLine = String.join(" ", positionStrings);
        return topLine + "\n" + bottomLine;
    }
}