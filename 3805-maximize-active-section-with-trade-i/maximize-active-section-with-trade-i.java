class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();

        // Count original active sections (1's)
        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        // Augment with '1' at both ends
        String t = "1" + s + "1";

        // Run-length encoding
        java.util.ArrayList<Character> ch = new java.util.ArrayList<>();
        java.util.ArrayList<Integer> len = new java.util.ArrayList<>();

        int i = 0;
        while (i < t.length()) {
            char c = t.charAt(i);
            int j = i;
            while (j < t.length() && t.charAt(j) == c) j++;
            ch.add(c);
            len.add(j - i);
            i = j;
        }

        int ans = ones;

        // Find an internal 1-block surrounded by 0-blocks
        for (i = 1; i < ch.size() - 1; i++) {
            if (ch.get(i) == '1'
                    && ch.get(i - 1) == '0'
                    && ch.get(i + 1) == '0') {

                int gain = len.get(i - 1) + len.get(i + 1);
                ans = Math.max(ans, ones + gain);
            }
        }

        return ans;
    }
}