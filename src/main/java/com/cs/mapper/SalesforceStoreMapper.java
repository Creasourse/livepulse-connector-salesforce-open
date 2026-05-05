package com.cs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.entity.SalesforceStore;
import org.apache.ibatis.annotations.Mapper;

/**
 * Salesforce 连接配置 Mapper
 *
 * @author LivePulse
 */
@Mapper
public interface SalesforceStoreMapper extends BaseMapper<SalesforceStore> {
}
