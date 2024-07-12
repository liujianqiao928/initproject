package com.ljq.project;

import cn.hutool.json.JSONObject;
import com.ljq.project.service.TestMongoService;
import net.sf.jsqlparser.statement.select.KSQLJoinWindow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootLjqinitApplicationTests {
@Autowired
private TestMongoService testMongoService;
    @Test
    void contextLoads() {
        List<JSONObject> jsonObjects = testMongoService.queryAll();
        for (JSONObject jsonObject : jsonObjects) {
            System.out.println(jsonObject);
        }
    }

}
