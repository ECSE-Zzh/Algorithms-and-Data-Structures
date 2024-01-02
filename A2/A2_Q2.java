import java.util.*;

public class A2_Q2 {

	public static int change(int[] denominations) {
		int amount = denominations[denominations.length-1] + denominations[denominations.length-2];
		int[] greedy;
		int[] dp;

		greedy = greedy(denominations, amount);
		//System.out.println("greedyLength: "+greedy.length);
		dp = dynamicProcess(denominations, amount);
		//System.out.println("dpLength: "+dp.length);

		for (int i = 0; i < greedy.length && i < dp.length; i++){
			//System.out.println("greedy: "+greedy[i]);
			//System.out.println("dp: "+dp[i]);
			if (greedy[i] != dp[i]){
				return i;
			}
		}
		return -1;
	}

	public static int[] greedy (int[] denominations, int amount){
		int greedyResult = 0;
		int range = denominations[denominations.length-1] + denominations[denominations.length-2];
		int[] greedyResultSet = new int[range+1];
		greedyResultSet[0] = 0;

//		//base case
		if (amount == 0){
			return greedyResultSet;
		}
		while (amount > 0 && amount < denominations[0]){
			greedyResult = (int)Double.POSITIVE_INFINITY;
			greedyResultSet[amount] = greedyResult;
		}
		for(amount = range; amount >= denominations[0]; amount--) {
			int i = denominations.length - 1; //largest coin
			int stillOwed = amount;
			while (i >= 0) {
				while (stillOwed >= denominations[i] && greedyResult < 101) {
					stillOwed -= denominations[i];
					greedyResult++;
				}
				i--;
			}
			//no solution
			if (stillOwed != 0 || greedyResult >= 101) {
				greedyResult = (int) Double.POSITIVE_INFINITY;
			}
			greedyResultSet[amount] = greedyResult;
			greedyResult = 0;
		}

		if (amount < denominations[0]){
			for (int i = amount; i > 0; i--){
				greedyResultSet[i] = (int) Double.POSITIVE_INFINITY;
			}
		}
		//greedy(denominations, amount-1);
		return greedyResultSet;
	}

	public static int[] dynamicProcess (int[] denominations, int amount){
		int[] dpResultSet = new int[amount+1];
		dpResultSet[0] = 0;

		if (amount < denominations[0]){
			for (int i = amount; i > 0; i--){
				dpResultSet[i] = (int) Double.POSITIVE_INFINITY;
				return dpResultSet;
			}
		}

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

				//results for each possible owed amount are saved in the last row of the table
				int dpResult = dp[denominations.length-1][j];
				if (dpResult >= 101){
					dpResult = (int) Double.POSITIVE_INFINITY;
				}
				dpResultSet[j] = dpResult;
			}
		}
		return dpResultSet;
	}

	public static void main(String[] args) {
		int[] denominations = {1,2,4,5};
		int [] test;
		int [] test2;
		int result = change(denominations);
		test = dynamicProcess(denominations, 0);
		test2 = greedy(denominations, 0);
		System.out.println("test1: "+test[0]);
		System.out.println("test2: "+test2[0]);
		System.out.println(result);

	}

}
