package com.cs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cs.entity.SalesforceSyncLog;
import com.cs.mapper.SalesforceSyncLogMapper;
import com.cs.service.SalesforceSyncLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Salesforce 同步日志服务实现
 *
 * @author LivePulse
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SalesforceSyncLogServiceImpl implements SalesforceSyncLogService {

    private final SalesforceSyncLogMapper syncLogMapper;

    @Override
    public SalesforceSyncLog findById(Long id) {
        return syncLogMapper.selectById(id);
    }

    @Override
    public SalesforceSyncLog create(SalesforceSyncLog syncLog) {
        syncLogMapper.insert(syncLog);
        return syncLog;
    }

    @Override
    public void update(SalesforceSyncLog syncLog) {
        syncLogMapper.updateById(syncLog);
    }

    @Override
    public void logSuccess(Long logId, Long successCount, Long failureCount) {
        SalesforceSyncLog syncLog = findById(logId);
        if (syncLog != null) {
            syncLog.setSyncStatus("success");
            syncLog.setSuccessCount(successCount.intValue());
            syncLog.setFailureCount(failureCount.intValue());
            syncLog.setTotalCount(successCount.intValue() + failureCount.intValue());
            syncLog.setEndTime(java.time.LocalDateTime.now());
            if (syncLog.getStartTime() != null) {
                long duration = java.time.Duration.between(syncLog.getStartTime(), syncLog.getEndTime()).toMillis();
                syncLog.setDuration(duration);
            }
            update(syncLog);
        }
    }

    @Override
    public void logFailure(Long logId, String errorMessage) {
        SalesforceSyncLog syncLog = findById(logId);
        if (syncLog != null) {
            syncLog.setSyncStatus("failed");
            syncLog.setErrorMessage(errorMessage);
            syncLog.setEndTime(java.time.LocalDateTime.now());
            if (syncLog.getStartTime() != null) {
                long duration = java.time.Duration.between(syncLog.getStartTime(), syncLog.getEndTime()).toMillis();
                syncLog.setDuration(duration);
            }
            update(syncLog);
        }
    }

    @Override
    public SalesforceSyncLog logStart(Long storeId, String syncType, String syncMethod, String startDate, String endDate) {
        SalesforceSyncLog syncLog = new SalesforceSyncLog();
        syncLog.setStoreId(storeId);
        syncLog.setSyncType(syncType);
        syncLog.setSyncMethod(syncMethod);
        if (startDate != null) {
            syncLog.setStartDate(LocalDate.parse(startDate, java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd")));
        }
        if (endDate != null) {
            syncLog.setEndDate(LocalDate.parse(endDate, java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd")));
        }
        syncLog.setSyncStatus("running");
        syncLog.setTotalCount(0);
        syncLog.setSuccessCount(0);
        syncLog.setFailureCount(0);
        syncLog.setStartTime(java.time.LocalDateTime.now());
        create(syncLog);
        return syncLog;
    }
}
