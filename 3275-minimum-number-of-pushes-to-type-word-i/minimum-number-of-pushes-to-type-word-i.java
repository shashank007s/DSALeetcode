class Solution {
    public int minimumPushes(String word) {
        int size = word.length();
        if(size <= 8){
            return size;
        }
        else if(size > 8 && size <= 16){
            int residue = size - 8;
            int answer = (residue*2) + 8;
            return answer;
        }
        else if(size > 16 && size <= 24){
            int residue1 = size - 16;
            int answer = 8 + 16 + (residue1*3);
            return answer;
        }
        else{
            int residue= size - 24;
            int answer= 8 + 16 + 24 + (residue*4);
            return answer;
        }
    }
}