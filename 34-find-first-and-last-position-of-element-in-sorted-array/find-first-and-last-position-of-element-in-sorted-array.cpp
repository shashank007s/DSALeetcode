class Solution {
public:
int firtsocc(vector<int>& nums, int target){
int l=0;
int h=nums.size()-1;
int ans=-1;
while(l<=h){
    int mid=(l+h)/2;
    if(nums[mid]==target){
        ans=mid;
        h=mid-1;
    }
    else if(nums[mid]>target){
        h=mid-1;
    }
    else{
        l=mid+1;

    }
}
return ans;
}
int lastocc(vector<int>& nums, int target){
int l=0;
int h=nums.size()-1;
int ans=-1;
while(l<=h){
    int mid=(l+h)/2;
    if(nums[mid]==target){
        ans=mid;
        l=mid+1;;
    }
    else if(nums[mid]>target){
        h=mid-1;
    }
    else{
        l=mid+1;

    }
}
return ans;
}
    vector<int> searchRange(vector<int>& nums, int target) {
        int f=firtsocc(nums,target);
        int l=lastocc(nums,target);
        return {f,l};
    }
};