-- auto-generated definition
create table customer
(
    id           integer not null
        constraint customer_pk
            primary key autoincrement,
    email        text    not null,
    phone_number text(9),
    password     text
);

create unique index customer_id_uindex
    on customer (id);

-- auto-generated definition
create table business_customer
(
    customer_ptr_id integer not null
        constraint business_customer_pk
            primary key
        references customer,
    nip             text(10),
    regon           text(11),
    name            text
);

create unique index business_customer_customer_ptr_id_uindex
    on business_customer (customer_ptr_id);

-- auto-generated definition
create table individual_customer
(
    customer_ptr_id integer  not null
        constraint individual_customer_pk
            primary key
        references customer,
    first_name      text     not null,
    last_name       text     not null,
    pesel           text(11) not null
);

create unique index individual_customer_customer_ptr_id_uindex
    on individual_customer (customer_ptr_id);

-- auto-generated definition
create table customer_address
(
    id          integer not null
        constraint customer_address_pk
            primary key autoincrement
        references address,
    customer_id integer not null,
    type        text    not null
);

create unique index customer_address_id_uindex
    on customer_address (id);

-- auto-generated definition
create table address
(
    id               integer not null
        constraint address_pk
            primary key autoincrement,
    prefix           text    not null,
    street_name      text    not null,
    building_number  text    not null,
    apartment_number text,
    zip_code         text(5) not null,
    city_name        text    not null
);

create unique index address_id_uindex
    on address (id);

-- auto-generated definition
create table contract
(
    id          integer not null
        constraint contract_pk
            primary key autoincrement,
    customer_id integer
        references customer
);

create unique index contract_id_uindex
    on contract (id);

-- auto-generated definition
create table post_paid_contract
(
    contract_ptr_id         integer not null
        constraint post_paid_contract_pk
            primary key
        references contract,
    sms_sent                int     not null,
    phone_minutes_used      int     not null,
    internet_megabytes_used int     not null
);

create unique index post_paid_contract_contract_ptr_id_uindex
    on post_paid_contract (contract_ptr_id);

-- auto-generated definition
create table pre_paid_contract
(
    contract_ptr_id          integer not null
        constraint pre_paid_contract_pk
            primary key
        references contract,
    sms_count                int     not null,
    phone_minutes_count      int,
    internet_megabytes_count int,
    contract_money           int     not null
);

create unique index pre_paid_contract_contract_ptr_id_uindex
    on pre_paid_contract (contract_ptr_id);

-- auto-generated definition
create table post_paid_prices
(
    id                                     integer not null
        constraint post_paid_prices_pk
            primary key,
    contract_price                         int     not null,
    sms_count_in_price                     int     not null,
    phone_minutes_in_price                 int     not null,
    internet_megabytes_in_price            int     not null,
    sms_above_contract_price               int     not null,
    phone_minute_above_contract_price      int     not null,
    internet_megabyte_above_contract_price int     not null
);

create unique index post_paid_prices_id_uindex
    on post_paid_prices (id);

-- auto-generated definition
create table pre_paid_prices
(
    id                      integer not null
        constraint pre_paid_prices_pk
            primary key,
    sms_price               int     not null,
    phone_minute_price      int     not null,
    internet_megabyte_price int     not null
);

create unique index pre_paid_prices_id_uindex
    on pre_paid_prices (id);