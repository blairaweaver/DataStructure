//Deleted all code that pertains to the implementation of Adj List

//To Do:
//Add methods: depth traversal, breadth traversal, Kruskal's, Prim's, Dijkstra's

import java.util.LinkedList;

public class Assignment4 {

    private int[][] adjMatrix;
//    -1 for grey, 0 for red, 1 for black
    private int[] color;
    private int[] visit;
    private int verticies = 0;
    private int edges = 0;

    public Assignment4(int verticies) {
        this.adjMatrix = new int[verticies][verticies];
        this.color = new int[verticies];
        this.visit = new int[verticies];
    }

    public int numVertices() {
        return verticies;
    }

    private void setColor(int vert, int color) {
        this.color[vert] = color;
    }

    private void clearColor() {
        for (int i = 0; i < color.length; i++) {
            setColor(i, -1);
        }
    }

    private void clearVist() {
        for (int i = 0; i < visit.length; i++) {
            visit[i] = -1;
        }
    }

    private void visited(int vert) {
        int i = 0;
        while (visit[i] != -1) {
            i++;
        }
        visit[i] = vert;
    }

    public int[] getVisit() {
        return visit;
    }

    public int numEdges() {
        return edges;
    }

    public int outDegree(int vert) {
        vert--;
        int sum = 0;
        for (int i = 0; i < this.adjMatrix[vert].length; i++) {
            if (this.adjMatrix[vert][i] != -1) {
                sum += this.adjMatrix[vert][i];
            }
        }
        return sum;
    }

    public boolean areAdjacent(int vert1, int vert2) {
        return this.adjMatrix[--vert1][--vert2] == 1;
    }

    public void insertVertex() {
        int oldSize = adjMatrix[0].length;
        int[][] tempMatrix = new int[oldSize + 1][oldSize + 1];
        this.color = new int[oldSize + 1];
        this.visit = new int[oldSize + 1];

        for (int i = 0; i < adjMatrix.length; i++) {
            tempMatrix[i][i] = adjMatrix[i][i];

        }
        verticies++;
    }

    public void insertEdge(int vert1, int vert2, boolean d) {
        vert1--;
        vert2--;
        adjMatrix[vert1][vert2] = 1;
//        if the graph is directed, d = true, don't add
//        if the graph is undirected, d = false, add
        if (d == false) {
            adjMatrix[vert2][vert1] = 1;
        }
        edges++;
    }

    public void insertEdge(int vert1, int vert2, int weight) {
//        Insert function for weighted graph
        vert1--;
        vert2--;
        adjMatrix[vert1][vert2] = weight;
        adjMatrix[vert2][vert1] = weight;
        edges++;
    }

    public void removeEdge(int vert1, int vert2) {
        vert1--;
        vert2--;
        adjMatrix[vert1][vert2] = 0;
        adjMatrix[vert2][vert1] = 0;
        edges--;
    }

    public void removeVertex(int vert) {
        vert--;
        for (int i = 0; i < adjMatrix.length; i++) {
            adjMatrix[vert][i] = -1;
            adjMatrix[i][vert] = -1;
        }
        verticies--;
    }

    public void print(){
        for (int i = 0; i < adjMatrix.length; i++) {
            int vert1 = i + 1;
            System.out.print(vert1 + ": ");
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] == 1) {
                    int vert2 = j + 1;
                    System.out.print(vert2 + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printVisited() {
        for (int i = 0; i < visit.length; i++) {
            System.out.print(visit[i] + " ");
        }
        System.out.println();
    }

    public void DFS(int vert) {
        clearColor();
        clearVist();
        DFSvisit(vert);
    }

    private void DFSvisit(int vert) {
        setColor(vert-1, 0);
        visited(vert);
        for (int i = 0; i < this.adjMatrix[vert-1].length; i++) {
            if (areAdjacent(vert, i+1) && this.color[i] == -1) {
                DFSvisit(i+1);
            }
        }
        setColor(vert-1, 1);
    }

    public void BFS(int vert) {
        clearColor();
        clearVist();
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(vert);
        visited(vert);
        while (!queue.isEmpty()) {
            int current = queue.removeFirst();
            for (int i = 0; i < adjMatrix[current-1].length; i++) {
                if (areAdjacent(current, i+1) && color[i] == -1) {
                    setColor(i,0);
                    visited(i+1);
                    queue.add(i + 1);
                }
            }
            setColor(current-1, 1);
        }
    }




    public static void main(String[] args) {
//        Generating Adj Matrix for Question 1
        Assignment4 Q1 = new Assignment4(8);
        Q1.insertEdge(1,2,true);
        Q1.insertEdge(1,4,true);
        Q1.insertEdge(2,3,true);
        Q1.insertEdge(2,5,true);
        Q1.insertEdge(3,2,true);
        Q1.insertEdge(3,4,true);
        Q1.insertEdge(3,5,true);
        Q1.insertEdge(4,1,true);
        Q1.insertEdge(4,3,true);
        Q1.insertEdge(5,2,true);
        Q1.insertEdge(5,3,true);
        Q1.insertEdge(5,6,true);
        Q1.insertEdge(6,5,true);
        Q1.insertEdge(6,7,true);
        Q1.insertEdge(6,8,true);
        Q1.insertEdge(7,6,true);
        Q1.insertEdge(7,8,true);
        Q1.insertEdge(8,6,true);
        Q1.insertEdge(8,7,true);

        Q1.print();

        Q1.DFS(1);

        Q1.printVisited();

        Q1.BFS(1);

        Q1.printVisited();

//        Generating Adj matrix for Q2 where the value stored is the weight
        Assignment4 Q2 = new Assignment4(9);
        Q2.insertEdge(1,2,22);
        Q2.insertEdge(1,3,9);
        Q2.insertEdge(1,4,12);
        Q2.insertEdge(2,3,35);
        Q2.insertEdge(2,6,36);
        Q2.insertEdge(2,8,34);
        Q2.insertEdge(3,6,42);
        Q2.insertEdge(3,5,65);
        Q2.insertEdge(3,4,4);
        Q2.insertEdge(4,5,33);
        Q2.insertEdge(4,9,30);
        Q2.insertEdge(5,6,18);
        Q2.insertEdge(5,7,23);
        Q2.insertEdge(6,7,39);
        Q2.insertEdge(6,8,24);
        Q2.insertEdge(7,8,25);
        Q2.insertEdge(7,9,21);
        Q2.insertEdge(8,9,19);
    }
}
