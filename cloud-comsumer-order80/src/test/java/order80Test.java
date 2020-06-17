import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(JUnit4.class)
public class order80Test {
    @Test
    public void test1(){
        String s1 = "abc";
        s1 = "mbcdg";
        System.out.println(s1);
    }
}
