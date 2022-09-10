package com.ljq.project.feign;

import com.ljq.project.config.FeignLogConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "provider",configuration = FeignLogConfig.class)
public interface UserFeign {
    @RequestMapping("/user/getUser")
    public String getUser();

}
