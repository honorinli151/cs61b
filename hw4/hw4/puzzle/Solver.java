package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solver {
    private SearchNode initNode;
    private MinPQ<SearchNode> minPQ = new MinPQ<>();
    private int totMoves = 0;
    private List<WorldState> bestSolution;
    public Solver(WorldState initial) {
        this.initNode = new SearchNode(initial, 0, null);
        minPQ.insert(initNode);
        this.solve();
    }
    public int moves() {
        return totMoves;
    }
    public Iterable<WorldState> solution() {
        List<WorldState> ret = new ArrayList<>();
        for (int i = totMoves; i >= 0; i--) {
            ret.add(bestSolution.get(i));
        }
        return ret;
    }
    private void getAnswer(SearchNode goalNode) {
        totMoves = goalNode.moveCnt;
        bestSolution = new ArrayList<>();
        SearchNode p = goalNode;

        while (p!= null) {
            bestSolution.add(p.currentState);
            p = p.prevNode;
        }
    }
    public void solve() {
        while (true) {
            SearchNode curNode = minPQ.delMin();
            if (curNode.currentState.isGoal()) {
                this.getAnswer(curNode);
                return;
            }
            else {
                for (WorldState w : curNode.currentState.neighbors()) {
                    if (curNode.prevNode == null || !w.equals(curNode.prevNode.currentState)) {
                        minPQ.insert(new SearchNode(w, curNode.moveCnt + 1, curNode));
                    }
                }
            }

        }
    }

    private class SearchNode implements Comparable<SearchNode>{
        private int moveCnt;
        private WorldState currentState;
        private SearchNode prevNode;

        public SearchNode(WorldState ws, int mc, SearchNode pprev) {
            this.currentState = ws;
            this.moveCnt = mc;
            this.prevNode = pprev;
        }

        public WorldState getState() {
            return currentState;
        }

        public int getMoves() {
            return moveCnt;
        }

        public SearchNode getPrev() {
            return prevNode;
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.moveCnt + this.currentState.estimatedDistanceToGoal() - o.moveCnt + o.currentState.estimatedDistanceToGoal();
        }
    }
}
