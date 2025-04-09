package programmers;

import java.util.*;
import java.util.stream.Collectors;

public class Programmers퍼즐조각채우기 {
    class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(!(o instanceof Pos pos)) return false;
            return x == pos.x && y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static int[][] nextPos = new int[][]{{0,1},{1,0},{0,-1}, {-1, 0}}; // 인접 상하좌우 지점의 좌표차이

    public static int n;

    public int solution(int[][] game_board, int[][] table) {
        // 1. bfs로 블록 찾는 메소드 구현

        // 2. 정규화 메소드 구현

        // 3. 회전 구현

        // 4. 빈 공간에 퍼즐 조각 몇 개 넣을 수 있는지 확인

        n = game_board.length;

        List<List<Pos>> emptyAreas = findBlocks(game_board, 0);
        List<List<Pos>> puzzleBlocks = findBlocks(table, 1);

        boolean[] usedPuzzleIdxs = new boolean[puzzleBlocks.size()];

        int result = 0;

        for(List<Pos> emptyArea: emptyAreas) {
            for(int idx = 0; idx < puzzleBlocks.size(); ++idx) {
                if(!usedPuzzleIdxs[idx] && checkMatched(emptyArea, puzzleBlocks.get(idx))) {
                    usedPuzzleIdxs[idx] = true;
                    result += puzzleBlocks.get(idx).size();
                    break; // 이미 매칭되는 퍼즐을 찾았으므로 for-idx문 탈출
                }
            }
        }

        return result;
    }

    /*
        blocks: 주어진 게임보드/테이블
        value: 어떤 값을 기준으로 bfs하는지
    */
    private List<List<Pos>> findBlocks(int[][] blocks, int value) {
        Deque<Pos> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        List<List<Pos>> blocksList = new ArrayList<>();
        List<Pos> curBlocksList;


        for(boolean[] arr: visited) {
            Arrays.fill(arr, false);
        }

        for(int x = 0; x<n; ++x) {
            for(int y = 0; y<n; ++y) {
                if(!visited[x][y] && blocks[x][y] == value) {
                    visited[x][y] = true;
                    queue.clear();
                    queue.add(new Pos(x, y));
                    curBlocksList = new ArrayList<>();

                    while(!queue.isEmpty()) {
                        Pos curPos = queue.poll();
                        curBlocksList.add(new Pos(curPos.x, curPos.y));
                        int nx, ny;
                        for(int[] next: nextPos) {
                            nx = curPos.x + next[0];
                            ny = curPos.y + next[1];

                            if(checkValidPos(nx, ny) && !visited[nx][ny] && blocks[nx][ny] == value) {
                                visited[nx][ny] = true;
                                queue.add(new Pos(nx, ny));
                            }
                        }
                    }

                    blocksList.add(normalize(curBlocksList));
                }
            }
        }

        return blocksList;
    }

    private boolean checkValidPos(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    private List<Pos> normalize(List<Pos> blocks) {
        int minX = blocks.stream().mapToInt(x -> x.x).min().getAsInt();
        int minY = blocks.stream().mapToInt(x -> x.y).min().getAsInt();

        return blocks.stream().map(pos -> new Pos(pos.x - minX, pos.y - minY))
                .sorted((o1, o2) -> {
                    if (o1.x - o2.x == 0) return o1.y - o2.y;
                    else return o1.x - o2.x;
                }).collect(Collectors.toList());
    }

    private List<Pos> rotate(List<Pos> blocks) {
        // 시계방향 90도 회전 -> (x, y) -> (y, n - 1 - x)
        return normalize(blocks.stream()
                .map(pos -> new Pos(pos.y, n - 1 - pos.x))
                .collect(Collectors.toList()));
    }

    private boolean checkMatched(List<Pos> emptyArea, List<Pos> puzzleBlock) {
        List<Pos> targetPuzzle = puzzleBlock;
        int cnt = 0;
        while(cnt < 4) {
            if(emptyArea.equals(targetPuzzle)) {
                return true;
            } else {
                targetPuzzle = rotate(targetPuzzle);
                cnt++;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] game_board = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
        int[][] table = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};

        Programmers퍼즐조각채우기 solver = new Programmers퍼즐조각채우기();
        System.out.println(solver.solution(game_board, table));
    }
}
