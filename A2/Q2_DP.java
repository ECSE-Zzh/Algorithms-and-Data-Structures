public class Q2_DP {
    public static int[] dynamicProcess (int[] denominations, int amount){
        int[] DpResultSet = new int[amount+1];
        DpResultSet[0] = 0;

        int dp[][] = new int[denominations.length][amount+1];//start from zero
        for (int i = 0; i < denominations.length; i++){
            //first line
            dp[i][0]=0;

            for (int j = 1; j <= amount; j++){
                //first row
                if(i==0 && j-denominations[i] < 0){
                    dp[i][j] = (int) Double.POSITIVE_INFINITY;
                } else if (i == 0 && j-denominations[i] >= 0){
                    dp[i][j] = dp[i][j-denominations[i]]+1;
                }

                //other rows: owed < denomination: cannot use denomination
                if(j-denominations[i] < 0 && i > 0){
                    dp[i][j] = dp[i-1][j];
                } else if (j-denominations[i] >= 0 && i > 0) {
                    //owed > denomination: can be used to pay
                    dp[i][j] = Math.min(dp[i][j-denominations[i]]+1, dp[i-1][j]);
                }

                System.out.println(dp[i][j]);

                //results for each possible owed amount are saved in the last row of the table
                int dpResult = dp[denominations.length-1][j];
                DpResultSet[j] = dpResult;
            }
        }
        return DpResultSet;
    }

    public static void main(String[] args) {
        int[] denominations = {1,2,3,4};
        int[] result;
        result = dynamicProcess(denominations, 6);
        System.out.println(result);
    }
}
