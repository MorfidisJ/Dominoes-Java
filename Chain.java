// Morfidis Ioannis AM: 5740

class Chain {
    private ChainElement leftEnd;
    private ChainElement rightEnd;
    private int leftValue;
    private int rightValue;
    
    public Chain() {
        leftEnd = null;
        rightEnd = null;
    }
    
    public boolean isEmpty() {
        return leftEnd == null;
    }
    
    public void insertLeft(Tile tile) {
        ChainElement element = new ChainElement(tile);
        
        if (isEmpty()) {
            leftEnd = element;
            rightEnd = element;
            leftValue = tile.getLeftValue();
            rightValue = tile.getRightValue();
        } else {
            element.setNext(leftEnd);
            leftEnd.setPrev(element);
            leftEnd = element;
            leftValue = tile.getLeftValue();
        }
    }
    
    public void insertRight(Tile tile) {
        ChainElement element = new ChainElement(tile);
        
        if (isEmpty()) {
            leftEnd = element;
            rightEnd = element;
            leftValue = tile.getLeftValue();
            rightValue = tile.getRightValue();
        } else {
            rightEnd.setNext(element);
            element.setPrev(rightEnd);
            rightEnd = element;
            rightValue = tile.getRightValue();
        }
    }
    
    public int getLeftValue() {
        return leftValue;
    }
    
    public int getRightValue() {
        return rightValue;
    }
    
    
    public String toString() {
        if (isEmpty()) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        ChainElement current = leftEnd;
        
        while (current != null) {
            sb.append(current.getTile().toString());
            current = current.getNext();
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        Stock stock = new Stock();
        Chain chain = new Chain();
        
        System.out.println("Initial chain: " + chain);
        for (int i = 0; i < 10; i++) {
            Tile tile = stock.draw();
            if (i % 2 == 0) {
                System.out.println("Adding to left: " + tile);
                chain.insertLeft(tile);
            } else {
                System.out.println("Adding to right: " + tile);
                chain.insertRight(tile);
            }
            System.out.println("Chain: " + chain);
        }
    }
}