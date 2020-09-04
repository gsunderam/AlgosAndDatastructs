package com.gs.home.toh;

import static com.gs.home.log.Logger.stdout;

/**
 * Time complexity = 2 power n - 1
 */
public class TowersOfHanoi {
    static int count;

    public static void main(String[] args) {
        int n = 3;
        toh(n, "A", "C", "B");
        stdout(String.format("No of steps = %d", count));
    }

    private static void toh(int n, String src, String dest, String aux) {
        if (n == 1) {
            stdout(String.format("Move disc 1 from %s to %s", src, dest));
            count++;
            return;
        }

        toh(n - 1, src, aux, dest);
        stdout(String.format("Move disc %d from %s to %s", n, src, dest));
        count++;
        toh(n - 1, aux, dest, src);
    }
}
