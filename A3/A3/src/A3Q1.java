import java.util.*;

public class A3Q1 {
    public static int find_exit(int time, String[][] board) {
        int rowLength = board.length;
        int colLength = board[0].length;
        int curRow = 0;
        int curCol = 0;
        int timeCheck = 0;
        int[] curResult;
        Queue<int[]> results = new LinkedList<>();

        //add start node into queue
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (board[i][j].equals("S")) {
                    curRow = i;
                    curCol = j;
                    results.add(new int[]{curRow, curCol, timeCheck});
                    break;
                }
            }
            if (!results.isEmpty()){
                break;
            }
        }

        while (!results.isEmpty()) {
            curResult = results.poll();
            curRow = curResult[0];
            curCol = curResult[1];
            timeCheck = curResult[2];
            if (timeCheck > time || timeCheck >= 200) {
                return -1;
            }
            if ((curRow == 0 || curRow == rowLength - 1 || curCol == 0 || curCol == colLength - 1)) {
                return curResult[2];
            }
            //R, D, L, U
            for (int d = 0; d < 4; d++) {
                switch (d) {
                    case 0:
                        if (board[curRow][curCol + 1].equals("0") || board[curRow ][curCol+ 1].equals("L")) {
                            board[curRow][curCol+1] = "Visited";
                            results.add(new int[]{curRow , curCol+ 1, timeCheck + 1});
                        }
                        continue;
                    case 1:
                        if (board[curRow+ 1][curCol ].equals("0") || board[curRow+ 1][curCol ].equals("U")) {
                            board[curRow+1][curCol] = "Visited";
                            results.add(new int[]{curRow+ 1, curCol , timeCheck + 1});
                        }
                        continue;
                    case 2:
                        if (board[curRow ][curCol- 1].equals("0") || board[curRow ][curCol- 1].equals("R")) {
                            board[curRow][curCol-1] = "Visited";
                            results.add(new int[]{curRow , curCol- 1, timeCheck + 1});
                        }
                        continue;
                    case 3:
                        if (board[curRow- 1][curCol ].equals("0") || board[curRow- 1][curCol ].equals("D")) {
                            board[curRow-1][curCol] = "Visited";
                            results.add(new int[]{curRow- 1, curCol , timeCheck + 1});
                        }
                        continue;
                    default:
                        break;
                }
                timeCheck += 1;
                System.out.println("here " + timeCheck);
            }
        }
        return -1;
    }
}
