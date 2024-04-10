package ca.mcmaster.se2aa4.mazerunner;

import java.util.*;

public class BFSTraversal implements GraphTraversal {

    @Override
    public Path traverse(Graph graph, Node startNode, Node endNode) {
    
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> cameFrom = new HashMap<>();
        queue.add(startNode);
        cameFrom.put(startNode, null);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (currentNode.equals(endNode)) {
                return reconstructPath(cameFrom, startNode, endNode);
            }

            for (Node neighbor : graph.getNeighbors(currentNode)) {
                if (!cameFrom.containsKey(neighbor)) {
                    queue.add(neighbor);
                    cameFrom.put(neighbor, currentNode);
                }
            }
        }

        return new Path(); 
    }

    private Path reconstructPath(Map<Node, Node> cameFrom, Node start, Node end) {
        LinkedList<Position> pathPositions = new LinkedList<>();
        for (Node at = end; at != null; at = cameFrom.get(at)) {
            pathPositions.addFirst(at.getPosition());
        }

        return convertToPath(pathPositions);
    }

    private Path convertToPath(LinkedList<Position> positions) {
        Path path = new Path();

        Direction currentDirection = Direction.RIGHT; //initial direction right?
        Position previous = positions.poll(); 
    
        for (Position current : positions) {
            Direction requiredDirection = getRequiredDirection(previous, current);
    
            while (currentDirection != requiredDirection) {
                int turnsRight = turnsRequired(currentDirection, requiredDirection, true);
                int turnsLeft = turnsRequired(currentDirection, requiredDirection, false);
    
                if (turnsRight <= turnsLeft) {
                    path.addStep('R');
                    currentDirection = currentDirection.turnRight();
                } else {
                    path.addStep('L');
                    currentDirection = currentDirection.turnLeft();
                }
            }
            path.addStep('F');
            previous = current;
        }
    
        return path;
    }
    
    private int turnsRequired(Direction currentDirection, Direction targetDirection, boolean calculateRightTurns) {
        int turns = 0;
        Direction current = currentDirection;
        while (current != targetDirection) {
            if (calculateRightTurns) {
                current = current.turnRight();
            } else {
                current = current.turnLeft();
            }
            turns++;
        }
        return turns;
    }
    
    private Direction getRequiredDirection(Position from, Position to) {
        if (from.x() < to.x()) return Direction.RIGHT;
        if (from.x() > to.x()) return Direction.LEFT;
        if (from.y() < to.y()) return Direction.DOWN; 
        if (from.y() > to.y()) return Direction.UP;
        throw new IllegalStateException("cannot determine direction from " + from + " to " + to);
    }
    
}