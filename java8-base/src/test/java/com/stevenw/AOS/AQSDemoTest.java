package com.stevenw.AOS;

import com.stevenw.AQS.AqsDemo;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stevenw
 * @date 2019/7/29
 */
@SpringBootTest
public class AQSDemoTest {
    @Test
    public void testSemaphore() throws InterruptedException {
//           AqsDemo.semaphoreDemo();
        AqsDemo.executorsDemo();
        System.err.println(Thread.currentThread().getName());
        Thread.sleep(3000);

    }
}
