public class GameResult {
    private final GameMove playerMove;
    private final GameMove computerMove;
    private final GameOutcome outcome;

    public GameResult(GameMove playerMove, GameMove computerMove) {
        this.playerMove = playerMove;
        this.computerMove = computerMove;
        this.outcome = determineOutcome(playerMove, computerMove);
    }

    private GameOutcome determineOutcome(GameMove playerMove, GameMove computerMove) {
        if (playerMove == computerMove) {
            return GameOutcome.DRAW;
        }

        if (playerMove.winsAgainst(computerMove)) {
            return GameOutcome.PLAYER_WINS;
        }

        return GameOutcome.COMPUTER_WINS;
    }

    public GameMove getPlayerMove() {
        return playerMove;
    }

    public GameMove getComputerMove() {
        return computerMove;
    }

    public GameOutcome getOutcome() {
        return outcome;
    }

    public String getResultDescription() {
        return switch (outcome) {
            case PLAYER_WINS -> "You win!";
            case COMPUTER_WINS -> "Computer wins!";
            case DRAW -> "It's a draw!";
        };
    }

    @Override
    public String toString() {
        return String.format("You: %s | Computer: %s | %s",
            playerMove.getDisplayName(),
            computerMove.getDisplayName(),
            getResultDescription()
        );
    }
}
