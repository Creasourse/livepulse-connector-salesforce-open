-- Salesforce 连接器 PostgreSQL 表结构
-- 版本: 1.2
-- 说明: 支持 Account、Contact、Lead、Opportunity 同步及 CDC 实时订阅

-- ============================================
-- 清理已存在的表（按依赖关系顺序）
-- ============================================
DROP TABLE IF EXISTS salesforce_opportunity CASCADE;
DROP TABLE IF EXISTS salesforce_contact CASCADE;
DROP TABLE IF EXISTS salesforce_lead CASCADE;
DROP TABLE IF EXISTS salesforce_account CASCADE;
DROP TABLE IF EXISTS salesforce_cdc_log CASCADE;
DROP TABLE IF EXISTS salesforce_sync_log CASCADE;
DROP TABLE IF EXISTS salesforce_store CASCADE;

-- ============================================
-- 1. Salesforce 连接配置表
-- ============================================
CREATE TABLE salesforce_store (
    id BIGSERIAL PRIMARY KEY,
    instance_url VARCHAR(500) NOT NULL,
    access_token VARCHAR(1000) NOT NULL,
    refresh_token VARCHAR(1000),
    client_id VARCHAR(255) NOT NULL,
    client_secret VARCHAR(500),
    api_version VARCHAR(20) DEFAULT '60.0',
    username VARCHAR(255),
    enabled BOOLEAN DEFAULT TRUE,
    sync_status VARCHAR(50) DEFAULT 'pending',
    last_account_sync_time TIMESTAMP,
    last_contact_sync_time TIMESTAMP,
    last_lead_sync_time TIMESTAMP,
    last_opportunity_sync_time TIMESTAMP,
    cdc_enabled BOOLEAN DEFAULT FALSE,
    cdc_channel_name VARCHAR(255),
    last_replay_id BIGINT DEFAULT -1,
    last_error_message TEXT,
    retry_count INT DEFAULT 0,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_by VARCHAR(100),
    update_by VARCHAR(100),
    CONSTRAINT uk_instance_url UNIQUE (instance_url)
);

-- 表注释
COMMENT ON TABLE salesforce_store IS 'Salesforce 连接配置表';
COMMENT ON COLUMN salesforce_store.instance_url IS 'Salesforce 实例 URL';
COMMENT ON COLUMN salesforce_store.access_token IS '访问令牌';
COMMENT ON COLUMN salesforce_store.refresh_token IS '刷新令牌';
COMMENT ON COLUMN salesforce_store.client_id IS '连接客户端 ID';
COMMENT ON COLUMN salesforce_store.client_secret IS '连接客户端密钥';
COMMENT ON COLUMN salesforce_store.api_version IS 'API 版本';
COMMENT ON COLUMN salesforce_store.username IS '用户名';
COMMENT ON COLUMN salesforce_store.enabled IS '是否启用';
COMMENT ON COLUMN salesforce_store.sync_status IS '同步状态: pending/syncing/success/failed';
COMMENT ON COLUMN salesforce_store.last_account_sync_time IS '最后客户同步时间';
COMMENT ON COLUMN salesforce_store.last_contact_sync_time IS '最后联系人同步时间';
COMMENT ON COLUMN salesforce_store.last_lead_sync_time IS '最后线索同步时间';
COMMENT ON COLUMN salesforce_store.last_opportunity_sync_time IS '最后商机同步时间';
COMMENT ON COLUMN salesforce_store.cdc_enabled IS '是否启用 CDC';
COMMENT ON COLUMN salesforce_store.cdc_channel_name IS 'CDC 频道名称';
COMMENT ON COLUMN salesforce_store.last_replay_id IS '最后重放 ID';
COMMENT ON COLUMN salesforce_store.last_error_message IS '最后错误信息';
COMMENT ON COLUMN salesforce_store.retry_count IS '重试次数';
COMMENT ON COLUMN salesforce_store.create_time IS '创建时间';
COMMENT ON COLUMN salesforce_store.update_time IS '更新时间';
COMMENT ON COLUMN salesforce_store.create_by IS '创建人';
COMMENT ON COLUMN salesforce_store.update_by IS '更新人';

