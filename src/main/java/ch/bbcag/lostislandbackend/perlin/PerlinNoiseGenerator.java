package ch.bbcag.lostislandbackend.perlin;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

/**
 * Perlin noise generator ported from <a href="https://github.com/blackears/PerlinNoiseMaker/blob/master/noiseMaker.js">noiseMaker.js by blackears</a>
 */
public class PerlinNoiseGenerator {

    /**
     * Use this method to get a perlin noise double matrix. Width and height defines the size of the matrix.
     * For cellSize use a value that's a fraction of width and height. Generally use 2^n values except for lvl and randSeed.
     * As default best use width and height 256, randSeed = 100, cellSize = 16 and lvl = 1. Change values for different patterns and smoother or rougher structures.
     *
     * @param width    Defines the size of x-axis
     * @param height   Defines the size of y-axis
     * @param randSeed Seed for the random generator
     * @param cellSize Bigger cellSizes generate more but smaller structure, smaller cellSizes generate less but greater structure. Use fraction of width and height.
     * @param lvl      Defines how many times the noise function is used for each cell. Bigger lvl values generate smoother structures.
     * @return Matrix with random generated perlin noise.
     */
    public static String generateNoiseMatrix(int width, int height, int randSeed, double cellSize, int lvl) {
        double cellSizeInverse = 1 / cellSize;

        int[][] matrix = new int[height][width];

        for (int lvlIdx = 0; lvlIdx < lvl; ++lvlIdx) {
            PerlinSampler sampler = new PerlinSampler((int) Math.ceil(width * cellSizeInverse), (int) Math.ceil(height * cellSizeInverse), randSeed + lvlIdx);

            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    double z =sampler.getValue(j * cellSizeInverse, i * cellSizeInverse);
                    if (z < 1){
                        matrix[i][j] = 3;
                    }
                    if (z < 0.3){
                        matrix[i][j] = 2;
                    }
                    if (z < 0){
                        matrix[i][j] = 1;
                    }
                    if (z < -0.3){
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        String data = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            data = String.valueOf(mapper.readTree(Arrays.deepToString(matrix)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}