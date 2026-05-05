package com.cs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Salesforce 连接配置实体
 *
 * @author LivePulse
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("salesforce_store")
@Schema(name = "SalesforceStore", description = "Salesforce 连接配置表")
public class SalesforceStore extends Model<SalesforceStore> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "Salesforce 实例 URL")
    @TableField("instance_url")
    private String instanceUrl;

    @Schema(description = "访问令牌")
    @TableField("access_token")
    private String accessToken;

    @Schema(description = "刷新令牌")
    @TableField("refresh_token")
    private String refreshToken;

    @Schema(description = "连接客户端 ID")
    @TableField("client_id")
    private String clientId;

    @Schema(description = "连接客户端密钥")
    @TableField("client_secret")
    private String clientSecret;

    @Schema(description = "API 版本")
    @TableField("api_version")
    private String apiVersion;

    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    @Schema(description = "是否启用")
    @TableField("enabled")
    private Boolean enabled;

    @Schema(description = "同步状态: pending/syncing/success/failed")
    @TableField("sync_status")
    private String syncStatus;

    @Schema(description = "最后客户同步时间")
    @TableField("last_account_sync_time")
    private LocalDateTime lastAccountSyncTime;

    @Schema(description = "最后联系人同步时间")
    @TableField("last_contact_sync_time")
    private LocalDateTime lastContactSyncTime;

    @Schema(description = "最后线索同步时间")
    @TableField("last_lead_sync_time")
    private LocalDateTime lastLeadSyncTime;

    @Schema(description = "最后商机同步时间")
    @TableField("last_opportunity_sync_time")
    private LocalDateTime lastOpportunitySyncTime;

    @Schema(description = "是否启用 CDC")
    @TableField("cdc_enabled")
    private Boolean cdcEnabled;

    @Schema(description = "CDC 频道名称")
    @TableField("cdc_channel_name")
    private String cdcChannelName;

    @Schema(description = "最后重放 ID")
    @TableField("last_replay_id")
    private Long lastReplayId;

    @Schema(description = "最后错误信息")
    @TableField("last_error_message")
    private String lastErrorMessage;

    @Schema(description = "重试次数")
    @TableField("retry_count")
    private Integer retryCount;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @Schema(description = "创建人")
    @TableField("create_by")
    private String createBy;

    @Schema(description = "更新人")
    @TableField("update_by")
    private String updateBy;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
