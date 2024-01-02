
import java.util.*;

public class A1_Q3 {

    public static int elements(int[] sizes) {

        HashSet<Integer> window = new HashSet<>();
        int pointer_l = 0; //左指针
        int pointer_r = 0; //右指针
        int cur_length = 0;
        int max_length = 0;

        while (pointer_r < sizes.length){
            if (!window.contains(sizes[pointer_r])){
                window.add(sizes[pointer_r]);
                cur_length = window.size();
                if (cur_length > max_length){
                    max_length = cur_length;
                }
                pointer_r++;
            } else {
                window.remove(sizes[pointer_l]);
                cur_length = window.size();
                pointer_l++;
            }
        }
        return max_length;
    }

    public static void main(String[] args) {
        int[] test = {1,1,3,1,4};

        int result = elements(test);
        System.out.println(result);
    }

}

