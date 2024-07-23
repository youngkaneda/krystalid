package org.capsules.the.crystal;

import java.util.HashMap;
import java.util.Map;

// uranyl selenates, sulfates, and chromates rules.
public class UranylCrystalStructureRule implements CrystalStructureRule {

    private Map<String, String> cellularAutomataRules = new HashMap<>();
    private Map<String, String[]> deBruijnGraph = new HashMap<>();

    public UranylCrystalStructureRule() {
        String[] keys = {
            "000", "001", "002", "010", "011", "012", "020", "021", "022", "100", "101", "102", "110", "111", "112", "120", "121", "122", "200", "201", "202", "210", "211", "212", "220", "221", "222"
        };
        String[] values = {
            "0", "1", "0", "2", "0", "2", "1", "1", "0", "1", "1", "1", "0", "0", "0", "1", "1", "0", "1", "1", "1", "0", "1", "0", "0", "0", "0"
        };
        for (int i = 0; i < keys.length; i++) {
            cellularAutomataRules.put(keys[i], values[i]);
        }
        deBruijnGraph.put("00", new String[] {"00", "01", "02"});
        deBruijnGraph.put("01", new String[] {"10", "12"});
        deBruijnGraph.put("10", new String[] {"00", "01", "02"});
        deBruijnGraph.put("12", new String[] {"20", "21"});
        deBruijnGraph.put("02", new String[] {"20", "21"});
        deBruijnGraph.put("20", new String[] {"02"});
        deBruijnGraph.put("21", new String[] {"10", "12"});
    }

    @Override
    public String getChildValueForNeighborhood(String neighborhood) {
        return cellularAutomataRules.get(neighborhood);
    }

    @Override
    public String[] getDeBruijnConnectedNodesTo(String node) {
        return deBruijnGraph.get(node);
    }
}
