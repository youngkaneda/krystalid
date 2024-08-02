package org.capsules.the.automata;

import org.capsules.the.crystal.CrystalStructureRule;
import org.capsules.the.utils.DeBruijn;
import java.util.List;

public class CellularAutomata {

    private int latticeSize;
    private List<String> colors;
    private int[][] grid;
    private CrystalStructureRule structureRule;
    private boolean replaceDefaultBlankColor;

    public CellularAutomata(int latticeSize, List<String> colors, CrystalStructureRule structureRule, boolean replaceDefaultBlankColor) {
        this.latticeSize = latticeSize;
        this.colors = colors;
        this.structureRule = structureRule;
        this.replaceDefaultBlankColor = replaceDefaultBlankColor;
    }

    private String evolvePattern(String pattern) {
        StringBuilder nextPattern = new StringBuilder();
        int length = pattern.length();
        for (int i = 0; i < length; i++) {
            int prev = getPreviousIndex(length, i);
            int next = getNextIndex(length, i);
            nextPattern.append(structureRule.getChildValueForNeighborhood(pattern.charAt(prev) + pattern.substring(i, i + 1) + pattern.charAt(next)));
        }
        return nextPattern.toString();
    }

    public void generate(String pattern) throws RuntimeException {
        if (!pattern.matches("^[0-9]*$")) {
            throw new RuntimeException("The pattern given as input can only contain numbers.");
        }
        DeBruijn deBruijn = new DeBruijn(this.structureRule);
        StringBuilder lattice = new StringBuilder();
        this.grid = new int[this.latticeSize][this.latticeSize];
        for (int i = 0; i < this.latticeSize; i++) {
            while (lattice.length() < this.latticeSize) {
                lattice.append(pattern);
            }
            lattice.setLength(latticeSize);
            if (deBruijn.checkLattice(lattice.toString())) {
                for (int j = 0; j < lattice.length(); j++) {
                    grid[i][j] = Integer.parseInt(lattice.substring(j, j + 1));
                }
            } else {
                throw new RuntimeException("Sorry, an invalid lattice with this pattern was created: " + lattice);
            }
            lattice.setLength(0);
            pattern = evolvePattern(pattern);
        }
    }

    public void paintGrid() {
    }

    private int getPreviousIndex(int length, int index) {
        if (index == 0) {
            return length - 1;
        }
        return length == 1 ? 0 : ((index - 1) % length);
    }

    private int getNextIndex(int length, int index) {
        if (index >= length) {
            return 0;
        }
        return length == 1 ? 0 : (index + 1) % length;
    }
}
