import java.util.*;

public class A3Q2 {
    /*
    "排degree， degree高的Lego靠后"
    从degree lowest的module开始
    往前推
     */

    public static long[] num_pieces(long[] pieces, int[][] instructions) {
        int inDeg[] = new int[pieces.length];//index: lego号， element: lego degree
        Stack<Integer> legoPieces = new Stack<>();
//        int[] sourceLego = new int[pieces.length];
//        int[] quantity = new int[pieces.length];
        int[] sortedLego = new int[pieces.length];
        /*
        target Lego的前置Lego
        如果target是module 0, module 0 的 degree 变成1
        如果target是module 1, module 1 的 degree 变成1
        再次出现变成2...
        所以inDeg的index是Lego编号
        */
        for (int i = 0; i < instructions.length; i++){
            inDeg[instructions[i][1]]++;
        }

        //starts from the basic
        for (int i = 0; i < inDeg.length; i++){
            if(inDeg[i] == 0){
                //最底层Lego最先入队
                legoPieces.push(i);//element: lego号
            }
        }

        int index = 0;
        //int indexS = 0;
        while(!legoPieces.isEmpty()){
            int curLego = legoPieces.pop();
            //index 0: 最底层的Lego号
            sortedLego[index] = curLego;
            index++;
            for (int i = 0; i < instructions.length; i++){
                if(instructions[i][0] == curLego){
                    inDeg[instructions[i][1]]--;
                    if(inDeg[instructions[i][1]] == 0){
                        legoPieces.push(instructions[i][1]);
                    }
                }
            }
        }

        //sorted lego从低到高完成排序
        for (int i = sortedLego.length-1; i >= 0; i--){
            int curLego = sortedLego[i];
            for(int j = 0; j < instructions.length; j++) {
                if(curLego == instructions[j][1]){
                    int curLegoSource = instructions[j][0];
                    int curLegoSourceQ = instructions[j][2];
                    pieces[curLegoSource] += pieces[curLego]*curLegoSourceQ;
                }
            }

        }
        return pieces;
    }

}