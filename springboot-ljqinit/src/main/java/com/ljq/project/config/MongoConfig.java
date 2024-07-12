package com.ljq.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

/**
 * Program: MedTrack
 * <p>
 * Description: MongoDB配置类
 * </p>
 *
 * @author Lean
 * @version 1.0
 * @since 2023-04-25
 */
@Configuration
public class MongoConfig {
    /**
     * setMapKeyDotReplacement
     * <p>
     * 将key中的点（.）转换为下划线（_）
     * </p>
     * 
     * @param mappingMongoConverter
     * @return void
     */
    @Autowired
    void setMapKeyDotReplacement(MappingMongoConverter mappingMongoConverter) {
        mappingMongoConverter.setMapKeyDotReplacement("__mongo_key__");
    }
}
