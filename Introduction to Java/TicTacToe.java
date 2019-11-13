/*
 * Plays Tic-Tac-Toe
 * CSC 1350 Lab #9
 * @author Phillip Kerr
 * @since November 15, 2017
 */
package tictactoe;
import java.util.Scanner;


public class TicTacToe {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int ROWS = 5;
        final int COLUMNS = 5;
        int count = 0;
        boolean over = false;
        String[][] board = new String[ROWS][COLUMNS];
        for (int i=0; i < ROWS; i++)
        {
            for (int j=0; j < COLUMNS; j++)
            {
                if (i%2 == 0)
                {
                    if (j%2 == 0)
                    {
                    board[i][j] = " ";
                    }
                    else
                    {
                    board[i][j] = "|";
                    }
                }
                else
                {
                 board[i][j] = "-";
                }
            }
        }
         for (int i=0; i < ROWS; i++)
        {
            for (int j=0; j < COLUMNS; j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
         System.out.print("Player 1, enter your name->");
         String player1 = in.next();
         System.out.print("Player 1, enter your symbol->");
         String symbol = in.next();
         System.out.print("Player 2, enter your name->");
         String player2 = in.next();
         
            while (!over)
            {
                if (symbol.equals("X"))
                        {
                    if (count%2==0)
                    {
                System.out.print(player1 + ", please enter a position for your X->");
                int pos1 = in.nextInt();
                int pos2 = in.nextInt();
                if (board[pos1][pos2] == "|" || board[pos1][pos2] == "-" || board[pos1][pos2] == "X" || board[pos1][pos2] == "O")
                {
                 System.out.println("Error: not a valid position.");
                }
                else 
                {
                    board[pos1][pos2] = "X";
                    count++;
                }
                         for (int i=0; i < ROWS; i++)
        {
            for (int j=0; j < COLUMNS; j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
                         if(CheckWin(board, pos1, pos2))
                                 {
                                     System.out.println(player1 + " wins!");
                                     over = true;
                                 }
                    }
                    else if (count%2!=0&&count!= 9)
                {
                System.out.print(player2 + ", please enter a position for your O->");
                int pos1 = in.nextInt();
                int pos2 = in.nextInt();
                if (board[pos1][pos2] == "|" || board[pos1][pos2] == "-" || board[pos1][pos2] == "X" || board[pos1][pos2] == "O")
                {
                 System.out.println("Error: not a valid position.");
                }
                else 
                {
                    board[pos1][pos2] = "O";
                    count++;
                }         
                for (int i=0; i < ROWS; i++)
        {
            for (int j=0; j < COLUMNS; j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
                if (CheckWin(board, pos1, pos2))
                {
                   System.out.println(player2 + " wins!");
                   over = true; 
                }
                        }
                    else
                    {
                        System.out.println("Tie!");
                        over = true;
                    }
            }
                else if (symbol.equals("O"))
                        {
                    if (count%2==0)
                    {
                System.out.print(player1 + ", please enter a position for your O->");
                int pos1 = in.nextInt();
                int pos2 = in.nextInt();
                if (board[pos1][pos2] == "|" || board[pos1][pos2] == "-" || board[pos1][pos2] == "X" || board[pos1][pos2] == "O")
                {
                 System.out.println("Error: not a valid position.");
                }
                else 
                {
                    board[pos1][pos2] = "O";
                    count++;
                }
                         for (int i=0; i < ROWS; i++)
        {
            for (int j=0; j < COLUMNS; j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
                         if(CheckWin(board, pos1, pos2))
                                 {
                                     System.out.println(player1 + " wins!");
                                     over = true;
                                 }
                    }
                    else if (count%2!=0&&count!= 9)
                {
                System.out.print(player2 + ", please enter a position for your X->");
                int pos1 = in.nextInt();
                int pos2 = in.nextInt();
                if (board[pos1][pos2] == "|" || board[pos1][pos2] == "-" || board[pos1][pos2] == "X" || board[pos1][pos2] == "O")
                {
                 System.out.println("Error: not a valid position.");
                }
                else if (pos1 < 0 || pos1 > 4 || pos2 < 0 || pos2 > 4)
                {
                    System.out.println("Error: out of bounds of the board.");
                }
                else 
                {
                    board[pos1][pos2] = "X";
                    count++;
                }         
                for (int i=0; i < ROWS; i++)
        {
            for (int j=0; j < COLUMNS; j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
                if (CheckWin(board, pos1, pos2))
                {
                   System.out.println(player2 + " wins!");
                   over = true; 
                }
                        }
                    else
                    {
                        System.out.println("Tie!");
                        over = true;
                    }
            }
                else
                {
                 System.out.println("Error! Must type in X or O!");
                 over = true;
                }
            }
    }
  public static boolean CheckWin(String[][]board, int ROWS, int COLUMNS)
    {
        if (ROWS==0&&COLUMNS==0)
        {
          if (board[0][0].equals(board[2][0])&& board[0][0].equals(board[4][0]))
          {
              return true;
           }
          else if (board[0][0].equals(board[0][2])&&board[0][0].equals(board[0][4]))
          {
              return true;
          }
          else if (board[0][0].equals(board[2][2])&&board[0][0].equals(board[4][4]))
          {
              return true;
          }
        }
          else if (ROWS==0&&COLUMNS==2)
          {
              {
                  if (board[0][2].equals(board[0][0])&&board[0][2].equals(board[0][4]))
                  {
                      return true;
                  }
                  else if (board[0][2].equals(board[2][2])&&board[0][2].equals(board[4][2]))
                  {
                      return true;
                  }
              }
          }
          else if (ROWS==0&&COLUMNS==4)
          {
              if (board[0][4].equals(board[0][0])&&board[0][4].equals(board[0][2]))
              {
                  return true;
              }
              else if (board[0][4].equals(board[2][2])&&board[0][4].equals(board[4][0]))
              {
                  return true;
              }
              else if (board[0][4].equals(board[2][4])&&board[0][4].equals(board[4][4]))
              {
                  return true;
              }
          }
          else if (ROWS==2&&COLUMNS==0)
          {
              if (board[2][0].equals(board[2][2])&&board[2][0].equals(board[2][4]))
              {
                  return true;
              }
              else if (board[2][0].equals(board[0][0])&&board[2][0].equals(board[4][0]))
              {
                  return true;
              }
          }
          else if(ROWS==2&&COLUMNS==2)
          {
              if (board[2][2].equals(board[0][0])&&board[2][2].equals(board[4][4]))
              {
                  return true;
              }
              else if (board[2][2].equals(board[0][2])&&board[2][2].equals(board[4][2]))
              {
                  return true;
              }
              else if (board[2][2].equals(board[0][4])&&board[2][2].equals(board[4][0]))
              {
                  return true;
              }
              else if (board[2][2].equals(board[2][0])&&board[2][2].equals(board[2][4]))
              {
                  return true;
              }     
          }
          else if (ROWS==2&&COLUMNS==4)
          {
              if (board[2][4].equals(board[0][4])&&board[2][4].equals(board[4][4]))
              {
                  return true;
              }
              else if (board[2][4].equals(board[2][2])&&board[2][4].equals(board[2][0]))
              {
                  return true;
              }
          }
          else if (ROWS==4&&COLUMNS==0)
          {
              if (board[4][0].equals(board[0][0])&&board[4][0].equals(board[2][0]))
              {
                  return true;
              }
              else if (board[4][0].equals(board[4][2])&&board[4][0].equals(board[4][4]))
              {
                  return true;
              }
              else if (board[4][0].equals(board[2][2])&&board[4][0].equals(board[0][4]))
              {
                  return true;
              }
          }
          else if (ROWS==4&&COLUMNS==2)
          {
              if (board[4][2].equals(board[2][2])&&board[4][2].equals(board[0][2]))
                      {
                          return true;
                      }
              else if (board[4][2].equals(board[4][0])&&board[4][2].equals(board[4][4]))
              {
                  return true;
              }
          }
          else if (ROWS == 4&&COLUMNS==4)
          {
              if (board[4][4].equals(board[2][2])&&board[4][4].equals(board[0][0]))
              {
                  return true;
              }
              else if (board[4][4].equals(board[4][0])&&board[4][4].equals(board[4][2]))
              {
                  return true;
              }
              else if (board[4][4].equals(board[0][4])&&board[4][4].equals(board[2][4]))
              {
                  return true;
              }
          }
        return false;
    }
}


            
        

    
    

