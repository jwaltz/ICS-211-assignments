public class Meep implements Comparable<Meep> {
    
    private int size;

    public Meep(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return "M" + this.getSize();
    }

    public boolean equals(Meep m) {
        return this.getSize() == m.getSize();
    }

    public int compareTo(Meep m) {
        return this.getSize() - m.getSize();
    }


}
