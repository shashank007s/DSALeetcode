import java.util.*;

class Solution {

    static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class Node {
        int v;
        long dist;

        Node(int v, long dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        int maxCost = 0;
        for (int[] e : edges) {
            graph[e[0]].add(new Edge(e[1], e[2]));
            maxCost = Math.max(maxCost, e[2]);
        }

        int low = 0, high = maxCost;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canReach(mid, graph, online, k)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean canReach(int minEdge, ArrayList<Edge>[] graph,
                             boolean[] online, long k) {

        int n = graph.length;
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));

        dist[0] = 0;
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.dist != dist[cur.v]) continue;
            if (cur.dist > k) continue;

            if (cur.v == n - 1) return true;

            for (Edge e : graph[cur.v]) {
                if (e.cost < minEdge) continue;

                int nxt = e.to;

                // Intermediate nodes must be online.
                if (nxt != n - 1 && !online[nxt]) continue;

                long nd = cur.dist + e.cost;
                if (nd < dist[nxt] && nd <= k) {
                    dist[nxt] = nd;
                    pq.offer(new Node(nxt, nd));
                }
            }
        }

        return false;
    }
}