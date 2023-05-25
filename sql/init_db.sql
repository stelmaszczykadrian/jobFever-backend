-- Important information:
--     1. Run this file
--     2. Password for all created employers is : 111111

-- example employers
WITH inserted_employers AS (
    INSERT INTO employers (company_name, name_and_surname, phone_number, email, about_us, why_worth_working_with_us, localization, nip, linkedin, role_type, img_file_name)
        VALUES
            ('ACME Corp', 'John Doe', 123456789, 'johndoe@example.com', 'We are a software development company specializing in web applications and mobile apps.', 'We offer a great work-life balance and opportunities for growth within the company.', 'New York', 1111111111, '/acme-corp', 'EMPLOYER', 'looogo.png'),
            ('FULLSTACK Corp', 'Jane Smith', 987654321, 'janesmith@example.com', 'We are a technology consulting firm with expertise in data analytics and cloud solutions.', 'We offer flexible work hours and a collaborative team environment.', 'San Francisco', 2222222222, '/fullstack-corp', 'EMPLOYER', 'looogo.png'),
            ('WEB Inc', 'Bob Johnson', 555555555, 'bjohnson@example.com', 'We are a software company specializing in enterprise resource planning (ERP) systems.', 'We provide great benefits and opportunities for professional development.', 'Warsaw', 3333333333, '/web-inc', 'EMPLOYER', 'looogo.png'),
            ('BACKEND Corp', 'Lisa Wang', 111222333, 'lisawang@example.com', 'We are a startup developing cutting-edge artificial intelligence (AI) software.', 'We offer a dynamic and challenging work environment for those passionate about AI.', 'Cracow', 4444444444, '/backend-corp', 'EMPLOYER', 'looogo.png'),
            ('FRONT LLC', 'David Lee', 111888777, 'davidlee@example.com', 'We are a software development company creating mobile apps and custom software solutions.', 'We provide a supportive and fun work culture with opportunities for creativity and innovation.', 'Tokio', 5555555555, '/front-lcc', 'EMPLOYER', 'looogo.png')
        RETURNING id, email
)
INSERT INTO users (email, password, role_type, employer_id)
SELECT
    e.email,
    '$2a$10$.I0QYwo1jNRPwmY8YjhOauPErcNC041FjFOFmN8U876qzphEas.JO',
    'EMPLOYER',
    e.id
FROM inserted_employers e;


