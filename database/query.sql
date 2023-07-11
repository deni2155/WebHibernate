--создаю БД
CREATE DATABASE medo ENCODING 'UTF-8';

--таблица с УЗ пользователей
CREATE TABLE public.users (
	login varchar(50) NULL,
	hash varchar(1500) NULL,
	fname varchar(100) NULL,
	id_user serial NOT NULL
);
COMMENT ON TABLE public.accounts IS 'УЗ пользователей';

-- Column comments

COMMENT ON COLUMN public.accounts.login IS 'Логин для входа пользователя';
COMMENT ON COLUMN public.accounts.hash IS 'Хэш пароля пользователя';
COMMENT ON COLUMN public.accounts.fname IS 'ФИО пользователя';
COMMENT ON COLUMN public.accounts.id_account IS 'Идентификатор УЗ пользователя';

INSERT INTO public.accounts (login, hash, fname, id_account) VALUES('admin', '$2a$12$VvHg0jN6n5v7GAP6sZUMVOeRoxxBpXuwDUxJX/PWomnLIeF1xyLRG', 'Администрастор', 1);
--Таблица с участниками МЭДО
CREATE TABLE public.guides (
	id_guid smallserial NOT NULL,
	name_org varchar(250) NULL,
	guid varchar(40) NULL,
	addressee varchar(50) NULL
);

-- Column comments

COMMENT ON COLUMN public.guides.id_guid IS 'Идентификатор участника МЭДО';
COMMENT ON COLUMN public.guides.name_org IS 'Наименование организации';
COMMENT ON COLUMN public.guides.guid IS 'Идентификатор участника';
COMMENT ON COLUMN public.guides.addressee IS 'Адрес получателя';


