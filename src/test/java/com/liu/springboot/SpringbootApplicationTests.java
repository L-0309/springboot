package com.liu.springboot;

import com.liu.springboot.mapper.AddressMapper;
import com.liu.springboot.pojo.Address;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringbootApplicationTests {

    @Resource
    AddressMapper addressMapper;

    @Test
    void contextLoads() {
        List<Address> list = addressMapper.selectList(null);
        list.forEach(System.out::println);
    }

}