-- ============================================
-- 2. Salesforce Account（客户）表
-- ============================================
CREATE TABLE salesforce_account (
    id BIGSERIAL PRIMARY KEY,
    store_id BIGINT NOT NULL,
    account_id VARCHAR(50) NOT NULL,
    account_name VARCHAR(500),
    account_number VARCHAR(100),
    type VARCHAR(100),
    industry VARCHAR(100),
    billing_street VARCHAR(500),
    billing_city VARCHAR(100),
    billing_state VARCHAR(100),
    billing_postal_code VARCHAR(50),
    billing_country VARCHAR(100),
    shipping_street VARCHAR(500),
    shipping_city VARCHAR(100),
    shipping_state VARCHAR(100),
    shipping_postal_code VARCHAR(50),
    shipping_country VARCHAR(100),
    phone VARCHAR(100),
    fax VARCHAR(100),
    website VARCHAR(500),
    owner_id VARCHAR(50),
    owner_name VARCHAR(255),
    annual_revenue DECIMAL(20, 2),
    number_of_employees INT,
    description TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    processed BOOLEAN DEFAULT FALSE,
    processed_time TIMESTAMP,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_account_store FOREIGN KEY (store_id) REFERENCES salesforce_store(id) ON DELETE CASCADE,
    CONSTRAINT uk_account_id UNIQUE (store_id, account_id)
);

CREATE INDEX idx_account_store_id ON salesforce_account(store_id);
CREATE INDEX idx_account_type ON salesforce_account(type);
CREATE INDEX idx_account_industry ON salesforce_account(industry);
CREATE INDEX idx_account_created_at ON salesforce_account(created_at);

-- 表注释
COMMENT ON TABLE salesforce_account IS 'Salesforce Account（客户）表';
COMMENT ON COLUMN salesforce_account.store_id IS '关联的连接 ID';
COMMENT ON COLUMN salesforce_account.account_id IS 'Salesforce Account ID';
COMMENT ON COLUMN salesforce_account.account_name IS '客户名称';
COMMENT ON COLUMN salesforce_account.account_number IS '客户编号';
COMMENT ON COLUMN salesforce_account.type IS '类型';
COMMENT ON COLUMN salesforce_account.industry IS '行业';
COMMENT ON COLUMN salesforce_account.billing_street IS '账单街道';
COMMENT ON COLUMN salesforce_account.billing_city IS '账单城市';
COMMENT ON COLUMN salesforce_account.billing_state IS '账单省/州';
COMMENT ON COLUMN salesforce_account.billing_postal_code IS '账单邮编';
COMMENT ON COLUMN salesforce_account.billing_country IS '账单国家';
COMMENT ON COLUMN salesforce_account.shipping_street IS '发货街道';
COMMENT ON COLUMN salesforce_account.shipping_city IS '发货城市';
COMMENT ON COLUMN salesforce_account.shipping_state IS '发货省/州';
COMMENT ON COLUMN salesforce_account.shipping_postal_code IS '发货邮编';
COMMENT ON COLUMN salesforce_account.shipping_country IS '发货国家';
COMMENT ON COLUMN salesforce_account.phone IS '电话';
COMMENT ON COLUMN salesforce_account.fax IS '传真';
COMMENT ON COLUMN salesforce_account.website IS '网站';
COMMENT ON COLUMN salesforce_account.owner_id IS '所有者 ID';
COMMENT ON COLUMN salesforce_account.owner_name IS '所有者姓名';
COMMENT ON COLUMN salesforce_account.annual_revenue IS '年收入';
COMMENT ON COLUMN salesforce_account.number_of_employees IS '员工数量';
COMMENT ON COLUMN salesforce_account.description IS '描述';
COMMENT ON COLUMN salesforce_account.created_at IS 'Salesforce 创建时间';
COMMENT ON COLUMN salesforce_account.updated_at IS 'Salesforce 更新时间';
COMMENT ON COLUMN salesforce_account.processed IS '是否已处理';
COMMENT ON COLUMN salesforce_account.processed_time IS '处理时间';

