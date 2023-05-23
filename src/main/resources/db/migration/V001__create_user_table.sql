create table allow_notificatio
(
    id                bigint auto_increment
        primary key,
    allow_button_text varchar(255) null,
    deny_button_text  varchar(255) null,
    message_text      varchar(255) null
);

create table site
(
    id       bigint auto_increment
        primary key,
    address  varchar(255) null,
    name     varchar(255) null,
    url_icon varchar(255) null
);

create table user
(
    id              bigint auto_increment
        primary key,
    company_address varchar(255) null,
    company_name    varchar(255) null,
    email           varchar(255) null,
    name            varchar(255) null,
    password        varchar(255) null,
    phone_number    varchar(255) null,
    constraint email_index_user_constrain
        unique (email)
);

create table app
(
    id      bigint auto_increment
        primary key,
    name    varchar(255) null,
    token   varchar(255) null,
    user_id bigint       null,
    constraint user_id_fk
        foreign key (user_id) references user (id)
);

create table notification_config
(
    id      bigint auto_increment
        primary key,
    channel varchar(255) null,
    enabled bit          not null,
    app_id  bigint       null,
    constraint channel_app_id_notification_config_uniq_constrain
        unique (channel, app_id),
    constraint app_id_notification_config_notification_config_constrain
        foreign key (app_id) references app (id)
);

create table welcome_notification
(
    id                  bigint auto_increment
        primary key,
    enable_url_redirect int          null,
    message_text        varchar(255) null,
    message_title       varchar(255) null,
    url_redirect        varchar(255) null
);

create table webpush
(
    id                      bigint auto_increment
        primary key,
    allow_notification_id   bigint null,
    app_id                  bigint null,
    site_id                 bigint null,
    welcome_notification_id bigint null,
    constraint site_id_fk_webpush_constrain
        foreign key (site_id) references site (id),
    constraint app_id_fk_webpush_constrain
        foreign key (app_id) references app (id),
    constraint welcome_notification_id_fk_webpush_constrain
        foreign key (welcome_notification_id) references welcome_notification (id),
    constraint allow_notification_id_fk_webpush_constrain
        foreign key (allow_notification_id) references allow_notificatio (id)
);

