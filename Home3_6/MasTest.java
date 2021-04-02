import org.junit.Assert;
import org.junit.Test;
import ru.geekbrains.homework3_6.Main;

public class MasTest {

    @Test
    public void test1MasAfter4 () {
        int [] in = new int[] {1, 3, 4, 6, 9, 10, 11};
        int [] out = new int[] {6, 9, 10, 11};
        Assert.assertArrayEquals(out, Main.masWithFour(in));
    }

    @Test (expected = RuntimeException.class)
    public void test2MasAfter4 () {
        int [] in = new int[] {7, 6, 8, 8};
        Main.masWithFour(in);
    }

    @Test
    public void test1oneAndFourMas () {
        int [] in = new int[] {1, 2, 3, 4, 5};
        Assert.assertFalse(Main.oneAndFourMas(in));
    }

    @Test
    public void test2oneAndFourMas () {
        int [] in = new int[] {1, 1, 4, 4};
        Assert.assertTrue(Main.oneAndFourMas(in));
    }

    @Test
    public void testAdd () {
        Assert.assertEquals(5, Main.add(2,3));
    }

}
