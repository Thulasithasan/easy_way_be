-- liquibase formatted sql

-- liquibase formatted sql

-- ===================================
-- ROLES
-- ===================================
-- changeset Thulasithsan:v1-create-roles-table
CREATE TABLE IF NOT EXISTS roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now()
);
-- rollback drop table roles;

-- ===================================
-- PERMISSIONS
-- ===================================
-- changeset Thulasithsan:v1-create-permissions-table
CREATE TABLE IF NOT EXISTS permissions (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now()
);
-- rollback drop table permissions;

-- ===================================
-- ROLE_PERMISSIONS
-- ===================================
-- changeset Thulasithsan:v1-create-role_permissions-table
CREATE TABLE IF NOT EXISTS role_permissions (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    sub_permissions TEXT,
    PRIMARY KEY (role_id, permission_id),
    CONSTRAINT fk_role_permissions_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    CONSTRAINT fk_role_permissions_permission FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE
);
-- rollback drop table role_permissions;

-- ===================================
-- USERS
-- ===================================
-- changeset Thulasithsan:v1-create-users-table
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(255),
    address VARCHAR(255),
    province VARCHAR(100),
    district VARCHAR(100),
    city VARCHAR(100),
    password VARCHAR(255),
    previous_passwords TEXT,
    is_password_changed BOOLEAN DEFAULT FALSE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    role_id BIGINT,
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE SET NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now()
);
-- rollback drop table users;

-- ===================================
-- BRANCH
-- ===================================
-- changeset Thulasithsan:v1-create-branch-table
CREATE TABLE IF NOT EXISTS branches (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(50),
    address TEXT,
    province VARCHAR(100),
    district VARCHAR(100),
    city VARCHAR(100),
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now()
);
-- rollback drop table branches;

-- ===================================
-- CATEGORY
-- ===================================
-- changeset Thulasithsan:v1-create-category-table
CREATE TABLE IF NOT EXISTS categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now()
);
-- rollback drop table categories;

-- ===================================
-- SUB_CATEGORY
-- ===================================
-- changeset Thulasithsan:v1-create-sub_category-table
CREATE TABLE IF NOT EXISTS sub_categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    category_id BIGINT,
    CONSTRAINT fk_sub_category_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);
-- rollback drop table sub_categories;

-- ===================================
-- PRODUCT
-- ===================================
-- changeset Thulasithsan:v1-create-product-table
CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    measurement_value NUMERIC(15,2) NOT NULL,
    measurement_unit VARCHAR(50) NOT NULL,
    hero_image VARCHAR(255),
    images TEXT,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    sub_category_id BIGINT NOT NULL,
    CONSTRAINT fk_product_sub_category FOREIGN KEY (sub_category_id) REFERENCES sub_categories(id) ON DELETE CASCADE,
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now()
);
-- rollback drop table products;

-- ===================================
-- PRODUCT NAME (multilingual)
-- ===================================
-- changeset Thulasithsan:v1-create-product-names-table
CREATE TABLE IF NOT EXISTS product_names (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    language VARCHAR(10) NOT NULL,
    name TEXT NOT NULL,
    CONSTRAINT fk_product_name_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT uq_product_language UNIQUE (product_id, language)
);
-- rollback drop table product_names;


