import junit.framework.TestCase;
/**
 * Created by WSYOU on 2017-12-14.
 */
public class TestUnitTest extends TestCase {

    public void testgetSum() {
        int i = 0;
        int j = 10;
        assertTrue(i+j == 10);
    }

    public void testgetSum2() {
        int i = 10;
        int j = 10;
        assertTrue(i+j == 20);
    }
}
