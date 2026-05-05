package com.cs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.entity.SalesforceOpportunity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Salesforce Opportunity（商机）Mapper
 *
 * @author LivePulse
 */
@Mapper
public interface SalesforceOpportunityMapper extends BaseMapper<SalesforceOpportunity> {
}
