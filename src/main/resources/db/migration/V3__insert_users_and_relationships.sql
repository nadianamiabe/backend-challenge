insert into application_user(id, email, document_id, full_name, password, created_date)
values ('85c0cc96-0dd4-4d61-827f-4b05c0885f49', 'maria@email.com', '17336696040', 'Maria da Silva', '$2a$12$zhKjIsObIb.7hyge3vDJj.NhK1XmD9jj2iKSkemtJsEdWE20RSLq2', '2023-05-24 08:57:00.0');

insert into application_user(id, email, document_id, full_name, password, created_date)
values ('2cf4ff1c-0879-4a03-8ab1-8139ed277322', 'joao@email.com', '43990029053', 'Joao da Silva', '$2a$12$GgNICYtcF8uS0SnKQUEb0uaj5eXjDser4ssBvaqF7n7frSCVcsiNC', '2023-05-24 08:57:00.0');


insert into application_user_roles(application_user_id, roles_name)
values ('85c0cc96-0dd4-4d61-827f-4b05c0885f49', 'COMMON_USER');

insert into application_user_roles(application_user_id, roles_name)
values ('2cf4ff1c-0879-4a03-8ab1-8139ed277322', 'SHOPKEEPER_USER');


insert into wallet(id, balance, application_user_id)
values ('ae2acfef-4ba4-405b-bc7f-9c94b664d86b', '1000.00', '85c0cc96-0dd4-4d61-827f-4b05c0885f49');

insert into wallet(id, balance, application_user_id)
values ('548658ed-6365-4336-8367-ea0f0be78459', '2000.00', '2cf4ff1c-0879-4a03-8ab1-8139ed277322');