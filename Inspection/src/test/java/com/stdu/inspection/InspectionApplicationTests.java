package com.stdu.inspection;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.stdu.inspection.mapper")
class InspectionApplicationTests {

    @Test
    void contextLoads() {
    }

}
