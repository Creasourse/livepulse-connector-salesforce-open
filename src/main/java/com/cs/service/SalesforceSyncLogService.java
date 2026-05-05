package com.cs.service;

import com.cs.entity.SalesforceSyncLog;

/**
 * Salesforce 同步日志服务接口
 *
 * @author LivePulse
 */
public interface SalesforceSyncLogService {

    /**
     * 根据 ID 查询
     */
    SalesforceSyncLog findById(Long id);

    /**
     * 创建同步日志
     */
    SalesforceSyncLog create(SalesforceSyncLog syncLog);

    /**
     * 更新同步日志
     */
    void update(SalesforceSyncLog syncLog);

    /**
     * 记录成功
     */
    void logSuccess(Long logId, Long successCount, Long failureCount);

    /**
     * 记录失败
     */
    void logFailure(Long logId, String errorMessage);

    /**
     * 记录开始
     */
    SalesforceSyncLog logStart(Long storeId, String syncType, String syncMethod, String startDate, String endDate);
}
