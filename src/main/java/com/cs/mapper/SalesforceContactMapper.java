package com.cs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.entity.SalesforceContact;
import org.apache.ibatis.annotations.Mapper;

/**
 * Salesforce Contact（联系人）Mapper
 *
 * @author LivePulse
 */
@Mapper
public interface SalesforceContactMapper extends BaseMapper<SalesforceContact> {
}
