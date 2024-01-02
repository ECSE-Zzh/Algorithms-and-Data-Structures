import java.util.*;

public class A2_Q4 {

	public static double swaps(int[] passengers) {
		return MergeSort(passengers, 0, passengers.length-1);
	}

	public static double MergeSort(int[] passengers, int start, int end){
		//base case: one element only
		if (start >= end){
			return 0.0;
		}

		int midPoint = (start+end)/2;

		double minLeftSwap = MergeSort(passengers, start, midPoint);
		double minRightSwap = MergeSort(passengers, midPoint+1, end);
		double minMergeSwap = Merge(passengers, start, midPoint, end);

		return minLeftSwap + minMergeSwap +minRightSwap;
	}
	public static double Merge(int[] passengers, int start, int midPoint, int end){
		int n1 = midPoint + 1 - start ;
		int n2 = end - midPoint;
		int i = 0;
		int j = 0;
		int k = start;
		double minSwapMerge = 0.0;

		int[] passengerLeft = new int[n1];
		for (int p = 0; p < passengerLeft.length; p++){
			passengerLeft[p] = passengers[start+p];
		}

		int[] passengerRight = new int[n2];
		for (int q = 0; q < passengerRight.length; q++){
			passengerRight[q] = passengers[midPoint+1+q];
		}

		for(k = start; i<passengerLeft.length && j<passengerRight.length; k++){
			// in order, no need to swap
			if(passengerLeft[i] <= passengerRight[j]){
				passengers[k] = passengerLeft[i];
				i++;
			} else {
				// left i to mid > right j, swap i to mid with right j
				passengers[k] =passengerRight[j];
				j++;
				minSwapMerge += midPoint - start - i + 1;
			}
		}


		if (j==passengerRight.length){
			while(i<passengerLeft.length){
				passengers[k]=passengerLeft[i];
				i++;
				k++;
			}
		} else if (i==passengerLeft.length){
			while(j<passengerRight.length){
				passengers[k]=passengerRight[j];
				j++;
				k++;
			}
		}
//		while(i<passengerLeft.length){
//			passengers[k]=passengerLeft[i];
//			i++;
//			k++;
//		}
//
//		while(j<passengerRight.length){
//			passengers[k]=passengerRight[j];
//			j++;
//			k++;
//		}

		return minSwapMerge;
	}

}
