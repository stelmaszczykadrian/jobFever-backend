-- employers
WITH inserted_employers AS (
    INSERT INTO employers (company_name, name_and_surname, phone_number, email, about_us, why_worth_working_with_us, localization, nip, linkedin, role_type, img_file_name)
        VALUES
            ('ACME Corp', 'John Doe', 123456789, 'johndoe@example.com', 'We are a software development company specializing in web applications and mobile apps.', 'We offer a great work-life balance and opportunities for growth within the company.', 'Cracow', 1111111111, '/acme-corp', 'EMPLOYER', 'looogo.png'),
            ('FULLSTACK Corp', 'Jane Smith', 987654321, 'janesmith@example.com', 'We are a technology consulting firm with expertise in data analytics and cloud solutions.', 'We offer flexible work hours and a collaborative team environment.', 'Berlin', 2222222222, '/fullstack-corp', 'EMPLOYER', 'looogo.png'),
            ('WEB Inc', 'Bob Johnson', 555555555, 'bjohnson@example.com', 'We are a software company specializing in enterprise resource planning (ERP) systems.', 'We provide great benefits and opportunities for professional development.', 'Warsaw', 3333333333, '/web-inc', 'EMPLOYER', 'looogo.png'),
            ('BACKEND Corp', 'Lisa Wang', 111222333, 'lisawang@example.com', 'We are a startup developing cutting-edge artificial intelligence (AI) software.', 'We offer a dynamic and challenging work environment for those passionate about AI.', 'London', 4444444444, '/backend-corp', 'EMPLOYER', 'looogo.png'),
            ('FRONT LLC', 'David Lee', 111888777, 'davidlee@example.com', 'We are a software development company creating mobile apps and custom software solutions.', 'We provide a supportive and fun work culture with opportunities for creativity and innovation.', 'Chicago', 5555555555, '/front-lcc', 'EMPLOYER', 'looogo.png')
        RETURNING id, email
)
INSERT INTO users (email, password, role_type, employer_id)
SELECT
    e.email,
    '$2a$10$.I0QYwo1jNRPwmY8YjhOauPErcNC041FjFOFmN8U876qzphEas.JO',
    'EMPLOYER',
    e.id
FROM inserted_employers e;

-- EMPLOYER 5 job 5
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '8 days',
           (SELECT id FROM employers WHERE company_name = 'FRONT LLC' AND name_and_surname = 'David Lee' LIMIT 1),
           'JavaScript Developer',
           'FRONT LLC is seeking a talented JavaScript Developer to join our frontend development team. As a JavaScript Developer, you will be responsible for designing and implementing user interfaces using modern JavaScript frameworks.',
           'Your responsibilities will include writing clean and maintainable code, conducting thorough testing, optimizing application performance, and collaborating with cross-functional teams to deliver high-quality frontend solutions.',
           'We are looking for a skilled developer with expertise in JavaScript and frontend development. Familiarity with frameworks like React or Angular, and knowledge of HTML, CSS, and related technologies is highly desired.',
           'FRONT LLC offers competitive compensation, a supportive work culture, and opportunities to work on diverse frontend projects. Join our team and contribute to the development of innovative web applications.',
           'Chicago',
           5500.0,
           9000.0,
           'EMPLOYMENT',
           'DOLLAR',
           'ONSITE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'FRONT LLC' AND name_and_surname = 'David Lee'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'JS');

-- EMPLOYER 4 job 5
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '9 days',
           (SELECT id FROM employers WHERE company_name = 'BACKEND Corp' AND name_and_surname = 'Lisa Wang' LIMIT 1),
           'PHP Backend Developer',
           'BACKEND Corp is seeking a skilled PHP backend developer to join our team. As a PHP backend developer, you will be responsible for designing, developing, and maintaining backend systems using PHP and related frameworks.',
           'Your responsibilities will include implementing APIs, optimizing database queries, and integrating with external services.',
           'We are looking for a talented developer with expertise in PHP and backend frameworks such as Laravel or Symfony. Proficiency in database systems and experience in building scalable web applications are essential.',
           'BACKEND Corp offers competitive compensation, a challenging work environment, and opportunities to work with modern backend technologies. Join our team and contribute to the development of innovative PHP-based backend solutions.',
           'London',
           5500.0,
           9000.0,
           'EMPLOYMENT',
           'EURO',
           'REMOTE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'BACKEND Corp' AND name_and_surname = 'Lisa Wang'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'PHP');

