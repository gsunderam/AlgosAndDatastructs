package com.gs.home.numbers;

import static com.gs.home.log.Logger.stdout;

/**
 * Euclid's algorithm to calculate GCD of two numbers.
 */
public class GCDEuclid {
    public static int gcd(int a, int b) {
        if (a == 0)
            return b;

        return gcd(b%a, a);
    }

    /**
     * claculate lcm using the above GCD algorithm
     * @param a
     * @param b
     * @return LCM of a and b
     */
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    public static int lcm2(int a, int b) {
        int high = Math.max(a, b);
        int low = Math.min(a, b);
        int lcm = high;

        while (lcm % low != 0) {
            lcm += high;
        }

        return lcm;
    }

    public static void main(String[] args) {
        stdout(String.format("GCD of 20 and 32: %d", gcd(20, 32)));
        stdout(String.format("GCD of 32 and 20: %d", gcd(32, 20)));
        stdout(String.format("LCM of 32 and 20: %d", lcm(32, 20)));
        stdout(String.format("LCM of 4 and 5: %d", lcm(5, 4)));
        stdout(String.format("LCM of 4 and 5 by LCM2: %d", lcm2(5, 4)));
        stdout(String.format("LCM of 23 and 27 by LCM2: %d", lcm2(23, 27)));
    }
}
