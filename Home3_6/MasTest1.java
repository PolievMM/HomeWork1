import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.geekbrains.homework3_6.Main;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@RunWith(Parameterized.class)
public class MasTest1 {

    private int[] in;
    private int[] out;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 3, 4, 5, 6}, new int[]{5, 6}},
                {new int[]{1, 3, 4}, new int[]{}},
                {new int[]{1, 3, 4, 5, 6, 4}, new int[]{}},
                {new int[]{1, 3, 4, 5, 4, 7}, new int[]{7}}
        });
    }

    public MasTest1(int[] in, int[] out) {
        this.in = in;
        this.out = out;
    }

    @Test
    public void testMasWithFour() {
        Assert.assertArrayEquals(out, Main.masWithFour(in));
    }
}

