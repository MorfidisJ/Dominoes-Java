// Morfidis Ioannis AM: 5740

class Tile {
    private int leftValue;
    private int rightValue;
    
    public Tile(int left, int right) {
        if (left <= right) {
            this.leftValue = left;
            this.rightValue = right;
        } else {
            this.leftValue = right;
            this.rightValue = left;
        }
    }
    
    public int getLeftValue() {
        return leftValue;
    }
    
    public int getRightValue() {
        return rightValue;
    }
    
    public void rotate() {
        int temp = leftValue;
        leftValue = rightValue;
        rightValue = temp;
    }

    public int points() {
        return leftValue + rightValue;
    }

    public String toString() {
        return "[" + leftValue + "|" + rightValue + "]";
    }
}