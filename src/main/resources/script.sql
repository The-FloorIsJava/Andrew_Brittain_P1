CREATE TABLE employee(
employee_email varchar(255) primary key,
is_manager_true boolean,
employee_password varchar(255) not null
);

CREATE TABLE ticket(
amount numeric not null,
ticket_id serial primary key,
ticket_type varChar(255) not null,
ticket_approved varChar(255),
requester varchar(255),
foreign key (requester) references employee(employee_email)
on delete cascade
);