-- EMPLOYER 3 job 5
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '9 days',
           (SELECT id FROM employers WHERE company_name = 'WEB Inc' AND name_and_surname = 'Bob Johnson' LIMIT 1),
           'Java Developer',
           'WEB Inc is seeking a skilled Java developer to join our team. As a Java developer, you will be responsible for designing, developing, and maintaining Java-based web applications.',
           'Your responsibilities will include writing clean and efficient code, collaborating with cross-functional teams, and ensuring the scalability and performance of Java applications.',
           'We are looking for a talented Java developer with expertise in Java EE or Spring framework. Proficiency in HTML, CSS, and JavaScript is also required. Familiarity with database systems and RESTful APIs is a plus.',
           'WEB Inc offers competitive compensation, a collaborative work environment, and opportunities for professional growth. Join our team and contribute to the development of Java-based web solutions.',
           'Warsaw',
           5000.0,
           8000.0,
           'EMPLOYMENT',
           'DOLLAR',
           'ONSITE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'WEB Inc' AND name_and_surname = 'Bob Johnson'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Java');


-- EMPLOYER 2 job 5
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '10 days',
           (SELECT id FROM employers WHERE company_name = 'FULLSTACK Corp' AND name_and_surname = 'Jane Smith' LIMIT 1),
           'Full Stack Web Developer',
           'FULLSTACK Corp is looking for a talented full stack web developer with expertise in PHP and Ruby. As a full stack developer, you will work on developing and maintaining web applications using these technologies.',
           'Your responsibilities will include designing and implementing database structures, developing server-side logic, and creating responsive and user-friendly web interfaces.',
           'We are seeking a highly skilled full stack web developer with experience in PHP and Ruby on Rails. Proficiency in HTML, CSS, JavaScript, and front-end frameworks is also required. Knowledge of SQL and database design principles is a plus.',
           'FULLSTACK Corp offers a flexible work environment, challenging projects, and opportunities for growth. Join our team and contribute to the development of dynamic web solutions using PHP and Ruby.',
           'Berlin',
           8000.0,
           10000.0,
           'EMPLOYMENT',
           'EURO',
           'REMOTE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'FULLSTACK Corp' AND name_and_surname = 'Jane Smith'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'PHP');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Ruby');


-- EMPLOYER 1 job 5
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '10 days',
           (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe' LIMIT 1),
           'UX Designer',
           'ACME Corp is looking for a talented UX designer to join our team. As a UX designer, you will play a crucial role in creating intuitive and engaging user experiences for our web and mobile applications.',
           'Your responsibilities will include conducting user research, creating wireframes and prototypes, and collaborating with cross-functional teams to deliver exceptional design solutions.',
           'We are seeking a creative and detail-oriented UX designer with a strong portfolio showcasing user-centered design solutions. The ideal candidate should have expertise in design tools such as Sketch or Figma, knowledge of UX best practices, and the ability to translate complex concepts into simple and elegant designs.',
           'ACME Corp offers competitive compensation, a supportive work environment, and opportunities to work on exciting projects. Join our team and contribute to the development of user-centric digital experiences.',
           'Cracow',
           5000.0,
           7000.0,
           'B2B',
           'PLN',
           'ONSITE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'JS');


-- EMPLOYER 5 job 4
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '10 days',
           (SELECT id FROM employers WHERE company_name = 'FRONT LLC' AND name_and_surname = 'David Lee' LIMIT 1),
           'Python Developer',
           'FRONT LLC is seeking a talented Python Developer to join our frontend development team. As a Python Developer, you will be responsible for implementing and maintaining frontend components using Python and related technologies.',
           'Your responsibilities will include writing clean and efficient code, collaborating with designers and backend developers, optimizing application performance, and delivering high-quality frontend solutions.',
           'We are looking for a skilled developer with expertise in Python and a strong understanding of frontend development. Familiarity with frontend frameworks like React or Angular and knowledge of HTML, CSS, and JavaScript is highly desired.',
           'FRONT LLC offers competitive compensation, a supportive work culture, and opportunities to work on diverse frontend projects. Join our team and contribute to the development of innovative web applications.',
           'Chicago',
           6500.0,
           8800.0,
           'EMPLOYMENT',
           'DOLLAR',
           'REMOTE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'FRONT LLC' AND name_and_surname = 'David Lee'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Python');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'JS');

