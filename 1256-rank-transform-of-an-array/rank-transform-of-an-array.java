import java.util.*;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;

        // Copy and sort the array
        int[] temp = arr.clone();
        Arrays.sort(temp);

        // Assign ranks to unique elements
        HashMap<Integer, Integer> map = new HashMap<>();
        int rank = 1;

        for (int num : temp) {
            if (!map.containsKey(num)) {
                map.put(num, rank++);
            }
        }

        // Replace each element with its rank
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = map.get(arr[i]);
        }

        return ans;
    }
}