import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tower {
    private int score;
    private List<Piece> pieces;

    public Tower() {
        this.pieces = new ArrayList<>();
    }

    public int getScore() {
        return score;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public int calculateScore() {
        // Check rules 1 and 2
        if (!pieces.get(0).getType().equals("Door")) {
            return 0;
        } else if (!pieces.get(pieces.size() - 1).getType().equals("Lookout")) {
            return 0;
        }
        // Check rule 3
        // Exclude the first and last pieces since they shouldn't be walls
        for (int i = 1; i < pieces.size() - 1; i++) {
            if (!pieces.get(i).getType().equals("Wall")) {
                return 0;
            }
        }
        // Check rule 4
        for (int i = 0; i < pieces.size() - 1; i++) {
            if (pieces.get(i).getWidth() < pieces.get(i + 1).getWidth()) {
                return 0;
            }
        }
        // Check rule 5
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).getStrength() < pieces.size() - 1 - i) {
                return 0;
            }
        }
        score = 10 + ((int) Math.pow(pieces.size(), 2)) - calculatePieceCost();
        return score;
    }

    public int calculatePieceCost() {
        int cost = 0;
        for (Piece piece : pieces) {
            cost += piece.getCost();
        }
        return cost;
    }

    public void mutation() {
        Random random = new Random();
        // Grabs two random positions within the size of the tower
        int piece1Index = random.nextInt(pieces.size());
        int piece2Index = random.nextInt(pieces.size());
        Piece piece1 = pieces.get(piece1Index);
        Piece piece2 = pieces.get(piece2Index);
        // Swaps the positions of the grabbed pieces
        pieces.set(piece1Index, piece2);
        pieces.set(piece2Index, piece1);
    }
}