-- EMPLOYER 4 job 4
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '10 days',
           (SELECT id FROM employers WHERE company_name = 'BACKEND Corp' AND name_and_surname = 'Lisa Wang' LIMIT 1),
           'C# Backend Developer',
           'BACKEND Corp is looking for a skilled C# backend developer to join our team. As a C# backend developer, you will be responsible for designing and developing efficient and scalable backend systems using C# and related technologies.',
           'Your responsibilities will include building APIs, optimizing database performance, and integrating with external services.',
           'We are seeking a talented developer with expertise in C# and backend frameworks such as ASP.NET or Entity Framework. Knowledge of SQL and experience in building RESTful APIs and microservices is essential.',
           'BACKEND Corp offers competitive compensation, a dynamic work environment, and opportunities for professional growth. Join our team and contribute to the development of robust backend solutions using C#.',
           'London',
           6000.0,
           10000.0,
           'EMPLOYMENT',
           'DOLLAR',
           'HYBRID'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'BACKEND Corp' AND name_and_surname = 'Lisa Wang'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'C#');


-- EMPLOYER 3 job 4
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '10 days',
           (SELECT id FROM employers WHERE company_name = 'WEB Inc' AND name_and_surname = 'Bob Johnson' LIMIT 1),
           'JavaScript Full Stack Developer',
           'WEB Inc is looking for a talented JavaScript full stack developer to join our team. As a full stack developer, you will work on developing and maintaining web applications using JavaScript and related technologies.',
           'Your responsibilities will include designing and implementing database structures, developing server-side logic, and creating responsive and user-friendly web interfaces.',
           'We are seeking a highly skilled full stack developer with expertise in JavaScript frameworks such as React or Angular for the front end, and Node.js or Express.js for the back end. Proficiency in HTML, CSS, and database systems is also required.',
           'WEB Inc offers a collaborative work environment, challenging projects, and opportunities for professional growth. Join our team and contribute to the development of dynamic web solutions using JavaScript.',
           'Warsaw',
           6000.0,
           10000.0,
           'EMPLOYMENT',
           'PLN',
           'REMOTE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'WEB Inc' AND name_and_surname = 'Bob Johnson'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'JS');


-- EMPLOYER 2 job 4
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '11 days',
           (SELECT id FROM employers WHERE company_name = 'FULLSTACK Corp' AND name_and_surname = 'Jane Smith' LIMIT 1),
           '.NET Developer',
           'FULLSTACK Corp is seeking a skilled .NET developer to join our team. As a .NET developer, you will be responsible for designing, developing, and maintaining robust and scalable applications using the .NET framework.',
           'Your responsibilities will include writing clean and efficient code, collaborating with cross-functional teams, and ensuring the performance and security of .NET applications.',
           'We are looking for a talented .NET developer with expertise in C# and experience with ASP.NET, MVC, or Entity Framework. Familiarity with front-end technologies such as HTML, CSS, and JavaScript is a plus.',
           'FULLSTACK Corp offers a collaborative work environment, opportunities for professional growth, and exposure to cutting-edge technologies. Join our team and contribute to the development of innovative .NET solutions.',
           'Berlin',
           6000.0,
           7000.0,
           'EMPLOYMENT',
           'EURO',
           'REMOTE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'FULLSTACK Corp' AND name_and_surname = 'Jane Smith'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), '.Net');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'C#');