-- ============================================
-- 3. Salesforce Contact（联系人）表
-- ============================================
CREATE TABLE salesforce_contact (
    id BIGSERIAL PRIMARY KEY,
    store_id BIGINT NOT NULL,
    contact_id VARCHAR(50) NOT NULL,
    account_id VARCHAR(50),
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(100),
    mobile_phone VARCHAR(100),
    home_phone VARCHAR(100),
    title VARCHAR(255),
    department VARCHAR(100),
    mailing_street VARCHAR(500),
    mailing_city VARCHAR(100),
    mailing_state VARCHAR(100),
    mailing_postal_code VARCHAR(50),
    mailing_country VARCHAR(100),
    owner_id VARCHAR(50),
    owner_name VARCHAR(255),
    lead_source VARCHAR(100),
    birthdate DATE,
    description TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    processed BOOLEAN DEFAULT FALSE,
    processed_time TIMESTAMP,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_contact_store FOREIGN KEY (store_id) REFERENCES salesforce_store(id) ON DELETE CASCADE,
    CONSTRAINT uk_contact_id UNIQUE (store_id, contact_id)
);

CREATE INDEX idx_contact_store_id ON salesforce_contact(store_id);
CREATE INDEX idx_contact_account_id ON salesforce_contact(account_id);
CREATE INDEX idx_contact_email ON salesforce_contact(email);
CREATE INDEX idx_contact_created_at ON salesforce_contact(created_at);

-- 表注释
COMMENT ON TABLE salesforce_contact IS 'Salesforce Contact（联系人）表';
COMMENT ON COLUMN salesforce_contact.store_id IS '关联的连接 ID';
COMMENT ON COLUMN salesforce_contact.contact_id IS 'Salesforce Contact ID';
COMMENT ON COLUMN salesforce_contact.account_id IS '关联的 Account ID';
COMMENT ON COLUMN salesforce_contact.first_name IS '名字';
COMMENT ON COLUMN salesforce_contact.last_name IS '姓氏';
COMMENT ON COLUMN salesforce_contact.name IS '全名';
COMMENT ON COLUMN salesforce_contact.email IS '邮箱';
COMMENT ON COLUMN salesforce_contact.phone IS '电话';
COMMENT ON COLUMN salesforce_contact.mobile_phone IS '手机';
COMMENT ON COLUMN salesforce_contact.home_phone IS '家庭电话';
COMMENT ON COLUMN salesforce_contact.title IS '职位';
COMMENT ON COLUMN salesforce_contact.department IS '部门';
COMMENT ON COLUMN salesforce_contact.mailing_street IS '邮寄街道';
COMMENT ON COLUMN salesforce_contact.mailing_city IS '邮寄城市';
COMMENT ON COLUMN salesforce_contact.mailing_state IS '邮寄省/州';
COMMENT ON COLUMN salesforce_contact.mailing_postal_code IS '邮寄邮编';
COMMENT ON COLUMN salesforce_contact.mailing_country IS '邮寄国家';
COMMENT ON COLUMN salesforce_contact.owner_id IS '所有者 ID';
COMMENT ON COLUMN salesforce_contact.owner_name IS '所有者姓名';
COMMENT ON COLUMN salesforce_contact.lead_source IS '线索来源';
COMMENT ON COLUMN salesforce_contact.birthdate IS '生日';
COMMENT ON COLUMN salesforce_contact.description IS '描述';
COMMENT ON COLUMN salesforce_contact.created_at IS 'Salesforce 创建时间';
COMMENT ON COLUMN salesforce_contact.updated_at IS 'Salesforce 更新时间';
COMMENT ON COLUMN salesforce_contact.processed IS '是否已处理';
COMMENT ON COLUMN salesforce_contact.processed_time IS '处理时间';

