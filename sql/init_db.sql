DROP TABLE IF EXISTS public.employer;
DROP TABLE IF EXISTS public.authorities;

CREATE TABLE public.users (
                              id serial NOT NULL PRIMARY KEY,
                              name VARCHAR(200) NOT NULL,
                              email VARCHAR(200) NOT NULL,
                              password VARCHAR(200) NOT NULL
);

create table public.authorities (
                            id serial NOT NULL PRIMARY KEY,
                            user_id INT not null,
                            authority VARCHAR(50) not null,
                            constraint fk_authorities_users foreign key(user_id) references users(id)
);


INSERT INTO users (name, email, password)
VALUES ('a', 'a', '$e0804$Gv0z3n4ujRCBES/uOnuH2OrDDv+MciIaiTkhR7osNMGO0jqqRfQ6Ffw8qSX34SJeKk19DlR7mvW3++2nZsWbCw==$M9zK0oLzuXvYKraIdGZYw3weoho0GRVtDQVS1FcDYQI=')
;
INSERT INTO public.authorities (user_id, authority)
VALUES (1, 'ROLE_USER')
