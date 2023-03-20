package ch.bbcag.lostislandbackend.perlin;

// https://github.com/blackears/PerlinNoiseMaker/blob/master/noiseMaker.js
public class RandomGenerator {
    private final long m = 2147483647;
    private long seed = 1;

    void setSeed(long seed) {
        if (seed <= 0) {
            seed = -(seed % (m - 1)) + 1;
        }
        if (seed > m - 1) {
            seed = m - 1;
        }
        this.seed = seed;
    }

    private long nextLong() {
        long a = 16807;
        long q = 127773;
        long r = 2836;

        long result = (long) (a * (seed % q) - r * Math.floor(((double) seed) / q));

        if (result <= 0) {
            result += m;
        }
        seed = result;
        return result;
    }

    double next() {
        double result = nextLong();
        return result / m;
    }


}
