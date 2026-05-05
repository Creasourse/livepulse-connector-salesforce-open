package com.cs.controller;

import com.cs.dto.SalesforceSyncResultDto;
import com.cs.entity.SalesforceStore;
import com.cs.service.SalesforceStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Salesforce 连接管理控制器
 *
 * @author LivePulse
 */
@RestController
@RequestMapping("/salesforce/store")
@RequiredArgsConstructor
@Tag(name = "Salesforce 连接管理", description = "Salesforce 连接配置和管理 API")
public class SalesforceStoreController {

    private final SalesforceStoreService storeService;

    @GetMapping("/{id}")
    @Operation(summary = "根据 ID 查询连接")
    public ResponseEntity<SalesforceStore> getById(@PathVariable Long id) {
        SalesforceStore store = storeService.findById(id);
        return store != null ? ResponseEntity.ok(store) : ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "查询所有连接")
    public ResponseEntity<List<SalesforceStore>> list() {
        return ResponseEntity.ok(storeService.findEnabled());
    }

    @PostMapping
    @Operation(summary = "创建连接")
    public ResponseEntity<SalesforceStore> create(@RequestBody SalesforceStore store) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storeService.create(store));
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新连接")
    public ResponseEntity<SalesforceStore> update(@PathVariable Long id, @RequestBody SalesforceStore store) {
        store.setId(id);
        return ResponseEntity.ok(storeService.update(store));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除连接")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        storeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/test")
    @Operation(summary = "测试连接")
    public ResponseEntity<Boolean> testConnection(@PathVariable Long id) {
        return ResponseEntity.ok(storeService.testConnection(id));
    }

    @PostMapping("/{id}/sync/accounts")
    @Operation(summary = "手动同步 Account（客户）")
    public ResponseEntity<SalesforceSyncResultDto> syncAccounts(
            @PathVariable Long id,
            @Parameter(description = "开始日期，格式：yyyyMMdd") @RequestParam String startDate,
            @Parameter(description = "结束日期，格式：yyyyMMdd") @RequestParam String endDate) {
        return ResponseEntity.ok(storeService.manualSyncAccounts(id, startDate, endDate));
    }

    @PostMapping("/{id}/sync/contacts")
    @Operation(summary = "手动同步 Contact（联系人）")
    public ResponseEntity<SalesforceSyncResultDto> syncContacts(
            @PathVariable Long id,
            @Parameter(description = "开始日期，格式：yyyyMMdd") @RequestParam String startDate,
            @Parameter(description = "结束日期，格式：yyyyMMdd") @RequestParam String endDate) {
        return ResponseEntity.ok(storeService.manualSyncContacts(id, startDate, endDate));
    }

    @PostMapping("/{id}/sync/leads")
    @Operation(summary = "手动同步 Lead（线索）")
    public ResponseEntity<SalesforceSyncResultDto> syncLeads(
            @PathVariable Long id,
            @Parameter(description = "开始日期，格式：yyyyMMdd") @RequestParam String startDate,
            @Parameter(description = "结束日期，格式：yyyyMMdd") @RequestParam String endDate) {
        return ResponseEntity.ok(storeService.manualSyncLeads(id, startDate, endDate));
    }

    @PostMapping("/{id}/sync/opportunities")
    @Operation(summary = "手动同步 Opportunity（商机）")
    public ResponseEntity<SalesforceSyncResultDto> syncOpportunities(
            @PathVariable Long id,
            @Parameter(description = "开始日期，格式：yyyyMMdd") @RequestParam String startDate,
            @Parameter(description = "结束日期，格式：yyyyMMdd") @RequestParam String endDate) {
        return ResponseEntity.ok(storeService.manualSyncOpportunities(id, startDate, endDate));
    }
}
