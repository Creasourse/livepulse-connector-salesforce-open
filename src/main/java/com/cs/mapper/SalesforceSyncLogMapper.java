package com.cs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.entity.SalesforceSyncLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * Salesforce 同步日志 Mapper
 *
 * @author LivePulse
 */
@Mapper
public interface SalesforceSyncLogMapper extends BaseMapper<SalesforceSyncLog> {
}