-- EMPLOYER 1 job 4
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '12 days',
           (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe' LIMIT 1),
           'Full Stack Developer',
           'ACME Corp is seeking a talented full stack developer to join our team. As a full stack developer, you will have the opportunity to work on a wide range of projects, from developing web applications to implementing complex database systems.',
           'Your responsibilities will include designing and implementing server-side architecture, developing front-end interfaces, and collaborating with cross-functional teams to deliver high-quality software solutions.',
           'We are looking for a highly skilled full stack developer with expertise in both front-end and back-end development. The ideal candidate should have experience with modern web technologies, databases, and frameworks such as React, Node.js, and MongoDB.',
           'ACME Corp offers competitive compensation, a collaborative work environment, and opportunities for professional growth. Join our team and make a significant impact on our diverse projects.',
           'Cracow',
           9000.0,
           11000.0,
           'EMPLOYMENT',
           'PLN',
           'HYBRID'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Scala');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'SQL');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Java');


-- EMPLOYER 5 job 3
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '13 days',
           (SELECT id FROM employers WHERE company_name = 'FRONT LLC' AND name_and_surname = 'David Lee' LIMIT 1),
           'Swift Developer',
           'FRONT LLC is seeking a talented Swift Developer to join our frontend development team. As a Swift Developer, you will be responsible for designing and implementing iOS applications using the Swift programming language.',
           'Your responsibilities will include developing clean and efficient code, conducting unit tests, collaborating with designers and backend developers, and delivering high-quality mobile applications.',
           'We are looking for a skilled developer with expertise in Swift and a strong understanding of iOS development. Experience with frameworks like SwiftUI and UIKit is highly desired.',
           'FRONT LLC offers competitive compensation, a supportive work culture, and opportunities to work on exciting iOS projects. Join our team and contribute to the creation of innovative mobile applications.',
           'Chicago',
           5500.0,
           9000.0,
           'EMPLOYMENT',
           'DOLLAR',
           'ONSITE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'FRONT LLC' AND name_and_surname = 'David Lee'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Swift');


-- EMPLOYER 4 job 3
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '14 days',
           (SELECT id FROM employers WHERE company_name = 'BACKEND Corp' AND name_and_surname = 'Lisa Wang' LIMIT 1),
           'Ruby Backend Developer',
           'BACKEND Corp is seeking a talented Ruby backend developer to join our team. As a Ruby backend developer, you will be responsible for designing, developing, and maintaining backend systems using Ruby and related frameworks.',
           'Your responsibilities will include implementing APIs, optimizing database queries, and integrating with external services.',
           'We are looking for a skilled developer with expertise in Ruby and backend frameworks such as Ruby on Rails. Proficiency in database systems and experience in building scalable web applications are essential.',
           'BACKEND Corp offers competitive compensation, a challenging work environment, and opportunities to work with modern backend technologies. Join our team and contribute to the development of innovative Ruby-based backend solutions.',
           'London',
           5500.0,
           9000.0,
           'B2B',
           'EURO',
           'REMOTE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'BACKEND Corp' AND name_and_surname = 'Lisa Wang'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Ruby');


-- EMPLOYER 3 job 3
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '15 days',
           (SELECT id FROM employers WHERE company_name = 'WEB Inc' AND name_and_surname = 'Bob Johnson' LIMIT 1),
           'Python Web Developer',
           'WEB Inc is seeking a talented Python web developer to join our team. As a Python web developer, you will be responsible for designing, developing, and maintaining web applications using Python and related frameworks.',
           'Your responsibilities will include writing efficient and scalable code, collaborating with cross-functional teams, and integrating web applications with databases and APIs.',
           'We are looking for a skilled Python developer with experience in web development frameworks such as Django or Flask. Proficiency in HTML, CSS, and JavaScript is also required. Familiarity with database systems and RESTful APIs is a plus.',
           'WEB Inc offers competitive compensation, a collaborative work environment, and opportunities for professional growth. Join our team and contribute to the development of Python-based web solutions.',
           'Warsaw',
           5000.0,
           8000.0,
           'EMPLOYMENT',
           'PLN',
           'HYBRID'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'WEB Inc' AND name_and_surname = 'Bob Johnson'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'JS');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Python');


