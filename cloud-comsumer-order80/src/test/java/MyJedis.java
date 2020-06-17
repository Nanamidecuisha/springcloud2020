/**
 * @Author: liujianbin
 * @Date: 2020/6/17 21:51
 */

import com.hteatime.springcloud.OrderMain80;
import com.hteatime.springcloud.util.MyRedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 *MyJedis
 *
 *@Author: Saber
 *@CreateTime: 2020-06-17
 */
@SpringBootTest(classes = OrderMain80.class)
@RunWith(SpringRunner.class)
public class MyJedis {
   @Autowired
   private MyRedisUtil redisUtil;

    @Test
    public void test1(){
        String set = redisUtil.set("htt", "handsome", 1);
        System.err.println(set);
    }

}
