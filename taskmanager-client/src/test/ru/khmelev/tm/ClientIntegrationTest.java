import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(CRUDIntegrationTest.class)
public class ClientIntegrationTest {

    @Test
    public void proverka1() {
        Assert.assertEquals("111ssds", "111ssds");
    }

    //@Category(CRUDIntegrationTest.class)
    @Test
    public void proverka2() {
        Assert.assertEquals("111ssds", "111ssds");
    }
}