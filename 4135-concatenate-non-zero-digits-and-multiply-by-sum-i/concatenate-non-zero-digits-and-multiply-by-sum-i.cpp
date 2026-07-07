class Solution {
public:
    long long suma(long long n) {
        long long sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    long long concat(int n) {
        string s = to_string(n);
        long long x = 0;

        for (char c : s) {
            if (c != '0') {
                x = x * 10 + (c - '0');
            }
        }

        return x;
    }

    long long sumAndMultiply(int n) {
        long long x = concat(n);
        long long sum = suma(x);
        return x * sum;
    }
};