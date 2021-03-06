import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Individual {
    private float fitness;
    private List<Float> bin1;
    private List<Float> bin2;
    private List<Float> bin3;
    private List<Float> bin4;

    public Individual() {
        this.bin1 = new ArrayList<>();
        this.bin2 = new ArrayList<>();
        this.bin3 = new ArrayList<>();
        this.bin4 = new ArrayList<>();
    }

    public float getFitness() {
        return fitness;
    }

    public List<Float> getBin1() {
        return bin1;
    }

    public List<Float> getBin2() {
        return bin2;
    }

    public List<Float> getBin3() {
        return bin3;
    }

    public List<Float> getBin4() {
        return bin4;
    }

    public void setBin1(List<Float> bin1) {
        this.bin1 = bin1;
    }

    public void setBin2(List<Float> bin2) {
        this.bin2 = bin2;
    }

    public void setBin3(List<Float> bin3) {
        this.bin3 = bin3;
    }

    public void setBin4(List<Float> bin4) {
        this.bin4 = bin4;
    }

    public float calculateFitness() {
        // Calculates contribution of bin 1
        float bin1Score = 1;
        for (float num : bin1) {
            bin1Score *= num;
        }
        // Calculates contribution of bin 2
        float bin2Score = 0;
        for (float num : bin2) {
            bin2Score += num;
        }
        // Calculates contribution of bin 3
        float max = -10;
        float min = 10;
        for (float num : bin3) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        float bin3Score = max - min;
        // Sums the scores of the bins
        fitness = bin1Score + bin2Score + bin3Score;
        return fitness;
    }

    public List<Float> getBin(int binNumber) {
        if (binNumber == 1) {
            return bin1;
        } else if (binNumber == 2) {
            return bin2;
        } else if (binNumber == 3) {
            return bin3;
        } else {
            return bin4;
        }
    }

    public void mutation() {
        Random random = new Random();
        int index = random.nextInt(10);
        // Grabs two random bins
        int randomBin1 = random.nextInt(4) + 1;
        int randomBin2 = random.nextInt(4) + 1;
        // Grabs two random positions within the bins
        float bin1Value = getBin(randomBin1).get(index);
        float bin2Value = getBin(randomBin2).get(index);
        // Swaps the corresponding values
        getBin(randomBin2).set(index, bin1Value);
        getBin(randomBin1).set(index, bin2Value);
    }
}
