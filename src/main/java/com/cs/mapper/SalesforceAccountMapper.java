package com.cs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.entity.SalesforceAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * Salesforce Account（客户）Mapper
 *
 * @author LivePulse
 */
@Mapper
public interface SalesforceAccountMapper extends BaseMapper<SalesforceAccount> {
}
