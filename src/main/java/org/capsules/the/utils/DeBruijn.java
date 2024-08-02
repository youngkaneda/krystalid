package org.capsules.the.utils;

import org.capsules.the.crystal.CrystalStructureRule;

import java.util.Arrays;

public class DeBruijn {

    private CrystalStructureRule structureRule;

    public DeBruijn(CrystalStructureRule structureRule) {
        this.structureRule = structureRule;
    }

    public boolean checkSequence(String previous, String current) {
        return Arrays.asList(structureRule.getDeBruijnConnectedNodesTo(current)).contains(previous);
    }

    public boolean checkLattice(String lattice) {
        if (lattice.length() == 0) {
            return false;
        }
        for (int i = structureRule.deBruijnSequenceSize(); i < lattice.length(); i++) {
            int p = i - structureRule.deBruijnSequenceSize();
            if (!checkSequence(lattice.substring(p, p + structureRule.deBruijnSequenceSize()), lattice.substring(p + 1, p + 1 + structureRule.deBruijnSequenceSize()))) {
                return false;
            }
        }
        return true;
    }
}
