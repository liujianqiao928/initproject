package com.ljq.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author xiaoqiao
* @description 针对表【org_patient(患者)】的数据库操作Mapper
* @createDate 2024-07-08 17:33:47
* @Entity generator.domain.OrgPatient
*/
public interface OrgPatientMapper extends BaseMapper<OrgPatient> {
    List<OrgPatient> selectAll();
}




