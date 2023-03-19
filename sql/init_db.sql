DROP TABLE IF EXISTS public.users;
DROP TABLE IF EXISTS public.authorities;
DROP TABLE IF EXISTS public.jobs;

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

CREATE TABLE public.jobs(
    id SERIAL NOT NULL PRIMARY KEY,
    user_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    technical_requirements VARCHAR(1000) NOT NULL,
    responsibilities VARCHAR(1000) NOT NULL,
    who_we_are_looking_for VARCHAR(1000) NOT NULL,
    benefits VARCHAR(1000) NOT NULL,
    city VARCHAR(500) NOT NULL,
    address VARCHAR(1000) NOT NULL,
    salary DECIMAL NOT NULL,
    currency VARCHAR(200) DEFAULT 'USD',
    posting_date DATE NOT NULL DEFAULT CURRENT_DATE
);


INSERT INTO users (name, email, password)
VALUES ('a', 'a', '$e0804$Gv0z3n4ujRCBES/uOnuH2OrDDv+MciIaiTkhR7osNMGO0jqqRfQ6Ffw8qSX34SJeKk19DlR7mvW3++2nZsWbCw==$M9zK0oLzuXvYKraIdGZYw3weoho0GRVtDQVS1FcDYQI=')
;
INSERT INTO public.authorities (user_id, authority)
VALUES (1, 'ROLE_USER')
;
INSERT INTO public.jobs (user_id, title, description, technical_requirements, responsibilities, who_we_are_looking_for, benefits, city, address, salary)
VALUES (1,'title', 'description', 'tech req', 'resp', 'who we are looking for?', 'benefits', 'city', 'adress', 1000)

