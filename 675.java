/*
    BFS + PriorityQueue :
        DFS cannot work, because find minimum distance
*/
public static int cutOffTree(List<List<Integer>> forest) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)-> a[0] - b[0]); 
    for (int i = 0; i < forest.size(); i++) {
        for (int j = 0; j < forest.get(0).size(); j++) {
            if (forest.get(i).get(j) > 1) pq.add(new int[]{forest.get(i).get(j), i, j});
        }
    }
    
    int step = 0;
    int[] cur = {0, 0};
    
    while (pq.size() > 0) {
        int[] tmp = pq.poll();
        int[] des = new int[] {tmp[1], tmp[2]};
        //corner case : because return step when adding to que(in bfs), cannot find step if start and end are equal, need check 
        if (cur[0] == des[0] && cur[1] == des[1]) continue;
        int add = bfs(forest, cur, des);
        if (add == -1) return -1;
        step += add;
        cur = des;
    }
    return step;
}

static int[][] path = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
public static int bfs(List<List<Integer>> forest, int[] start, int[] end) {
    Queue<int[]> que = new LinkedList<>();
    boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
    int m = forest.size(), n = forest.get(0).size();
    int step = 0;
    que.offer(start);
    visited[start[0]][start[1]] = true;
    
    while (!que.isEmpty()) {
        int size = que.size();
        for (int j = 0; j < size; j++) {
            int[] tmp = que.poll();
            for (int i = 0; i < path.length; i++) {
                int lr = path[i][0];
                int ud = path[i][1];
                int x = tmp[0] + lr, y = tmp[1] + ud;
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || forest.get(x).get(y) == 0) {
                    continue;
                }
                if (x == end[0] && y == end[1]) {
                    return step + 1;
                }
                visited[x][y] = true;
                que.offer(new int[] {x, y});
            }
        }
        step++;
    }
    return -1;
}