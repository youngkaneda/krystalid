package org.capsules.the.crystal;

public interface CrystalStructureRule {

    String getChildValueForNeighborhood(String neighborhood);

    String[] getDeBruijnConnectedNodesTo(String node);

    int deBruijnSequenceSize();
}