-- EMPLOYER 2 job 3
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '16 days',
           (SELECT id FROM employers WHERE company_name = 'FULLSTACK Corp' AND name_and_surname = 'Jane Smith' LIMIT 1),
           'DevOps Engineer',
           'FULLSTACK Corp is looking for a skilled DevOps engineer to join our team. As a DevOps engineer, you will play a crucial role in implementing and maintaining CI/CD pipelines, automating infrastructure deployment, and ensuring smooth software releases.',
           'Your responsibilities will include managing cloud infrastructure, optimizing system performance, and collaborating with development teams to improve development and deployment processes.',
           'We are seeking a talented DevOps engineer with expertise in cloud platforms, containerization technologies, and configuration management tools. Experience with DevOps best practices and familiarity with tools such as Docker, Kubernetes, and Jenkins is desirable.',
           'FULLSTACK Corp offers a flexible work environment, opportunities for professional growth, and exposure to the latest DevOps technologies. Join our team and contribute to streamlined software delivery processes.',
           'Berlin',
           6000.0,
           10000.0,
           'B2B',
           'EURO',
           'HYBRID'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'FULLSTACK Corp' AND name_and_surname = 'Jane Smith'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Python');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Java');


-- EMPLOYER 1 job 3
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '17 days',
           (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe' LIMIT 1),
           'Mobile App Developer',
           'ACME Corp is looking for a skilled mobile app developer to join our team. As a mobile app developer, you will be responsible for creating innovative and user-friendly mobile applications for iOS and Android platforms.',
           'Your responsibilities will include developing mobile applications from concept to deployment, collaborating with designers and product managers, and ensuring the performance and usability of the apps.',
           'We are seeking a highly motivated individual with a strong background in mobile app development. The ideal candidate should have experience with iOS and Android frameworks, knowledge of mobile UI/UX principles, and proficiency in programming languages such as Swift or Kotlin.',
           'ACME Corp offers competitive compensation, flexible work hours, and a supportive work environment. Join our team and contribute to the development of cutting-edge mobile applications.',
           'Cracow',
           8000.0,
           10000.0,
           'COMMISSION',
           'PLN',
           'REMOTE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Kotlin');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Java');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Swift');


-- EMPLOYER 5 job 2
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '18 days',
           (SELECT id FROM employers WHERE company_name = 'FRONT LLC' AND name_and_surname = 'David Lee' LIMIT 1),
           'JavaScript and Java Developer',
           'FRONT LLC is seeking a talented JavaScript and Java Developer to join our frontend development team. As a JavaScript and Java Developer, you will be responsible for developing and maintaining web applications using JavaScript frameworks and Java technologies.',
           'Your responsibilities will include implementing user interfaces, optimizing application performance, troubleshooting issues, and collaborating with cross-functional teams to deliver high-quality frontend solutions.',
           'We are looking for a skilled developer with expertise in JavaScript and Java. Familiarity with frontend frameworks like React or Angular and experience with Java frameworks such as Spring or Hibernate is highly desired.',
           'FRONT LLC offers competitive compensation, a supportive work culture, and opportunities to work on diverse frontend projects. Join our team and contribute to the development of innovative web applications.',
           'Chicago',
           7000.0,
           10000.0,
           'B2B',
           'DOLLAR',
           'ONSITE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'FRONT LLC' AND name_and_surname = 'David Lee'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'JS');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Java');


-- EMPLOYER 4 job 2
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '19 days',
           (SELECT id FROM employers WHERE company_name = 'BACKEND Corp' AND name_and_surname = 'Lisa Wang' LIMIT 1),
           'Java Backend Developer',
           'BACKEND Corp is looking for a skilled Java backend developer to join our team. As a Java backend developer, you will be responsible for designing and developing scalable and efficient backend systems.',
           'Your responsibilities will include implementing business logic, optimizing database queries, and integrating with external APIs and services.',
           'We are seeking a talented developer with expertise in Java and backend frameworks such as Spring or Hibernate. Knowledge of SQL and experience in building RESTful APIs and microservices is essential.',
           'BACKEND Corp offers competitive compensation, a dynamic work environment, and opportunities for professional growth. Join our team and contribute to the development of robust backend solutions.',
           'London',
           7000.0,
           10000.0,
           'EMPLOYMENT',
           'EURO',
           'ONSITE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'BACKEND Corp' AND name_and_surname = 'Lisa Wang'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Java');


