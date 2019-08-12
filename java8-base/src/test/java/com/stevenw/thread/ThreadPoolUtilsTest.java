package com.stevenw.thread;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author stevenw
 * @date 2019/7/19
 */
@SpringBootTest
public class ThreadPoolUtilsTest {
    @Test
    public void testCreadThreadPool(){
        ThreadPoolUtils.createThreadPool();
    }
}
