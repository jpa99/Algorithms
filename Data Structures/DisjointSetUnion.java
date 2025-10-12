/**
 * Disjoint Set Union (Union-Find) with path compression and union by rank.
 *
 * <p>This data structure maintains a collection of disjoint sets over elements labeled
 * {@code 0..n-1}. It supports near-constant-time merging of sets and connectivity queries, which
 * makes it a staple for Kruskal's minimum spanning tree algorithm, offline connectivity problems,
 * and clustering applications.</p>
 */
public class DisjointSetUnion {

    private final int[] parent;
    private final int[] rank;
    private final int[] size;
    private int components;

    /**
     * Creates {@code n} singleton sets {0}, {1}, ..., {n-1}.
     */
    public DisjointSetUnion(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        this.parent = new int[n];
        this.rank = new int[n];
        this.size = new int[n];
        this.components = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Finds the representative for {@code x} using path compression.
     */
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    /**
     * Merges the sets containing {@code a} and {@code b}. Returns {@code true} if the sets were
     * previously disjoint.
     */
    public boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return false;
        }
        if (rank[rootA] < rank[rootB]) {
            int tmp = rootA;
            rootA = rootB;
            rootB = tmp;
        }
        parent[rootB] = rootA;
        size[rootA] += size[rootB];
        if (rank[rootA] == rank[rootB]) {
            rank[rootA]++;
        }
        components--;
        return true;
    }

    /**
     * Returns the size of the set containing {@code x}.
     */
    public int componentSize(int x) {
        return size[find(x)];
    }

    /**
     * Returns how many disjoint components remain.
     */
    public int components() {
        return components;
    }

    public static void main(String[] args) {
        DisjointSetUnion dsu = new DisjointSetUnion(10);
        dsu.union(1, 2);
        dsu.union(2, 3);
        dsu.union(4, 5);
        dsu.union(6, 7);
        dsu.union(7, 8);
        System.out.println("Components: " + dsu.components());
        System.out.println("1 and 3 connected? " + (dsu.find(1) == dsu.find(3)));
        System.out.println("1 and 8 connected? " + (dsu.find(1) == dsu.find(8)));
        dsu.union(3, 7);
        System.out.println("Components after union(3, 7): " + dsu.components());
        System.out.println("Size of component containing 7: " + dsu.componentSize(7));
    }
}