-- EMPLOYER 3 job 2
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '20 days',
           (SELECT id FROM employers WHERE company_name = 'WEB Inc' AND name_and_surname = 'Bob Johnson' LIMIT 1),
           'Front-end Web Developer',
           'WEB Inc is looking for a talented front-end web developer to join our team. As a front-end developer, you will be responsible for implementing user interfaces and ensuring a seamless user experience for our web applications.',
           'Your responsibilities will include developing responsive web interfaces, collaborating with designers and back-end developers, and optimizing front-end performance.',
           'We are seeking a highly skilled front-end developer with expertise in HTML, CSS, JavaScript, and front-end frameworks such as React or Angular. Knowledge of UI/UX principles and experience in building user-friendly web applications is essential.',
           'WEB Inc offers a collaborative work environment, opportunities for professional growth, and exposure to exciting projects. Join our team and contribute to the development of innovative web solutions.',
           'Warsaw',
           7000.0,
           9000.0,
           'EMPLOYMENT',
           'PLN',
           'ONSITE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'WEB Inc' AND name_and_surname = 'Bob Johnson'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'JS');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Pascal');

-- EMPLOYER 2 job 2
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '21 days',
           (SELECT id FROM employers WHERE company_name = 'FULLSTACK Corp' AND name_and_surname = 'Jane Smith' LIMIT 1),
           'Front-end Developer',
           'FULLSTACK Corp is seeking a talented front-end developer to join our team. As a front-end developer, you will be responsible for implementing user interfaces and ensuring a seamless user experience.',
           'Your responsibilities will include developing responsive web applications, collaborating with designers and back-end developers, and optimizing front-end performance.',
           'We are looking for a skilled front-end developer with expertise in HTML, CSS, JavaScript, and modern front-end frameworks such as React or Vue.js. Experience in building accessible and responsive interfaces is a plus.',
           'FULLSTACK Corp offers a collaborative team environment, flexible work hours, and opportunities to work on diverse and challenging projects. Join our team and contribute to cutting-edge web development.',
           'Berlin',
           5000.0,
           8000.0,
           'EMPLOYMENT',
           'EURO',
           'ONSITE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'FULLSTACK Corp' AND name_and_surname = 'Jane Smith'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'JS');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Scala');


-- EMPLOYER 1 job 2
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '21 days',
           (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe' LIMIT 1),
           'Web Developer',
           'ACME Corp is seeking a talented web developer to join our team. As a web developer, you will be responsible for creating and maintaining web applications using cutting-edge technologies. Join us in shaping the digital landscape and delivering innovative solutions to our clients.',
           'Your responsibilities will include developing and implementing web-based solutions, collaborating with cross-functional teams, and ensuring the scalability and performance of web applications.',
           'We are looking for a passionate web developer with a strong understanding of front-end and back-end technologies. The ideal candidate should have experience with HTML, CSS, JavaScript, and web frameworks like React or Angular. A keen eye for design and the ability to work collaboratively are essential.',
           'ACME Corp offers competitive compensation, a great work-life balance, and opportunities for professional growth. Join our team and be part of a dynamic and innovative company.',
           'Cracow',
           6000.0,
           8000.0,
           'B2B',
           'PLN',
           'ONSITE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'JS');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), '.Net');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'SQL');

-- EMPLOYER 5 job 1
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '22 days',
           (SELECT id FROM employers WHERE company_name = 'FRONT LLC' AND name_and_surname = 'David Lee' LIMIT 1),
           'JavaScript and .Net Developer',
           'FRONT LLC is seeking a talented JavaScript and .Net Developer to join our frontend development team. As a JavaScript and .Net Developer, you will be responsible for developing and maintaining web applications using JavaScript frameworks and the .Net technology stack.',
           'Your responsibilities will include implementing user interfaces, optimizing application performance, troubleshooting issues, and collaborating with cross-functional teams to deliver high-quality frontend solutions.',
           'We are looking for a skilled developer with expertise in JavaScript and .Net technologies such as ASP.Net and C#. Familiarity with frontend frameworks like React or Angular is highly desired.',
           'FRONT LLC offers competitive compensation, a supportive work culture, and opportunities to work on diverse frontend projects. Join our team and contribute to the development of innovative web applications.',
           'Chicago',
           6000.0,
           10000.0,
           'EMPLOYMENT',
           'DOLLAR',
           'REMOTE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'FRONT LLC' AND name_and_surname = 'David Lee'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), '.Net');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'JS');

-- EMPLOYER 4 job 1
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '24 days',
           (SELECT id FROM employers WHERE company_name = 'BACKEND Corp' AND name_and_surname = 'Lisa Wang' LIMIT 1),
           'Python Backend Developer',
           'BACKEND Corp is seeking a talented Python backend developer to join our team. As a Python backend developer, you will be responsible for designing, developing, and maintaining scalable and high-performance backend systems.',
           'Your responsibilities will include building RESTful APIs, integrating with databases and external services, and optimizing backend processes for efficiency.',
           'We are looking for a skilled developer with expertise in Python and backend frameworks such as Django or Flask. Knowledge of database systems and experience in building scalable and robust web applications is essential.',
           'BACKEND Corp offers competitive compensation, a challenging work environment, and opportunities to work with cutting-edge technologies. Join our team and contribute to the development of innovative backend solutions.',
           'London',
           400.0,
           5000.0,
           'INTERNSHIP',
           'EURO',
           'ONSITE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'BACKEND Corp' AND name_and_surname = 'Lisa Wang'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Python');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'SQL');

