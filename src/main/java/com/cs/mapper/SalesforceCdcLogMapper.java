package com.cs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.entity.SalesforceCdcLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * Salesforce CDC 事件日志 Mapper
 *
 * @author LivePulse
 */
@Mapper
public interface SalesforceCdcLogMapper extends BaseMapper<SalesforceCdcLog> {
}
