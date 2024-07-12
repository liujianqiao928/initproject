package com.ljq.project.service;

import cn.hutool.json.JSONObject;

import java.util.List;

public interface TestMongoService {
    List<JSONObject> queryAll();
}
