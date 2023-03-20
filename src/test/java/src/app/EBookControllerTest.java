package src.app;

import org.junit.Test;

import src.app.controller.EBookController;
import static org.junit.Assert.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EBookControllerTest {
    
    @Test
    public void evaluateReadFile() {
        EBookController control = new EBookController();
        String filepath = "/";
        control.loadDataFromFile(filepath);
    }
}