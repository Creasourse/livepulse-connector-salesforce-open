package com.cs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Salesforce 连接器配置
 *
 * @author LivePulse
 */
@Configuration
@ConfigurationProperties(prefix = "salesforce")
public class SalesforceConfig {

    /**
     * API 配置
     */
    private Api api = new Api();

    /**
     * 同步配置
     */
    private Sync sync = new Sync();

    /**
     * 调度任务配置
     */
    private Scheduled scheduled = new Scheduled();

    /**
     * CDC 配置
     */
    private Cdc cdc = new Cdc();

    public static class Api {
        /**
         * API 版本
         */
        private String version = "60.0";

        /**
         * 请求超时时间（秒）
         */
        private int timeout = 30;

        /**
         * 连接超时时间（秒）
         */
        private int connectTimeout = 10;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }

        public int getConnectTimeout() {
            return connectTimeout;
        }

        public void setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
        }
    }

    public static class Sync {
        /**
         * 批次大小
         */
        private int batchSize = 100;

        /**
         * 最大重试次数
         */
        private int maxRetries = 3;

        /**
         * 重试延迟（毫秒）
         */
        private int retryDelay = 1000;

        /**
         * 每次查询的最大天数
         */
        private int maxQueryDays = 30;

        public int getBatchSize() {
            return batchSize;
        }

        public void setBatchSize(int batchSize) {
            this.batchSize = batchSize;
        }

        public int getMaxRetries() {
            return maxRetries;
        }

        public void setMaxRetries(int maxRetries) {
            this.maxRetries = maxRetries;
        }

        public int getRetryDelay() {
            return retryDelay;
        }

        public void setRetryDelay(int retryDelay) {
            this.retryDelay = retryDelay;
        }

        public int getMaxQueryDays() {
            return maxQueryDays;
        }

        public void setMaxQueryDays(int maxQueryDays) {
            this.maxQueryDays = maxQueryDays;
        }
    }

    public static class Scheduled {
        /**
         * 是否启用调度任务
         */
        private boolean enabled = true;

        /**
         * Account 同步配置
         */
        private AccountSync accountSync = new AccountSync();

        /**
         * Contact 同步配置
         */
        private ContactSync contactSync = new ContactSync();

        /**
         * Lead 同步配置
         */
        private LeadSync leadSync = new LeadSync();

        /**
         * Opportunity 同步配置
         */
        private OpportunitySync opportunitySync = new OpportunitySync();

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public AccountSync getAccountSync() {
            return accountSync;
        }

        public void setAccountSync(AccountSync accountSync) {
            this.accountSync = accountSync;
        }

        public ContactSync getContactSync() {
            return contactSync;
        }

        public void setContactSync(ContactSync contactSync) {
            this.contactSync = contactSync;
        }

        public LeadSync getLeadSync() {
            return leadSync;
        }

        public void setLeadSync(LeadSync leadSync) {
            this.leadSync = leadSync;
        }

        public OpportunitySync getOpportunitySync() {
            return opportunitySync;
        }

        public void setOpportunitySync(OpportunitySync opportunitySync) {
            this.opportunitySync = opportunitySync;
        }

        public static class AccountSync {
            private boolean enabled = true;
            private String cron = "0 0 */4 * * ?";
            private int initialDelay = 60;
            private int fixedDelay = 14400;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getCron() {
                return cron;
            }

            public void setCron(String cron) {
                this.cron = cron;
            }

            public int getInitialDelay() {
                return initialDelay;
            }

            public void setInitialDelay(int initialDelay) {
                this.initialDelay = initialDelay;
            }

            public int getFixedDelay() {
                return fixedDelay;
            }

            public void setFixedDelay(int fixedDelay) {
                this.fixedDelay = fixedDelay;
            }
        }

        public static class ContactSync {
            private boolean enabled = true;
            private String cron = "0 0 */4 * * ?";
            private int initialDelay = 120;
            private int fixedDelay = 14400;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getCron() {
                return cron;
            }

            public void setCron(String cron) {
                this.cron = cron;
            }

            public int getInitialDelay() {
                return initialDelay;
            }

            public void setInitialDelay(int initialDelay) {
                this.initialDelay = initialDelay;
            }

            public int getFixedDelay() {
                return fixedDelay;
            }

            public void setFixedDelay(int fixedDelay) {
                this.fixedDelay = fixedDelay;
            }
        }

        public static class LeadSync {
            private boolean enabled = true;
            private String cron = "0 0 */4 * * ?";
            private int initialDelay = 180;
            private int fixedDelay = 14400;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getCron() {
                return cron;
            }

            public void setCron(String cron) {
                this.cron = cron;
            }

            public int getInitialDelay() {
                return initialDelay;
            }

            public void setInitialDelay(int initialDelay) {
                this.initialDelay = initialDelay;
            }

            public int getFixedDelay() {
                return fixedDelay;
            }

            public void setFixedDelay(int fixedDelay) {
                this.fixedDelay = fixedDelay;
            }
        }

        public static class OpportunitySync {
            private boolean enabled = true;
            private String cron = "0 0 */4 * * ?";
            private int initialDelay = 240;
            private int fixedDelay = 14400;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getCron() {
                return cron;
            }

            public void setCron(String cron) {
                this.cron = cron;
            }

            public int getInitialDelay() {
                return initialDelay;
            }

            public void setInitialDelay(int initialDelay) {
                this.initialDelay = initialDelay;
            }

            public int getFixedDelay() {
                return fixedDelay;
            }

            public void setFixedDelay(int fixedDelay) {
                this.fixedDelay = fixedDelay;
            }
        }
    }

    public static class Cdc {
        /**
         * 是否启用 CDC
         */
        private boolean enabled = false;

        /**
         * 订阅的对象
         */
        private String[] objects = {"Account", "Contact", "Lead", "Opportunity"};

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String[] getObjects() {
            return objects;
        }

        public void setObjects(String[] objects) {
            this.objects = objects;
        }
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public Sync getSync() {
        return sync;
    }

    public void setSync(Sync sync) {
        this.sync = sync;
    }

    public Scheduled getScheduled() {
        return scheduled;
    }

    public void setScheduled(Scheduled scheduled) {
        this.scheduled = scheduled;
    }

    public Cdc getCdc() {
        return cdc;
    }

    public void setCdc(Cdc cdc) {
        this.cdc = cdc;
    }
}
