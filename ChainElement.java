// Morfidis Ioannis AM: 5740
class ChainElement {
    private Tile tile;
    private ChainElement next;
    private ChainElement prev;
    
    public ChainElement(Tile tile) {
        this.tile = tile;
        this.next = null;
        this.prev = null;
    }
    
    public Tile getTile() {
        return tile;
    }
    
    public ChainElement getNext() {
        return next;
    }
    
    public void setNext(ChainElement next) {
        this.next = next;
    }
    
    public ChainElement getPrev() {
        return prev;
    }
    
    public void setPrev(ChainElement prev) {
        this.prev = prev;
    }
}