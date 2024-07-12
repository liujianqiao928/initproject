package com.ljq.project.service.impl;

import cn.hutool.json.JSONObject;
import com.ljq.project.service.TestMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class TestMongoServiceImpl implements TestMongoService {
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public List<JSONObject> queryAll() {
        Query query = new Query();
        // 查询所有数据
        query.addCriteria(Criteria.where("*"));

        return mongoTemplate.findAll(JSONObject.class,"device_data");
    }
}
