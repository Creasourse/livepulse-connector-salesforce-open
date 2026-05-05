package com.cs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.entity.SalesforceLead;
import org.apache.ibatis.annotations.Mapper;

/**
 * Salesforce Lead（线索）Mapper
 *
 * @author LivePulse
 */
@Mapper
public interface SalesforceLeadMapper extends BaseMapper<SalesforceLead> {
}