-- EMPLOYER 3 job 1
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '26 days',
           (SELECT id FROM employers WHERE company_name = 'WEB Inc' AND name_and_surname = 'Bob Johnson' LIMIT 1),
           'ERP System Developer',
           'WEB Inc is seeking a skilled ERP system developer to join our team. As an ERP system developer, you will be responsible for designing, developing, and maintaining robust enterprise resource planning systems.',
           'Your responsibilities will include gathering requirements, implementing system functionalities, and integrating ERP solutions with other business applications.',
           'We are looking for a talented developer with expertise in ERP systems such as SAP, Oracle, or Microsoft Dynamics. Proficiency in programming languages such as Java or C# and knowledge of database management systems are essential.',
           'WEB Inc offers competitive compensation, great benefits, and opportunities for professional development. Join our team and contribute to the development of cutting-edge ERP solutions.',
           'Warsaw',
           6000.0,
           10000.0,
           'COMMISSION',
           'PLN',
           'ONSITE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'WEB Inc' AND name_and_surname = 'Bob Johnson'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'PowerShell');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'SQL');

-- EMPLOYER 2 job 1
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '28 days',
           (SELECT id FROM employers WHERE company_name = 'FULLSTACK Corp' AND name_and_surname = 'Jane Smith' LIMIT 1),
           'Data Analyst',
           'FULLSTACK Corp is seeking a skilled data analyst to join our team. As a data analyst, you will be responsible for gathering, analyzing, and interpreting complex data sets to provide valuable insights and support decision-making processes.',
           'Your responsibilities will include cleaning and transforming data, conducting statistical analyses, and creating visualizations and reports to communicate findings effectively.',
           'We are looking for a detail-oriented data analyst with strong analytical skills and proficiency in data manipulation and visualization tools such as SQL, Python, and Tableau. Experience in statistical analysis and data modeling is preferred.',
           'FULLSTACK Corp offers flexible work hours, a collaborative team environment, and opportunities for professional development. Join our team and contribute to meaningful data-driven projects.',
           'Berlin',
           6000.0,
           7000.0,
           'EMPLOYMENT',
           'EURO',
           'ONSITE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'FULLSTACK Corp' AND name_and_surname = 'Jane Smith'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'SQL');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'PowerShell');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Data');

-- EMPLOYER 1 job 1
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '29 days',
           (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe' LIMIT 1),
           'Software Developer',
           'We are seeking a skilled software developer to join our team.',
           'Responsibilities include developing and maintaining software applications, debugging and troubleshooting issues, and collaborating with the team to deliver high-quality code.',
           'We are looking for someone with a strong passion for software development, excellent problem-solving skills, and a desire to learn and grow in a dynamic environment.',
           'We offer competitive salary, flexible work hours, and opportunities for professional development.',
           'Cracow',
           10000.0,
           12000.0,
           'B2B',
           'PLN',
           'REMOTE'
       );

INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Python');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Scala');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'SQL');
