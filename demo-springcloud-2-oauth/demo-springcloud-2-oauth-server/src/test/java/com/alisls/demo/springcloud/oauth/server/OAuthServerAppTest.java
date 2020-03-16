package com.alisls.demo.springcloud.oauth.server;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试类
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class OAuthServerAppTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 获取加密密码
     */
    @Test
    public void getEncodePassword() {
        String srcPwd = "123456";
        String enPwd = passwordEncoder.encode(srcPwd);
        log.info("加密结果：" + enPwd);
    }

}
