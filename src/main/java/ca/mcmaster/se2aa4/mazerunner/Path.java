package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private final List<Character> path = new ArrayList<>();

    public Path() {     //initialize empty path  
    }

    public Path(String pathStr) {       //initialize path from path string
        String expanded = expandFactorizedStringPath(pathStr);
        for (Character c : expanded.toCharArray()) {
            if (c != ' ') {
                if (c != 'F' && c != 'L' && c != 'R') {
                    throw new IllegalArgumentException("Instruction '" + c + "' is invalid. Must be 'F', 'L', or 'R'.");
                }
                addStep(c);
            }
        }
    }

    public String expandFactorizedStringPath(String path) {
        StringBuilder expanded = new StringBuilder();

        for (int i = 0; i < path.length(); i++) {
            if (!Character.isDigit(path.charAt(i))) {
                expanded.append(path.charAt(i));
            } else {
                int count = 0;
                int digit = 0;
                do {
                    count *= (int) Math.pow(10, digit++);
                    count += Character.getNumericValue(path.charAt(i++));
                } while (Character.isDigit(path.charAt(i)));

                String step = String.valueOf(path.charAt(i)).repeat(count);
                expanded.append(step);
            }
        }

        return expanded.toString();
    }

    public List<Character> getPathSteps() {
        return new ArrayList<>(this.path);
    }

    public int getPathSize (Path path) {
        return this.path.size();
    }

    public void addStep(Character step) {
        path.add(step);
    }

    public String getCanonicalForm() {
        StringBuilder sb = new StringBuilder();

        for (Character c : path) {
            if (sb.isEmpty() || sb.charAt(sb.length() - 1) == c) {
                sb.append(c);
            } else {
                sb.append(' ');
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public String getFactorizedForm() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < path.size(); i++) {
            Character current = path.get(i);
            int count = 0;
            while (i < path.size() && current == path.get(i)) {
                count++;
                i++;
            }

            if (count == 1) {
                sb.append(current);
            } else {
                sb.append(count);
                sb.append(current);
            }

            if (i != path.size()) {
                sb.append(' ');
            }
            i--;
        }

        return sb.toString();
    }
}
