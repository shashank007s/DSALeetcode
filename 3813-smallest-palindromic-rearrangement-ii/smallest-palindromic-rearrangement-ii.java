class Solution {
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;

        int oddIdx = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) oddIdx = i;
        }

        int[] half = new int[26];
        int halfLen = 0;
        for (int i = 0; i < 26; i++) {
            half[i] = count[i] / 2;
            halfLen += half[i];
        }

        long Top = 3_000_000_000L; // Integer.MAX_VALUE se thoda upar, safe clamp

        long total = countArrangements(half, halfLen, Top);
        if (total < k) return ""; // itne distinct palindromes hain hi nahi

        StringBuilder sb = new StringBuilder();
        long remainingK = k;
        int remaining = halfLen;

        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--; // tentatively is character ko yahan rakh ke dekho
                long arrangements = countArrangements(half, remaining - 1, Top);

                if (remainingK <= arrangements) {
                    sb.append((char) ('a' + c));
                    break; // ye character confirm, agli position pe jao
                } else {
                    remainingK -= arrangements; // inko skip karo, aage badho
                    half[c]++; // revert
                }
            }
            remaining--;
        }

        String halfStr = sb.toString();
        StringBuilder full = new StringBuilder(halfStr);
        if (oddIdx != -1) full.append((char) ('a' + oddIdx));
        full.append(new StringBuilder(halfStr).reverse());
        return full.toString();
    }

    // Remaining multiset (cnt) ke saare distinct permutations count karta hai,
    // without factorial overflow — group-by-group combination trick.
    public long countArrangements(int[] cnt, int total, long Top) {
        long result = 1;
        int remaining = total;
        for (int i = 0; i < 26 && result <= Top; i++) {
            int c = cnt[i];
            long comb = 1;
            for (int j = 1; j <= c; j++) {
                comb = comb * (remaining - c + j) / j; // C(remaining, c) incrementally
                if (comb > Top) { comb = Top + 1; break; }
            }
            result *= comb;
            if (result > Top) { result = Top + 1; break; }
            remaining -= c;
        }
        return result;
    }
}