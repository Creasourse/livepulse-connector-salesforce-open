package com.cs.service;

import com.cs.dto.SalesforceSyncResultDto;
import com.cs.entity.SalesforceStore;

import java.util.List;

/**
 * Salesforce 连接服务接口
 *
 * @author LivePulse
 */
public interface SalesforceStoreService {

    /**
     * 根据 ID 查询
     */
    SalesforceStore findById(Long id);

    /**
     * 根据实例 URL 查询
     */
    SalesforceStore findByInstanceUrl(String instanceUrl);

    /**
     * 查询所有启用的连接
     */
    List<SalesforceStore> findEnabled();

    /**
     * 创建连接
     */
    SalesforceStore create(SalesforceStore store);

    /**
     * 更新连接
     */
    SalesforceStore update(SalesforceStore store);

    /**
     * 删除连接
     */
    void delete(Long id);

    /**
     * 测试连接
     */
    boolean testConnection(Long id);

    /**
     * 手动同步 Account（客户）
     */
    SalesforceSyncResultDto manualSyncAccounts(Long id, String startDate, String endDate);

    /**
     * 手动同步 Contact（联系人）
     */
    SalesforceSyncResultDto manualSyncContacts(Long id, String startDate, String endDate);

    /**
     * 手动同步 Lead（线索）
     */
    SalesforceSyncResultDto manualSyncLeads(Long id, String startDate, String endDate);

    /**
     * 手动同步 Opportunity（商机）
     */
    SalesforceSyncResultDto manualSyncOpportunities(Long id, String startDate, String endDate);
}
