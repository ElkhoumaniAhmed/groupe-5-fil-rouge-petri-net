package petrinet;

/**
 * A place holds a number of tokens (never negative).
 */
public class Place {

    private int tokens; // current number of tokens

    public Place(int tokens) {
        if (tokens < 0) {
            throw new IllegalArgumentException("The number of tokens must be >= 0");
        }
        this.tokens = tokens;
    }

    public int getTokens() {
        return tokens;
    }

    // Add n tokens (n must be >= 0)
    public void addTokens(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Cannot add a negative number of tokens");
        }
        tokens = tokens + n;
    }

    // Remove n tokens (n must be >= 0 and <= current tokens)
    public void removeTokens(int n) {
        if (n < 0 || n > tokens) {
            throw new IllegalArgumentException("Not enough tokens to remove");
        }
        tokens = tokens - n;
    }

}
