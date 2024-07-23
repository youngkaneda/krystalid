package org.capsules.the.crystal;

public interface CrystalStructureRule {

    public String getChildValueForNeighborhood(String neighborhood);

    public String[] getDeBruijnConnectedNodesTo(String node);
}