-- ===================================
-- STOCK
-- ===================================
-- changeset Thulasithsan:v1-create-stock-table
CREATE TABLE IF NOT EXISTS stocks (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    quantity NUMERIC(15,2) NOT NULL,
    reserved_quantity NUMERIC(15,2) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now(),

    CONSTRAINT fk_stock_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
-- rollback drop table stocks;

-- ===================================
-- PURCHASE BILL
-- ===================================
-- changeset Thulasithsan:v1-create-purchase-bill-table
CREATE TABLE IF NOT EXISTS purchase_bills (
    id BIGSERIAL PRIMARY KEY,
    supplier_name VARCHAR(255) NOT NULL,
    bill_number VARCHAR(255) NOT NULL UNIQUE,
    bill_date DATE NOT NULL,
    total_amount NUMERIC(15, 2) NOT NULL,
    bill_image_url VARCHAR(1024),
    note TEXT,
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now()
);
-- rollback drop table purchase_bills;


-- ===================================
-- STOCK INCOME
-- ===================================
-- changeset Thulasithsan:v1-create-stock-income-table
CREATE TABLE IF NOT EXISTS stock_incomes (
    id BIGSERIAL PRIMARY KEY,
    stock_id BIGINT NOT NULL,
    purchase_bill_id BIGINT,
    quantity NUMERIC(15, 2) NOT NULL,
    available_quantity NUMERIC(15, 2) NOT NULL,
    unit_buying_price NUMERIC(15, 2) NOT NULL,
    total_buying_amount NUMERIC(15, 2) NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now(),

    CONSTRAINT fk_stock_income_stock FOREIGN KEY (stock_id) REFERENCES stocks(id) ON DELETE CASCADE,
    CONSTRAINT fk_stock_income_purchase_bill FOREIGN KEY (purchase_bill_id) REFERENCES purchase_bills(id) ON DELETE CASCADE
);
-- rollback drop table stock_incomes;


-- ===================================
-- SALES_ORDER
-- ===================================
-- changeset Thulasithsan:v1-create-sales_order-table
CREATE TABLE IF NOT EXISTS sales_orders (
    id BIGSERIAL PRIMARY KEY,
    order_ref_no VARCHAR(255) NOT NULL,
    customer_id BIGINT,
    sales_person_id BIGINT,
    delivery_person_id BIGINT,
    total_buying_amount NUMERIC(15,2) NOT NULL,
    total_selling_amount NUMERIC(15,2) NOT NULL,
    total_profit_amount NUMERIC(15,2) NOT NULL,
    total_quantity NUMERIC(15,2),
    total_weight NUMERIC(15,2),
    status VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now(),
    CONSTRAINT fk_sales_order_customer FOREIGN KEY (customer_id) REFERENCES users(id),
    CONSTRAINT fk_sales_order_sales_person FOREIGN KEY (sales_person_id) REFERENCES users(id),
    CONSTRAINT fk_sales_order_delivery_person FOREIGN KEY (delivery_person_id) REFERENCES users(id)
);
-- rollback drop table sales_orders;

-- ===================================
-- SALES_ORDER_ITEM
-- ===================================
-- changeset Thulasithsan:v1-create-sales_order_item-table
CREATE TABLE IF NOT EXISTS sales_order_items (
    id BIGSERIAL PRIMARY KEY,
    sales_order_id BIGINT NOT NULL,
    stock_id BIGINT NOT NULL,
    quantity NUMERIC(15,2) NOT NULL,
    product_unit VARCHAR(50) NOT NULL,
    unit_buying_price_details TEXT,
    unit_selling_price NUMERIC(15,2) NOT NULL,
    total_buying_amount NUMERIC(15,2) NOT NULL,
    total_selling_amount NUMERIC(15,2) NOT NULL,
    total_profit_amount NUMERIC(15,2) NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now(),
    CONSTRAINT fk_sales_order_item_order FOREIGN KEY (sales_order_id) REFERENCES sales_orders(id),
    CONSTRAINT fk_sales_order_item_stock FOREIGN KEY (stock_id) REFERENCES stocks(id)
);
-- rollback drop table sales_order_items;

-- ===================================
-- STOCK_OUTGOING
-- ===================================
-- changeset Thulasithsan:v1-create-stock_outgoing-table
CREATE TABLE IF NOT EXISTS stock_outgoings (
    id BIGSERIAL PRIMARY KEY,
    stock_id BIGINT NOT NULL,
    sales_order_id BIGINT,
    quantity NUMERIC(15,2) NOT NULL,
    unit_buying_price NUMERIC(15,2) NOT NULL,
    unit_selling_price NUMERIC(15,2) NOT NULL,
    total_buying_amount NUMERIC(15,2) NOT NULL,
    total_selling_amount NUMERIC(15,2) NOT NULL,
    profit_amount NUMERIC(15,2) NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now(),

    CONSTRAINT fk_stock_outgoing_stock FOREIGN KEY (stock_id) REFERENCES stocks(id) ON DELETE CASCADE,
    CONSTRAINT fk_stock_outgoing_sales_order FOREIGN KEY (sales_order_id) REFERENCES sales_orders(id) ON DELETE SET NULL
);
-- rollback drop table stock_outgoings;

-- ===================================
-- PRICE
-- ===================================
-- changeset Thulasithsan:v1-create-price-table
CREATE TABLE IF NOT EXISTS prices (
    id BIGSERIAL PRIMARY KEY,
    stock_id BIGINT NOT NULL,
    quantity NUMERIC(15,2) NOT NULL,
    unit VARCHAR(50) NOT NULL,
    actual_price NUMERIC(15,2) NOT NULL,
    discount_percent NUMERIC(5,2) NOT NULL,
    discount_amount NUMERIC(15,2) NOT NULL,
    final_price NUMERIC(15,2) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    is_default BOOLEAN NOT NULL DEFAULT FALSE,
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now(),
    CONSTRAINT fk_price_stock FOREIGN KEY (stock_id) REFERENCES stocks(id) ON DELETE CASCADE
);
-- rollback drop table prices;

-- ===================================
-- INVOICE
-- ===================================
-- changeset Thulasithsan:v1-create-invoice-table
CREATE TABLE IF NOT EXISTS invoices (
    id BIGSERIAL PRIMARY KEY,
    invoice_ref_no VARCHAR(255) NOT NULL,
    sales_order_id BIGINT NOT NULL UNIQUE,
    invoice_amount NUMERIC(15,2) NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now(),

    CONSTRAINT fk_invoice_sales_order FOREIGN KEY (sales_order_id) REFERENCES sales_orders(id)
);
-- rollback drop table invoices;

-- ===================================
-- PAYMENT
-- ===================================
-- changeset Thulasithsan:v1-create-payment-table
CREATE TABLE IF NOT EXISTS payments (
    id BIGSERIAL PRIMARY KEY,
    payment_ref_no VARCHAR(255) NOT NULL,
    invoice_id BIGINT NOT NULL,
    payment_amount NUMERIC(15,2) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    payment_status VARCHAR(50) NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now(),
    CONSTRAINT fk_payment_invoice FOREIGN KEY (invoice_id) REFERENCES invoices(id)
);
-- rollback drop table payments;

-- ===================================
-- CARD ITEM
-- ===================================
-- changeset Thulasithsan:v1-create-card-item-table
CREATE TABLE IF NOT EXISTS card_items (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    quantity NUMERIC(15, 2) NOT NULL DEFAULT 1,
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now(),
    CONSTRAINT fk_card_item_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
-- rollback drop table card_items;

-- ===================================
-- FAVOURITE ITEM
-- ===================================
-- changeset Thulasithsan:v1-create-favourite-item-table
CREATE TABLE IF NOT EXISTS favourite_items (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now(),
    CONSTRAINT fk_favourite_item_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
-- rollback drop table favourite_items;

-- ===================================
-- RECURRING ORDER
-- ===================================
-- changeset Thulasithsan:v1-create-recurring-order-table
CREATE TABLE IF NOT EXISTS recurring_orders (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    note TEXT,
    user_id BIGINT NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMPTZ DEFAULT now(),
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMPTZ DEFAULT now()
);
-- rollback drop table recurring_orders;

-- ===================================
-- RECURRING ORDER ITEM
-- ===================================
-- changeset Thulasithsan:v1-create-recurring-order-item-table
CREATE TABLE IF NOT EXISTS recurring_order_items (
    id BIGSERIAL PRIMARY KEY,
    recurring_order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity NUMERIC(15, 2) NOT NULL,
    CONSTRAINT fk_recurring_order_item_order FOREIGN KEY (recurring_order_id) REFERENCES recurring_orders(id) ON DELETE CASCADE,
    CONSTRAINT fk_recurring_order_item_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
-- rollback drop table recurring_order_items;


-- ===================================
-- DATA INSERTIONS
-- ===================================

-- Insert Roles
-- changeset Thulasithsan:v1-insert-initial-roles
INSERT INTO roles (name, description) VALUES
('SUPER_ADMIN', 'Full access to all system features'),
('ADMIN', 'Administrative access'),
('USER', 'Regular user access');
-- rollback DELETE FROM roles WHERE name IN ('SUPER_ADMIN', 'ADMIN', 'USER');

-- Insert Permissions
-- changeset Thulasithsan:v1-insert-initial-permissions
INSERT INTO permissions (name, description) VALUES
('Challenges', 'Challenge management'),
('User', 'User management');
-- rollback DELETE FROM permissions WHERE name IN ('Challenges', 'User');

-- Assign Permissions to SUPER_ADMIN with sub_permissions
-- changeset Thulasithsan:v1-assign-superadmin-subpermissions
INSERT INTO role_permissions (role_id, permission_id, sub_permissions)
SELECT r.id, p.id, '["edit", "view", "delete", "create"]'::TEXT
FROM roles r, permissions p
WHERE r.name = 'SUPER_ADMIN' AND p.name IN ('Challenges', 'User');
-- rollback DELETE FROM role_permissions WHERE role_id = (SELECT role_id FROM roles WHERE name = 'SUPER_ADMIN');


-- Create Super Admin User
-- changeset Thulasithsan:v1-create-superadmin-user
INSERT INTO users (first_name, last_name, email, password, role_id, is_active, created_by, last_modified_by)
SELECT 'Super', 'Admin', 'superadmin@example.com',
       '$2a$12$ddWF0ZANd9pPl0QeQCtY7eiRKSfxIP3PADGjjlO3a1m1GV5Qb.q2S',
       r.id, TRUE, 'system', 'system'
FROM roles r WHERE r.name = 'SUPER_ADMIN';
-- rollback DELETE FROM users WHERE email = 'superadmin@example.com';
