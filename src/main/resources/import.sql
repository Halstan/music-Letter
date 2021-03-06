insert into roles(fecha_actualizacion, fecha_creacion, nombre) VALUES (now(), now(), 'ROLE_ADMIN');
insert into roles(fecha_actualizacion, fecha_creacion, nombre) VALUES (now(), now(), 'ROLE_USER');

insert into usuarios(fecha_actualizacion, fecha_creacion, apellidos, contrasenha, correo, estado, nombre_de_usuario, nombres, id_rol)VALUES (now(), now(), 'Arauco Belahonia', '$2a$10$KyLJWHy7EvgAy2WrYX4ie.AjUGJGfwg1M7daQXmirPdNT1uu.kVku', 'enzoarauco@gmail.com', true, 'halstan', 'Enzo Daniel', 1) ;

create table if not exists oauth_client_details (client_id VARCHAR(256) PRIMARY KEY,resource_ids VARCHAR(256),client_secret VARCHAR(256),scope VARCHAR(256),authorized_grant_types VARCHAR(256),web_server_redirect_uri VARCHAR(256),authorities VARCHAR(256),access_token_validity INTEGER,refresh_token_validity INTEGER,additional_information VARCHAR(4096),autoapprove VARCHAR(256));

create table if not exists oauth_client_token (token_id VARCHAR(256),token bytea,authentication_id VARCHAR(256) PRIMARY KEY,user_name VARCHAR(256),client_id VARCHAR(256));

create table if not exists oauth_access_token (token_id VARCHAR(256),token bytea,authentication_id VARCHAR(256) PRIMARY KEY,user_name VARCHAR(256),client_id VARCHAR(256),authentication bytea,refresh_token VARCHAR(256));

create table if not exists oauth_refresh_token (token_id VARCHAR(256),token bytea,authentication bytea);

create table if not exists oauth_code (code VARCHAR(256), authentication bytea);

create table if not exists oauth_approvals (userId VARCHAR(256),clientId VARCHAR(256),scope VARCHAR(256),status VARCHAR(10),expiresAt TIMESTAMP,lastModifiedAt TIMESTAMP);

--customized oauth_client_details table
create table if not exists ClientDetails (appId VARCHAR(256) PRIMARY KEY,resourceIds VARCHAR(256),appSecret VARCHAR(256),scope VARCHAR(256),grantTypes VARCHAR(256),redirectUrl VARCHAR(256),authorities VARCHAR(256),access_token_validity INTEGER,refresh_token_validity INTEGER,additionalInformation VARCHAR(4096),autoApproveScopes VARCHAR(256));

insert into oauth_client_details(resource_ids, client_id, client_secret, scope, authorized_grant_types,
                                 web_server_redirect_uri, authorities, access_token_validity,
                                 refresh_token_validity, additional_information, autoapprove)
                                 values ('letterid',
                                         'vueApp',
                                         '$2a$10$kkeajKPMB6O/jN1o6ZJgtuERUl.z4Pa8/oiHUkAlOlZkIM3k1cg26',
                                         'read,write',
                                         'password,refresh_token',
                                         null,
                                         null,
                                         10800,
                                         7200,
                                         null,
                                         true);