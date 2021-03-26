import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.geekbrains.homework3_6.Main;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MasTest2 {

    private int[] in;
    private boolean flag;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{3, 4, 5, 6}, false},
                {new int[]{1, 4, 4}, true},
                {new int[]{4, 4, 4}, false},
                {new int[]{1, 1, 1, 1}, false}
        });
    }

    public MasTest2 (int[]in,boolean flag) {
        this.in = in;
        this.flag = flag;
    }

    @Test
    public void testOneAndFourMas () {
        Assert.assertEquals(flag, Main.oneAndFourMas(in));
    }
}
