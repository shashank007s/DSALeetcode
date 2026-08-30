class Solution {
    public int minimumDeletions(int[] nums) {
        int min = 0;
        int max = 0;
        int n = nums.length;
        for(int i=1;i<n;i++) {
            if(nums[min] > nums[i]) {
                min = i;
            }
            if(nums[max] < nums[i]) {
                max = i;
            }
        }
        int maxIdx = Math.max(min,max);
        int minIdx = Math.min(min,max);
        int res = maxIdx+1;
        res = Math.min(res,n-minIdx);
        res = Math.min(res,(minIdx+1)+(n-maxIdx));
        return res;
    }
}