import java.util.*;

public class A2_Q1{
  public static int game_recursion(int[][] board) {
    return find_max_score(board,  0, 0);
  }

  public static int find_max_score(int[][] board, int current_score, int turn) {
    final int N = board.length;
    int max = Integer.MIN_VALUE;
    if (turn % 2 != 0) {
      max = Integer.MAX_VALUE;
    }

    boolean visited = false;
    for (int x = 0; x < N; x++) {
      for (int y = 0; y <= x; y++) {
        if (board[x][y] != 0) {
          continue;
        }

        for (int d = 0; d < 6; d++) {
          int[][] state = null;
          int val = -1;

          /* clockwise direction, 1 & 5 not exists
          * remove un-necessary case*/
          switch (d) {
            case 0:
              if (x >= 2 && board[x-1][y] > 0 && board[x-2][y] > 0) {
                val = board[x-1][y] * board[x-2][y];

                state = clone_board(board, N);
                state[x][y] = state[x-2][y];
                state[x-1][y] = state[x-2][y] = 0;
              }
              break;
            case 1:
              if (y < N - 2 && board[x][y+1] > 0 && board[x][y+2] > 0) {
                val = board[x][y+1] * board[x][y+2];

                state = clone_board(board, N);
                state[x][y] = state[x][y+2];
                state[x][y+1] = state[x][y+2] = 0;
              }
              break;
            case 2:
              if (x < N - 2 && y < N - 2 && board[x+1][y+1] > 0 && board[x+2][y+2] > 0) {
                val = board[x+1][y+1] * board[x+2][y+2];

                state = clone_board(board, N);
                state[x][y] = state[x+2][y+2];
                state[x+1][y+1] = state[x+2][y+2] = 0;
              }
              break;
            case 3:
              if (x < N - 2 && board[x+1][y] > 0 && board[x+2][y] > 0) {
                val = board[x+1][y] * board[x+2][y];

                state = clone_board(board, N);
                state[x][y] = state[x+2][y];
                state[x+1][y] = state[x+2][y] = 0;
              }
              break;
            case 4:
              if (y >= 2 && board[x][y-1] > 0 && board[x][y-2] > 0) {
                val = board[x][y-1] * board[x][y-2];

                state = clone_board(board, N);
                state[x][y] = state[x][y-2];
                state[x][y-1] = state[x][y-2] = 0;
              }
              break;
            case 5:
              if (x >= 2 && y >= 2 && board[x-1][y-1] >0 && board[x-2][y-2] > 0) {
                val =  board[x-1][y-1] * board[x-2][y-2];

                state = clone_board(board, N);
                state[x][y] = state[x-2][y-2];
                state[x-1][y-1] = state[x-2][y-2] = 0;
              }
              break;
            default:
              break;
          }

          if (state == null) {
            continue;
          }

          visited = true;
          if (turn % 2 == 0) {
            int next_score = find_max_score(state, current_score + val, turn +1);
            if (next_score > max) {
              max = next_score;
            }
          } else {
            int next_score = find_max_score(state, current_score - val, turn + 1);
            if (next_score < max) {
              max = next_score;
            }
          }
        }
      }
    }

    return visited ? max : current_score;
  }

  private static int[][] clone_board(int[][] board, int N) {
    int[][] state = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        state[i][j] = board[i][j];
      }
    }
    return state;
  }

  public static void main(String[] args) {
    int[][] board = {{56,-1,-1,-1,-1}, {12,56,-1,-1,-1},{56,16,51,-1,-1},{34,56,56,0,-1},{56,22,56,43,56}};
    game_recursion(board);
  }


}