-- EMPLOYER 1 job 1
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
        VALUES (
                   NOW(),
                   NOW() + INTERVAL '20 days',
                   (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe' LIMIT 1),
                   'Software Developer',
                   'We are seeking a skilled software developer to join our team.',
                   'Responsibilities include developing and maintaining software applications, debugging and troubleshooting issues, and collaborating with the team to deliver high-quality code.',
                   'We are looking for someone with a strong passion for software development, excellent problem-solving skills, and a desire to learn and grow in a dynamic environment.',
                   'We offer competitive salary, flexible work hours, and opportunities for professional development.',
                   'New York',
                   5000.0,
                   8000.0,
                   'EMPLOYMENT',
                   'DOLLAR',
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
    ((SELECT max(job_id) FROM jobs), 'Java');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'SQL');

-- EMPLOYER 1 job 2
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '25 days',
           (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe' LIMIT 1),
           'Web Developer',
           'ACME Corp is seeking a talented web developer to join our team. As a web developer, you will be responsible for creating and maintaining web applications using cutting-edge technologies. Join us in shaping the digital landscape and delivering innovative solutions to our clients.',
           'Your responsibilities will include developing and implementing web-based solutions, collaborating with cross-functional teams, and ensuring the scalability and performance of web applications.',
           'We are looking for a passionate web developer with a strong understanding of front-end and back-end technologies. The ideal candidate should have experience with HTML, CSS, JavaScript, and web frameworks like React or Angular. A keen eye for design and the ability to work collaboratively are essential.',
           'ACME Corp offers competitive compensation, a great work-life balance, and opportunities for professional growth. Join our team and be part of a dynamic and innovative company.',
           'Warsaw',
           6000.0,
           8000.0,
           'EMPLOYMENT',
           'PLN',
           'REMOTE'
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

-- EMPLOYER 1 job 3
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '26 days',
           (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe' LIMIT 1),
           'Mobile App Developer',
           'ACME Corp is looking for a skilled mobile app developer to join our team. As a mobile app developer, you will be responsible for creating innovative and user-friendly mobile applications for iOS and Android platforms.',
           'Your responsibilities will include developing mobile applications from concept to deployment, collaborating with designers and product managers, and ensuring the performance and usability of the apps.',
           'We are seeking a highly motivated individual with a strong background in mobile app development. The ideal candidate should have experience with iOS and Android frameworks, knowledge of mobile UI/UX principles, and proficiency in programming languages such as Swift or Kotlin.',
           'ACME Corp offers competitive compensation, flexible work hours, and a supportive work environment. Join our team and contribute to the development of cutting-edge mobile applications.',
           'Cracow',
           10000.0,
           12000.0,
           'EMPLOYMENT',
           'PLN',
           'REMOTE'
       );


INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Swift');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'JS');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Java');

-- EMPLOYER 1 job 4
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '22 days',
           (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe' LIMIT 1),
           'Full Stack Developer',
           'ACME Corp is seeking a talented full stack developer to join our team. As a full stack developer, you will have the opportunity to work on a wide range of projects, from developing web applications to implementing complex database systems.',
           'Your responsibilities will include designing and implementing server-side architecture, developing front-end interfaces, and collaborating with cross-functional teams to deliver high-quality software solutions.',
           'We are looking for a highly skilled full stack developer with expertise in both front-end and back-end development. The ideal candidate should have experience with modern web technologies, databases, and frameworks such as React, Node.js, and MongoDB.',
           'ACME Corp offers competitive compensation, a collaborative work environment, and opportunities for professional growth. Join our team and make a significant impact on our diverse projects.',
           'New York',
           7000.0,
           9000.0,
           'EMPLOYMENT',
           'DOLLAR',
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
    ((SELECT max(job_id) FROM jobs), 'JS');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'Pascal');

-- EMPLOYER 1 job 5
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '20 days',
           (SELECT id FROM employers WHERE company_name = 'ACME Corp' AND name_and_surname = 'John Doe' LIMIT 1),
           'UX Designer',
           'ACME Corp is looking for a talented UX designer to join our team. As a UX designer, you will play a crucial role in creating intuitive and engaging user experiences for our web and mobile applications.',
           'Your responsibilities will include conducting user research, creating wireframes and prototypes, and collaborating with cross-functional teams to deliver exceptional design solutions.',
           'We are seeking a creative and detail-oriented UX designer with a strong portfolio showcasing user-centered design solutions. The ideal candidate should have expertise in design tools such as Sketch or Figma, knowledge of UX best practices, and the ability to translate complex concepts into simple and elegant designs.',
           'ACME Corp offers competitive compensation, a supportive work environment, and opportunities to work on exciting projects. Join our team and contribute to the development of user-centric digital experiences.',
           'San Francisco',
           500.0,
           7000.0,
           'EMPLOYMENT',
           'DOLLAR',
           'ONSITE'
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
    ((SELECT max(job_id) FROM jobs), 'DATA');

-- EMPLOYER 2 job 1
INSERT INTO jobs (posting_date, expiration_date, employer_id, title, description, responsibilities, who_we_are_looking_for, benefits, location, salary_from, salary_to, job_type, currency_type, work_type)
VALUES (
           NOW(),
           NOW() + INTERVAL '20 days',
           (SELECT id FROM employers WHERE company_name = 'FULLSTACK Corp' AND name_and_surname = 'Jane Smith' LIMIT 1),
           'TEST',
           'We are seeking a skilled software developer to join our team.',
           'Responsibilities include developing and maintaining software applications, debugging and troubleshooting issues, and collaborating with the team to deliver high-quality code.',
           'We are looking for someone with a strong passion for software development, excellent problem-solving skills, and a desire to learn and grow in a dynamic environment.',
           'We offer competitive salary, flexible work hours, and opportunities for professional development.',
           'New York, NY',
           2000.0,
           6000.0,
           'COMMISSION',
           'PLN',
           'REMOTE'
       );


INSERT INTO employers_job (employer_id, job_job_id)
SELECT
    (SELECT id FROM employers WHERE company_name = 'FULLSTACK Corp' AND name_and_surname = 'Jane Smith'),
    (SELECT max(job_id) FROM jobs);

INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'C#');
INSERT INTO job_technical_requirements (job_job_id, technical_requirements)
VALUES
    ((SELECT max(job_id) FROM jobs), 'PowerShell');




