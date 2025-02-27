use pharmacy_mgmt;

DROP TABLE IF EXISTS pharmacy_store;
DROP TABLE IF EXISTS pharmacy_order_item;
DROP TABLE IF EXISTS pharmacy_inventory;
DROP TABLE IF EXISTS manufacturer_inventory;
DROP TABLE IF EXISTS shipment;
DROP TABLE IF EXISTS transport_vehicle;
DROP TABLE IF EXISTS pharmacy_order;
DROP TABLE IF EXISTS master_drug_table;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS company;

CREATE TABLE company(
company_id INT PRIMARY KEY AUTO_INCREMENT,
company_name VARCHAR(100) NOT NULL,
company_type VARCHAR(20) NOT NULL,
registered_date DATE
);

CREATE TABLE person(
username VARCHAR(20) PRIMARY KEY,
person_name VARCHAR(100) NOT NULL,
password VARCHAR(50) NOT NULL,
person_dob Date,
person_gender CHAR(10),
person_role VARCHAR(50) NOT NULL,
person_address VARCHAR(100),
person_city VARCHAR(50),
person_zipcode VARCHAR(10),
company_id INT,
person_email VARCHAR(50),
person_contact VARCHAR(50),
foreign key (company_id) references company(company_id)
);

CREATE TABLE master_drug_table(
drug_id INT PRIMARY KEY AUTO_INCREMENT,
drug_name VARCHAR(100) NOT NULL
);

CREATE TABLE pharmacy_store(
store_id INT PRIMARY KEY AUTO_INCREMENT,
pharmacy_id INT,
store_name VARCHAR(100) not null,
store_address VARCHAR(100) not null,
store_zip VARCHAR(10) not null,
store_city VARCHAR(25) not null,
foreign key (pharmacy_id) references company(company_id)
);

CREATE TABLE pharmacy_order(
order_id INT PRIMARY KEY AUTO_INCREMENT,
pharmacy_id INT NOT NULL,
manufacturer_id INT NOT NULL,
distributor_id INT,
transporter_id INT,
order_date DATE,
order_status varchar(50),
foreign key (pharmacy_id) references company(company_id),
foreign key (manufacturer_id) references company(company_id),
foreign key (distributor_id) references company(company_id),
foreign key (transporter_id) references company(company_id)
);

CREATE TABLE pharmacy_order_item(
order_item_id INT PRIMARY KEY AUTO_INCREMENT,
order_id INT NOT NULL,
item_id INT NOT NULL,
quantity INT NOT NULL,
cost_price DECIMAL,
foreign key (order_id) references pharmacy_order(order_id)
);

CREATE TABLE pharmacy_inventory(
inventory_id INT primary key auto_increment,
pharmacy_id INT,
drug_id INT,
quantity BIGINT NOT NULL,
cost_price BIGINT,
selling_price BIGINT,
FOREIGN KEY (drug_id) REFERENCES master_drug_table(drug_id),
FOREIGN KEY (pharmacy_id) REFERENCES company(company_id)
);

CREATE TABLE manufacturer_inventory(
manufacturer_id INT,
drug_id INT,
quantity BIGINT NOT NULL,
cost_price BIGINT,
selling_price BIGINT,
PRIMARY KEY(manufacturer_id, drug_id),
FOREIGN KEY (drug_id) REFERENCES master_drug_table(drug_id),
FOREIGN KEY (manufacturer_id) REFERENCES company(company_id)
);

CREATE TABLE shipment(
shipment_id INT primary key auto_increment,
order_id INT,
distributor_id INT,
transporter_id INT,
shipment_status VARCHAR(20),
foreign key (order_id) references pharmacy_order(order_id),
foreign key (distributor_id) references company(company_id),
foreign key (transporter_id) references company(company_id)
);

create table transport_vehicle(
transporter_id INT primary key,
vehicle_count INT,
foreign key (transporter_id) references company(company_id)
);

