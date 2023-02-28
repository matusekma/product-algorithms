--liquibase formatted sql
--changeset matusekma:create_calculation_history_entries_table
create table calculation_history_entries
(
    id             serial primary key,
    algorithm_type varchar(5) not null,
    comment        text not null,
    input          integer[],
    result         integer[],
    timestamp      timestamp with time zone not null,
    created_at     timestamp with time zone not null,
    updated_at     timestamp with time zone not null
)
--rollback DROP TABLE calculation_history_entries