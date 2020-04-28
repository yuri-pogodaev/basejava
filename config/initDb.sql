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