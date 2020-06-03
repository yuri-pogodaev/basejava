create table resume
(
    uuid      char(36) PRIMARY KEY NOT NULL,
    full_name text                 NOT NULL
);

create table contact
(
    id          SERIAL,
    type        text     NOT NULL,
    value       text     NOT NULL,
    resume_uuid char(36) NOT NULL references resume (uuid) ON DELETE CASCADE
);
create unique index contact_uuid_type_index
    on contact (resume_uuid, type);
create table section
(
    id serial not null
        constraint section_pk
            primary key,
    resume_uuid varchar(36) not null
        constraint section_resume_uuid_fk
            references resume
            on delete cascade,
    type text not null,
    content text not null
);

alter table section owner to postgres;