-- ============================================
-- 4. Salesforce Lead（线索）表
-- ============================================
CREATE TABLE salesforce_lead (
    id BIGSERIAL PRIMARY KEY,
    store_id BIGINT NOT NULL,
    lead_id VARCHAR(50) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    name VARCHAR(255),
    company VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(100),
    mobile_phone VARCHAR(100),
    title VARCHAR(255),
    industry VARCHAR(100),
    status VARCHAR(100),
    rating VARCHAR(50),
    annual_revenue DECIMAL(20, 2),
    street VARCHAR(500),
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(50),
    country VARCHAR(100),
    owner_id VARCHAR(50),
    owner_name VARCHAR(255),
    lead_source VARCHAR(100),
    converted_account_id VARCHAR(50),
    converted_contact_id VARCHAR(50),
    converted_opportunity_id VARCHAR(50),
    is_converted BOOLEAN DEFAULT FALSE,
    converted_date TIMESTAMP,
    description TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    processed BOOLEAN DEFAULT FALSE,
    processed_time TIMESTAMP,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_lead_store FOREIGN KEY (store_id) REFERENCES salesforce_store(id) ON DELETE CASCADE,
    CONSTRAINT uk_lead_id UNIQUE (store_id, lead_id)
);

CREATE INDEX idx_lead_store_id ON salesforce_lead(store_id);
CREATE INDEX idx_lead_status ON salesforce_lead(status);
CREATE INDEX idx_lead_email ON salesforce_lead(email);
CREATE INDEX idx_lead_created_at ON salesforce_lead(created_at);

-- 表注释
COMMENT ON TABLE salesforce_lead IS 'Salesforce Lead（线索）表';
COMMENT ON COLUMN salesforce_lead.store_id IS '关联的连接 ID';
COMMENT ON COLUMN salesforce_lead.lead_id IS 'Salesforce Lead ID';
COMMENT ON COLUMN salesforce_lead.first_name IS '名字';
COMMENT ON COLUMN salesforce_lead.last_name IS '姓氏';
COMMENT ON COLUMN salesforce_lead.name IS '全名';
COMMENT ON COLUMN salesforce_lead.company IS '公司';
COMMENT ON COLUMN salesforce_lead.email IS '邮箱';
COMMENT ON COLUMN salesforce_lead.phone IS '电话';
COMMENT ON COLUMN salesforce_lead.mobile_phone IS '手机';
COMMENT ON COLUMN salesforce_lead.title IS '职位';
COMMENT ON COLUMN salesforce_lead.industry IS '行业';
COMMENT ON COLUMN salesforce_lead.status IS '状态';
COMMENT ON COLUMN salesforce_lead.rating IS '评级';
COMMENT ON COLUMN salesforce_lead.annual_revenue IS '年收入';
COMMENT ON COLUMN salesforce_lead.street IS '街道';
COMMENT ON COLUMN salesforce_lead.city IS '城市';
COMMENT ON COLUMN salesforce_lead.state IS '省/州';
COMMENT ON COLUMN salesforce_lead.postal_code IS '邮编';
COMMENT ON COLUMN salesforce_lead.country IS '国家';
COMMENT ON COLUMN salesforce_lead.owner_id IS '所有者 ID';
COMMENT ON COLUMN salesforce_lead.owner_name IS '所有者姓名';
COMMENT ON COLUMN salesforce_lead.lead_source IS '线索来源';
COMMENT ON COLUMN salesforce_lead.converted_account_id IS '转换的 Account ID';
COMMENT ON COLUMN salesforce_lead.converted_contact_id IS '转换的 Contact ID';
COMMENT ON COLUMN salesforce_lead.converted_opportunity_id IS '转换的 Opportunity ID';
COMMENT ON COLUMN salesforce_lead.is_converted IS '是否已转换';
COMMENT ON COLUMN salesforce_lead.converted_date IS '转换日期';
COMMENT ON COLUMN salesforce_lead.description IS '描述';
COMMENT ON COLUMN salesforce_lead.created_at IS 'Salesforce 创建时间';
COMMENT ON COLUMN salesforce_lead.updated_at IS 'Salesforce 更新时间';
COMMENT ON COLUMN salesforce_lead.processed IS '是否已处理';
COMMENT ON COLUMN salesforce_lead.processed_time IS '处理时间';

