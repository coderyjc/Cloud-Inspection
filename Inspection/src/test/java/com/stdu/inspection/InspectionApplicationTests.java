package com.stdu.inspection;

import com.stdu.inspection.mapper.DamageMapper;
import com.stdu.inspection.mapper.UserMapper;
import com.stdu.inspection.pojo.Damage;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
@MapperScan("com.stdu.inspection.mapper")
class InspectionApplicationTests {

    @Test
    void contextLoads() {
        Damage damage = new Damage();
        List<Damage> damages = damage.selectAll();
        for (Damage damage1 : damages) {
            System.out.println(damage1);
        }
    }

}
