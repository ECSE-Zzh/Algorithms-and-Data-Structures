import java.util.*;

public class Open_Addressing {
    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public int[] Table;

    protected Open_Addressing(int w, int seed, int A) {

        this.w = w;
        this.r = (int) (w-1)/2 +1;
        this.m = power2(r);
        if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
        }
        else{
            this.A = A;
        }
        this.Table = new int[m];
        for (int i =0; i<m; i++) {
            Table[i] = -1;
        }

    }

    /** Calculate 2^w*/
    public static int power2(int w) {
        return (int) Math.pow(2, w);
    }
    public static int generateRandom(int min, int max, int seed) {
        Random generator = new Random();
        if(seed>=0){
            generator.setSeed(seed);
        }
        int i = generator.nextInt(max-min-1);
        return i+min+1;
    }
    /**Implements the hash function g(k)*/
    //only find next available slot
    public int probe(int key, int i) {
        //TODO: implement this function and change the return statement.
        int h_k = ((A*key) % power2(w)) >> (w-r);
        int g_k = (h_k+(i)) % power2(r);//Find next available slot
        return g_k;
    }


    /**Inserts key k into hash table. Returns the number of collisions encountered*/
    public int insertKey(int key){
        //TODO : implement this and change the return statement.
        int collision = 0;
        for (int i = 0; i<Table.length; i++){
           int slot = probe(key,i);
           if (Table[slot] == -1 || Table[slot] == -2){
               Table[slot]=key;
               return collision;
           } else {
               collision++;
           }
       }
        return collision;
    }

    /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
    public int insertKeyArray (int[] keyArray){
        int collision = 0;
        for (int key: keyArray) {
            collision += insertKey(key);
        }
        return collision;
    }

    /**Inserts key k into hash table. Returns the number of collisions encountered*/
    public int removeKey(int key){
        //TODO: implement this and change the return statement
        int collision = 0;
        for (int i = 0; i < Table.length; i++){
            int slot = probe(key, i);
            if (Table[slot]==-1){
                collision += 1;
                return collision;
            }
            if (Table[slot] != key){
                collision += 1;
            } else {
                Table[slot]=-2;//previously filled, still have chance to have key in hash. keep finding
                return collision;
            }

        }
       return collision;
    }

    public static void main (String args[]){
        Open_Addressing test = new Open_Addressing(10,0,-1);
        int a = test.insertKey(69);
        int b = test.insertKey(89);
        int c= test.insertKey(109);
        int d = test.insertKey(129);
        int e = test.removeKey(109);
    }

}