-- ============================================
-- 5. Salesforce Opportunity（商机）表
-- ============================================
CREATE TABLE salesforce_opportunity (
    id BIGSERIAL PRIMARY KEY,
    store_id BIGINT NOT NULL,
    opportunity_id VARCHAR(50) NOT NULL,
    account_id VARCHAR(50),
    opportunity_name VARCHAR(500),
    type VARCHAR(100),
    stage_name VARCHAR(100),
    amount DECIMAL(20, 2),
    probability DECIMAL(5, 2),
    expected_revenue DECIMAL(20, 2),
    close_date DATE,
    lead_source VARCHAR(100),
    campaign_id VARCHAR(50),
    owner_id VARCHAR(50),
    owner_name VARCHAR(255),
    description TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    processed BOOLEAN DEFAULT FALSE,
    processed_time TIMESTAMP,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_opportunity_store FOREIGN KEY (store_id) REFERENCES salesforce_store(id) ON DELETE CASCADE,
    CONSTRAINT uk_opportunity_id UNIQUE (store_id, opportunity_id)
);

CREATE INDEX idx_opportunity_store_id ON salesforce_opportunity(store_id);
CREATE INDEX idx_opportunity_account_id ON salesforce_opportunity(account_id);
CREATE INDEX idx_opportunity_stage_name ON salesforce_opportunity(stage_name);
CREATE INDEX idx_opportunity_close_date ON salesforce_opportunity(close_date);
CREATE INDEX idx_opportunity_created_at ON salesforce_opportunity(created_at);

-- 表注释
COMMENT ON TABLE salesforce_opportunity IS 'Salesforce Opportunity（商机）表';
COMMENT ON COLUMN salesforce_opportunity.store_id IS '关联的连接 ID';
COMMENT ON COLUMN salesforce_opportunity.opportunity_id IS 'Salesforce Opportunity ID';
COMMENT ON COLUMN salesforce_opportunity.account_id IS '关联的 Account ID';
COMMENT ON COLUMN salesforce_opportunity.opportunity_name IS '商机名称';
COMMENT ON COLUMN salesforce_opportunity.type IS '类型';
COMMENT ON COLUMN salesforce_opportunity.stage_name IS '阶段';
COMMENT ON COLUMN salesforce_opportunity.amount IS '金额';
COMMENT ON COLUMN salesforce_opportunity.probability IS '概率（百分比）';
COMMENT ON COLUMN salesforce_opportunity.expected_revenue IS '预期收入';
COMMENT ON COLUMN salesforce_opportunity.close_date IS '预计关闭日期';
COMMENT ON COLUMN salesforce_opportunity.lead_source IS '线索来源';
COMMENT ON COLUMN salesforce_opportunity.campaign_id IS '营销活动 ID';
COMMENT ON COLUMN salesforce_opportunity.owner_id IS '所有者 ID';
COMMENT ON COLUMN salesforce_opportunity.owner_name IS '所有者姓名';
COMMENT ON COLUMN salesforce_opportunity.description IS '描述';
COMMENT ON COLUMN salesforce_opportunity.created_at IS 'Salesforce 创建时间';
COMMENT ON COLUMN salesforce_opportunity.updated_at IS 'Salesforce 更新时间';
COMMENT ON COLUMN salesforce_opportunity.processed IS '是否已处理';
COMMENT ON COLUMN salesforce_opportunity.processed_time IS '处理时间';

-- ============================================
-- 6. Salesforce CDC 事件日志表
-- ============================================
CREATE TABLE salesforce_cdc_log (
    id BIGSERIAL PRIMARY KEY,
    store_id BIGINT,
    object_type VARCHAR(100) NOT NULL,
    record_id VARCHAR(50),
    change_type VARCHAR(50),
    change_event_date TIMESTAMP,
    replay_id BIGINT,
    payload JSONB,
    headers JSONB,
    processed_status VARCHAR(50) DEFAULT 'pending',
    error_message TEXT,
    received_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed_time TIMESTAMP,
    retry_count INT DEFAULT 0,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cdc_store FOREIGN KEY (store_id) REFERENCES salesforce_store(id) ON DELETE SET NULL
);

CREATE INDEX idx_cdc_store_id ON salesforce_cdc_log(store_id);
CREATE INDEX idx_cdc_object_type ON salesforce_cdc_log(object_type);
CREATE INDEX idx_cdc_status ON salesforce_cdc_log(processed_status);
CREATE INDEX idx_cdc_record ON salesforce_cdc_log(object_type, record_id);
CREATE INDEX idx_cdc_received_time ON salesforce_cdc_log(received_time);

