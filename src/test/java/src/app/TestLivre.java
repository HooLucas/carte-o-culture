package src.app;
import junit.framework.*;
import src.app.entity.Livre;
import org.junit.Test;

public class TestLivre extends TestCase {

    @Test
    public void testCRUD() throws Exception {
        Livre l1 = new Livre("Germinal","Victor Hugo","unknown.png");
        assertEquals("Germinal",l1.getTitle());
    }    
}
