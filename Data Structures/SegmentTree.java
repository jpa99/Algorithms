import java.util.Arrays;

/**
 * Segment tree for range sum and minimum queries with point updates.
 *
 * <p>This implementation exposes both range sum and range minimum queries to demonstrate how a
 * single tree can maintain multiple aggregate statistics. It is designed for educational purposes
 * to showcase how a tree decomposes an array into power-of-two intervals for logarithmic query and
 * update times.</p>
 */
public class SegmentTree {

    private final int size;
    private final long[] sumTree;
    private final long[] minTree;

    /**
     * Builds the tree using the provided input array.
     *
     * @param values the array whose values should be indexed. The array is copied so that updates do
     *     not mutate the caller's data.
     */
    public SegmentTree(long[] values) {
        this.size = values.length;
        int treeSize = 1;
        while (treeSize < size) {
            treeSize <<= 1;
        }
        treeSize <<= 1; // allocate enough space for the full binary tree
        this.sumTree = new long[treeSize];
        this.minTree = new long[treeSize];
        build(1, 0, size - 1, Arrays.copyOf(values, values.length));
    }

    private void build(int node, int left, int right, long[] values) {
        if (left == right) {
            sumTree[node] = values[left];
            minTree[node] = values[left];
            return;
        }
        int mid = left + (right - left) / 2;
        build(node * 2, left, mid, values);
        build(node * 2 + 1, mid + 1, right, values);
        pull(node);
    }

    /**
     * Performs a range sum query on the inclusive interval [queryLeft, queryRight].
     */
    public long rangeSum(int queryLeft, int queryRight) {
        validateRange(queryLeft, queryRight);
        return rangeSum(1, 0, size - 1, queryLeft, queryRight);
    }

    private long rangeSum(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryLeft > right || queryRight < left) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return sumTree[node];
        }
        int mid = left + (right - left) / 2;
        return rangeSum(node * 2, left, mid, queryLeft, queryRight)
                + rangeSum(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
    }

    /**
     * Performs a range minimum query on the inclusive interval [queryLeft, queryRight].
     */
    public long rangeMin(int queryLeft, int queryRight) {
        validateRange(queryLeft, queryRight);
        return rangeMin(1, 0, size - 1, queryLeft, queryRight);
    }

    private long rangeMin(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryLeft > right || queryRight < left) {
            return Long.MAX_VALUE;
        }
        if (queryLeft <= left && right <= queryRight) {
            return minTree[node];
        }
        int mid = left + (right - left) / 2;
        return Math.min(
                rangeMin(node * 2, left, mid, queryLeft, queryRight),
                rangeMin(node * 2 + 1, mid + 1, right, queryLeft, queryRight));
    }

    /**
     * Updates the value at {@code index} to {@code newValue}.
     */
    public void update(int index, long newValue) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is outside the tree");
        }
        update(1, 0, size - 1, index, newValue);
    }

    private void update(int node, int left, int right, int index, long newValue) {
        if (left == right) {
            sumTree[node] = newValue;
            minTree[node] = newValue;
            return;
        }
        int mid = left + (right - left) / 2;
        if (index <= mid) {
            update(node * 2, left, mid, index, newValue);
        } else {
            update(node * 2 + 1, mid + 1, right, index, newValue);
        }
        pull(node);
    }

    private void pull(int node) {
        sumTree[node] = sumTree[node * 2] + sumTree[node * 2 + 1];
        minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
    }

    private void validateRange(int left, int right) {
        if (left < 0 || right >= size || left > right) {
            throw new IllegalArgumentException(
                    "Invalid query range [" + left + ", " + right + "] for size " + size);
        }
    }

    public static void main(String[] args) {
        long[] values = {5, 2, 9, -4, 7, 6, 3, 8};
        SegmentTree tree = new SegmentTree(values);
        System.out.println("Initial sum [0, 7]: " + tree.rangeSum(0, 7));
        System.out.println("Initial min [2, 5]: " + tree.rangeMin(2, 5));

        tree.update(3, 10);
        System.out.println("After updating index 3 to 10");
        System.out.println("Sum [0, 7]: " + tree.rangeSum(0, 7));
        System.out.println("Min [2, 5]: " + tree.rangeMin(2, 5));
        System.out.println("Min [3, 3]: " + tree.rangeMin(3, 3));
    }
}