-- 表注释
COMMENT ON TABLE salesforce_cdc_log IS 'Salesforce CDC 事件日志表';
COMMENT ON COLUMN salesforce_cdc_log.store_id IS '关联的连接 ID';
COMMENT ON COLUMN salesforce_cdc_log.object_type IS '对象类型: Account/Contact/Lead/Opportunity';
COMMENT ON COLUMN salesforce_cdc_log.record_id IS '记录 ID';
COMMENT ON COLUMN salesforce_cdc_log.change_type IS '变更类型: create/update/delete/undelete';
COMMENT ON COLUMN salesforce_cdc_log.change_event_date IS '变更事件时间';
COMMENT ON COLUMN salesforce_cdc_log.replay_id IS '重放 ID';
COMMENT ON COLUMN salesforce_cdc_log.payload IS 'CDC 负载';
COMMENT ON COLUMN salesforce_cdc_log.headers IS '请求头';
COMMENT ON COLUMN salesforce_cdc_log.processed_status IS '处理状态: pending/success/failed';
COMMENT ON COLUMN salesforce_cdc_log.error_message IS '错误信息';
COMMENT ON COLUMN salesforce_cdc_log.received_time IS '接收时间';
COMMENT ON COLUMN salesforce_cdc_log.processed_time IS '处理时间';
COMMENT ON COLUMN salesforce_cdc_log.retry_count IS '重试次数';
COMMENT ON COLUMN salesforce_cdc_log.create_time IS '创建时间';

-- ============================================
-- 7. Salesforce 同步日志表
-- ============================================
CREATE TABLE salesforce_sync_log (
    id BIGSERIAL PRIMARY KEY,
    store_id BIGINT NOT NULL,
    sync_type VARCHAR(50) NOT NULL,
    sync_method VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    sync_status VARCHAR(50) DEFAULT 'running',
    total_count INT DEFAULT 0,
    success_count INT DEFAULT 0,
    failure_count INT DEFAULT 0,
    error_message TEXT,
    start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_time TIMESTAMP,
    duration BIGINT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_sync_store FOREIGN KEY (store_id) REFERENCES salesforce_store(id) ON DELETE CASCADE
);

CREATE INDEX idx_salessync_store_id ON salesforce_sync_log(store_id);
CREATE INDEX idx_salessync_type ON salesforce_sync_log(sync_type);
CREATE INDEX idx_salessync_status ON salesforce_sync_log(sync_status);
CREATE INDEX idx_salessync_start_time ON salesforce_sync_log(start_time);

-- 表注释
COMMENT ON TABLE salesforce_sync_log IS 'Salesforce 同步日志表';
COMMENT ON COLUMN salesforce_sync_log.store_id IS '关联的连接 ID';
COMMENT ON COLUMN salesforce_sync_log.sync_type IS '同步类型: account/contact/lead/opportunity/full';
COMMENT ON COLUMN salesforce_sync_log.sync_method IS '同步方式: scheduled/manual/cdc';
COMMENT ON COLUMN salesforce_sync_log.start_date IS '开始日期';
COMMENT ON COLUMN salesforce_sync_log.end_date IS '结束日期';
COMMENT ON COLUMN salesforce_sync_log.sync_status IS '同步状态: running/success/failed';
COMMENT ON COLUMN salesforce_sync_log.total_count IS '总记录数';
COMMENT ON COLUMN salesforce_sync_log.success_count IS '成功数量';
COMMENT ON COLUMN salesforce_sync_log.failure_count IS '失败数量';
COMMENT ON COLUMN salesforce_sync_log.error_message IS '错误信息';
COMMENT ON COLUMN salesforce_sync_log.start_time IS '开始时间';
COMMENT ON COLUMN salesforce_sync_log.end_time IS '结束时间';
COMMENT ON COLUMN salesforce_sync_log.duration IS '耗时（毫秒）';
COMMENT ON COLUMN salesforce_sync_log.create_time IS '创建时间';

-- ============================================
-- 初始化数据
-- ============================================

-- 创建示例连接配置（开发环境）
-- INSERT INTO salesforce_store (instance_url, access_token, client_id, enabled, create_by)
-- VALUES ('https://yourinstance.salesforce.com', 'your-access-token', 'your-client-id', TRUE, 'system');
