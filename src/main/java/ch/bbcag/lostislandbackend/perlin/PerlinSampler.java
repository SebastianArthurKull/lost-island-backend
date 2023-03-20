package ch.bbcag.lostislandbackend.perlin;

// https://github.com/blackears/PerlinNoiseMaker/blob/master/noiseMaker.js
public class PerlinSampler {
    private final int width;
    private final int height;
    private final double seed;
    private final double[] gradients;

    PerlinSampler(int width, int height, long seed) {
        this.width = width;
        this.height = height;
        this.seed = seed;

        this.gradients = new double[width * height * 2];

        RandomGenerator generator = new RandomGenerator();
        generator.setSeed(seed);

        for (int i = 0; i < gradients.length; i += 2) {
            double x, y;

            double angle = generator.next() * Math.PI * 2;

            x = Math.sin(angle);
            y = Math.cos(angle);

            gradients[i] = x;
            gradients[i + 1] = y;
        }
    }

    private double dotProduct(double x, double y, double vx, double vy) {
        double offset = (x + y * width) * 2;
        double wx = gradients[(int) offset];
        double wy = gradients[(int) (offset + 1)];

        return wx * vx + wy * vy;
    }

    private double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }

    private double sCurve(double t) {
        return t * t * (3 - 2 * t);
    }

    double getValue(double x, double y) {
        double xCell = Math.floor(x);
        double yCell = Math.floor(y);

        double xFraction = x - xCell;
        double yFraction = y - yCell;

        double x1 = xCell == width - 1 ? 0 : xCell + 1;
        double y1 = yCell == height - 1 ? 0 : yCell + 1;

        double v00 = dotProduct(xCell, yCell, xFraction, yFraction);
        double v10 = dotProduct(x1, yCell, xFraction - 1, yFraction);
        double v01 = dotProduct(xCell, y1, xFraction, yFraction - 1);
        double v11 = dotProduct(x1, y1, xFraction - 1, yFraction - 1);

        double vx0 = lerp(v00, v10, sCurve(xFraction));
        double vx1 = lerp(v01, v11, sCurve(xFraction));


        return lerp(vx0, vx1, sCurve(yFraction));
    }
}
