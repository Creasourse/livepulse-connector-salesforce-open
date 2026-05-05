package com.cs.task;

import com.cs.dto.SalesforceSyncResultDto;
import com.cs.entity.SalesforceStore;
import com.cs.service.SalesforceStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Salesforce 定时同步任务
 *
 * @author LivePulse
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "salesforce.scheduled.enabled", havingValue = "true", matchIfMissing = true)
public class SalesforceScheduledTask {

    private final SalesforceStoreService storeService;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 定时同步 Account（客户）
     * Cron: 每4小时执行一次
     */
    @Scheduled(cron = "${salesforce.scheduled.account-sync.cron:0 0 */4 * * ?}")
    public void syncAccounts() {
        log.info("开始定时同步 Salesforce Account");
        List<SalesforceStore> stores = storeService.findEnabled();
        String today = LocalDate.now().format(DATE_FORMATTER);
        String yesterday = LocalDate.now().minusDays(1).format(DATE_FORMATTER);

        for (SalesforceStore store : stores) {
            try {
                SalesforceSyncResultDto result = storeService.manualSyncAccounts(store.getId(), yesterday, today);
                log.info("Account 同步完成: {}", result.getMessage());
            } catch (Exception e) {
                log.error("Account 同步失败: storeId={}", store.getId(), e);
            }
        }
    }

    /**
     * 定时同步 Contact（联系人）
     * Cron: 每4小时执行一次
     */
    @Scheduled(cron = "${salesforce.scheduled.contact-sync.cron:0 0 */4 * * ?}")
    public void syncContacts() {
        log.info("开始定时同步 Salesforce Contact");
        List<SalesforceStore> stores = storeService.findEnabled();
        String today = LocalDate.now().format(DATE_FORMATTER);
        String yesterday = LocalDate.now().minusDays(1).format(DATE_FORMATTER);

        for (SalesforceStore store : stores) {
            try {
                SalesforceSyncResultDto result = storeService.manualSyncContacts(store.getId(), yesterday, today);
                log.info("Contact 同步完成: {}", result.getMessage());
            } catch (Exception e) {
                log.error("Contact 同步失败: storeId={}", store.getId(), e);
            }
        }
    }

    /**
     * 定时同步 Lead（线索）
     * Cron: 每4小时执行一次
     */
    @Scheduled(cron = "${salesforce.scheduled.lead-sync.cron:0 0 */4 * * ?}")
    public void syncLeads() {
        log.info("开始定时同步 Salesforce Lead");
        List<SalesforceStore> stores = storeService.findEnabled();
        String today = LocalDate.now().format(DATE_FORMATTER);
        String yesterday = LocalDate.now().minusDays(1).format(DATE_FORMATTER);

        for (SalesforceStore store : stores) {
            try {
                SalesforceSyncResultDto result = storeService.manualSyncLeads(store.getId(), yesterday, today);
                log.info("Lead 同步完成: {}", result.getMessage());
            } catch (Exception e) {
                log.error("Lead 同步失败: storeId={}", store.getId(), e);
            }
        }
    }

    /**
     * 定时同步 Opportunity（商机）
     * Cron: 每4小时执行一次
     */
    @Scheduled(cron = "${salesforce.scheduled.opportunity-sync.cron:0 0 */4 * * ?}")
    public void syncOpportunities() {
        log.info("开始定时同步 Salesforce Opportunity");
        List<SalesforceStore> stores = storeService.findEnabled();
        String today = LocalDate.now().format(DATE_FORMATTER);
        String yesterday = LocalDate.now().minusDays(1).format(DATE_FORMATTER);

        for (SalesforceStore store : stores) {
            try {
                SalesforceSyncResultDto result = storeService.manualSyncOpportunities(store.getId(), yesterday, today);
                log.info("Opportunity 同步完成: {}", result.getMessage());
            } catch (Exception e) {
                log.error("Opportunity 同步失败: storeId={}", store.getId(), e);
            }
        }
    }
}
