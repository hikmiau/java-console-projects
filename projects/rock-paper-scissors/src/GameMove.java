public enum GameMove {
    ROCK("Rock"),
    PAPER("Paper"),
    SCISSORS("Scissors");

    private final String displayName;

    GameMove(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean winsAgainst(GameMove otherMove) {
        if (this == otherMove) {
            return false;
        }

        return (this == ROCK && otherMove == SCISSORS) ||
               (this == PAPER && otherMove == ROCK) ||
               (this == SCISSORS && otherMove == PAPER);
    }

    public static GameMove getRandomMove() {
        GameMove[] moves = GameMove.values();
        int randomIndex = (int) (Math.random() * moves.length);
        return moves[randomIndex];
    }
}
