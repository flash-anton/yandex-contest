package common.str;

import java.util.Objects;

/**
 * Check equality of arrays by prefix hashes.<br>
 * <br>
 * partial example -> general rule<br>
 * <br>
 * pow[2] = x^2<br>
 * h[off] = H<br>
 * h[off+1] = h[off]*x + a[off] = H*x + a[off]<br>
 * h[off+2] = h[off+1]*x + a[off+1] = (H*x + a[off])*x + a[off+1] = H*x^2 + a[off+1]*x + a[off]<br>
 * <br>
 * h[off+2] - h[off] * pow[2]  =  (H*x^2 + a[off+1]*x + a[off]) - H*x^2  =  a[off+1]*x + a[off]<br>
 * <br>
 * if ((a[off+1]*x + a[off])  ==  (b[offB+1]*x + b[offB])) -> equals<br>
 * <br>
 * if ((h[off+2] - h[off] * pow[2])  ==  (hB[offB+2] - hB[offB] * pow[2])) -> equals<br>
 * <br>
 * avoiding operations with negative numbers<br>
 * if ((h[off+2] + hB[offB] * pow[2])  ==  (hB[offB+2] +  h[off] * pow[2])) -> equals
 */
public class PrefixHashesSimple {
    /**
     * A prime number slightly larger than the number of unique characters in the array, e.g.:<br>
     * 11 for 10 DEC symbols<br>
     * 17 for 16 HEX symbols<br>
     * 29 for 26 lowercase English letters<br>
     * ...<br>
     * 256/353 for 256 byte bits.
     */
    private final int x;

    /**
     * Divisor for remainder calculating, e.g. 1_000_000_007, 1_000_000_009, ...
     */
    private final int divisor;

    /**
     * Remainder of powered X:<br>
     * [0] = 1<br>
     * [i] = ([i-1] * x) % divisor
     */
    private final int[] poweredX;

    PrefixHashesSimple(int x, int divisor, int maxArrayLen) {
        this.x = x;
        this.divisor = divisor;

        poweredX = new int[1 + maxArrayLen];
        poweredX[0] = 1;
        for (int i = 1; i < 1 + maxArrayLen; i++)
            poweredX[i] = (int) (((long) poweredX[i - 1] * x) % divisor);
    }

    public int[] hashes(byte[] a) {
        int[] h = new int[1 + a.length];
        for (int i = 1; i < 1 + a.length; i++)
            h[i] = (int) (((long) h[i - 1] * x + a[i - 1]) % divisor);
        return h;
    }

    public boolean isEquals(int[] hashes1, int offset1, int[] hashes2, int offset2, int len) {
        Objects.checkFromIndexSize(offset1, len, hashes1.length);
        Objects.checkFromIndexSize(offset2, len, hashes2.length);
        if (hashes1.length > poweredX.length || hashes2.length > poweredX.length)
            throw new IllegalArgumentException("hashes.length must be <= " + (poweredX.length - 1));

        int a1 = (int) ((hashes1[offset1 + len] + (long) hashes2[offset2] * poweredX[len]) % divisor);
        int a2 = (int) ((hashes2[offset2 + len] + (long) hashes1[offset1] * poweredX[len]) % divisor);
        return a1 == a2;
    }
}
