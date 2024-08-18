package example.com.demo;
import java.util.Queue;
import java.util.LinkedList;
public class codingT {



        boolean isInRange(int r, int c, int rowLength, int colLength) {
            return (r >= 0 && r < rowLength) && (c >= 0 && c < colLength);
        }

        public int shortestPathBinaryMatrix(int[][] grid) {
            int shortestDist = -1;
            int rowLength = grid.length;
            int colLength = grid[0].length;

            if (grid[0][0] != 0 || grid[rowLength - 1][colLength - 1] != 0) {
                return shortestDist;
            }

            //✅ 이동 가능한 8가지 방향을 정의한다.
            int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

            //✅ 방문 여부를 저장할 이차원 리스트를 선언한다.
            boolean visited[][] = new boolean[rowLength][colLength];

            //✅ BFS를 진행할 큐를 선언한다.
            Queue<int[]> queue = new LinkedList<>();
            //✅ BFS의 시작 위치를 입력한다.
            queue.offer(new int[]{0, 0, 1});
            visited[0][0] = true;

            //✅ BFS를 진행한다.
            while (!queue.isEmpty()) {
                //✅ 현재 위치와 경로의 길이를 받는다.
                int[] curPos = queue.poll();
                int curRow = curPos[0];
                int curCol = curPos[1];
                int curLength = curPos[2];

                //✅ 현재 위치가 목적지일 경우 경로의 길이를 반환한다.
                if (curRow == rowLength - 1 && curCol == colLength - 1) {
                    shortestDist = curLength;
                    return shortestDist;
                }
                //✅ 유효한 8가지 방향으로 이동한 결과를 큐에 입력한다.
                for (int[] d : dir) {
                    int nextRow = curRow + d[0];
                    int nextCol = curCol + d[1];

                    if (isInRange(nextRow, nextCol, rowLength, colLength)) {
                        if (grid[nextRow][nextCol] == 0 && !(visited[nextRow][nextCol])) {
                            queue.offer(new int[]{nextRow, nextCol, curLength + 1});
                            visited[nextRow][nextCol] = true;
                        }
                    }
                }

            }
            //✅ 답을 반환한다.
            return shortestDist;
        }
    }

