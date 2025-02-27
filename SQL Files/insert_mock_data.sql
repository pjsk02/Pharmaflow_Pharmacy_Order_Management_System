use pharmacy_mgmt;

insert into master_drug_table(drug_name) values('Crocin');
insert into master_drug_table(drug_name) values('Paracetamol');
insert into master_drug_table(drug_name) values('Cetrizon');

insert into company(company_name, company_type, registered_date) values("Abbot Laboratories", "MANUFACTURER", "1980-02-13");
insert into company(company_name, company_type, registered_date) values("Johnson and Johnson", "MANUFACTURER", "1985-04-13");
insert into company(company_name, company_type, registered_date) values("Cardinal Health", "DISTRIBUTOR", "1990-05-23");
insert into company(company_name, company_type, registered_date) values("King World Medicines Group", "DISTRIBUTOR", "2000-12-25");
insert into company(company_name, company_type, registered_date) values("CVS Pharmacy", "PHARMACY", "1982-06-04");
insert into company(company_name, company_type, registered_date) values("MedPlus", "PHARMACY", "1970-02-16");
insert into company(company_name, company_type, registered_date) values("Boston Transports", "TRANSPORTER", "1982-06-04");

insert into person(username, person_name, password, person_role, company_id) values("man1", "Alankrutha Reddy", "password", "MANUFACTURE_ADMIN", 1);
insert into person(username, person_name, password, person_role, company_id) values("man2", "Varadmurty Mohod", "password", "MANUFACTURE_ADMIN", 2);
insert into person(username, person_name, password, person_role, company_id) values("dist1", "Alankrutha Reddy", "password", "DISTRIBUTOR_ADMIN", 3);
insert into person(username, person_name, password, person_role, company_id) values("dist2", "Varadmurty Mohod", "password", "DISTRIBUTOR_ADMIN", 4);
insert into person(username, person_name, password, person_role, company_id) values("pharm1", "Sudarshan Kudli", "password", "PHARMACY_ADMIN", 5);
insert into person(username, person_name, password, person_role, company_id) values("pharm2", "Sudarshan Kudli", "password", "PHARMACY_ADMIN", 6);
insert into person(username, person_name, password, person_role, company_id) values("phstore1", "Shreya Jaiswal", "password", "PHARMACY_STORE_MANAGER", 5);
insert into person(username, person_name, password, person_role, company_id) values("trans1", "Suraksha Nadig", "password", "TRANSPORT_ADMIN", 7);
insert into person(username, person_name, password, person_role, company_id) values("accmng1", "Kailash", "password", "ACCOUNTING_MANAGER", 5);
insert into person(username, person_name, password, person_role, company_id) values("salesmngr1", "Kailash Sales", "password", "SALES_MANAGER", 5);
insert into person(username, person_name, password, person_role, company_id) values("distmngr1", "Deepthi", "password", "DISTRIBUTOR_MANAGER", 6);


insert into manufacturer_inventory(manufacturer_id, drug_id, quantity, cost_price, selling_price) values(1, 1, 100, 3, 4);
insert into manufacturer_inventory(manufacturer_id, drug_id, quantity, cost_price, selling_price) values(1, 2, 120, 4, 4.5);
insert into manufacturer_inventory(manufacturer_id, drug_id, quantity, cost_price, selling_price) values(2, 2, 150, 10, 12.5);
insert into manufacturer_inventory(manufacturer_id, drug_id, quantity, cost_price, selling_price) values(2, 3, 200, 14, 15);

insert into pharmacy_order(pharmacy_id, manufacturer_id, order_date, order_status) values(5, 1, "2021-02-16", "PENDING");
insert into pharmacy_order(pharmacy_id, manufacturer_id, order_date, order_status) values(6, 2, "2021-04-16", "PENDING");

insert into pharmacy_order_item(order_id, item_id, quantity, cost_price) values(1, 1, 5, 4);
insert into pharmacy_order_item(order_id, item_id, quantity, cost_price) values(1, 2, 2, 4.5);
insert into pharmacy_order_item(order_id, item_id, quantity, cost_price) values(2, 2, 8, 4.5);
insert into pharmacy_order_item(order_id, item_id, quantity, cost_price) values(2, 3, 3, 15);

insert into pharmacy_inventory(pharmacy_id, drug_id, quantity, cost_price, selling_price) values(5, 1, 5, 4, 6);
insert into pharmacy_inventory(pharmacy_id, drug_id, quantity, cost_price, selling_price) values(5, 2, 10, 4.5, 7);

INSERT INTO pharmacy_store(pharmacy_id, store_name, store_address, store_zip, store_city)
VALUES(5, "CVS 1", "25 Fenway Street", "02118", "Boston");
INSERT INTO pharmacy_store(pharmacy_id, store_name, store_address, store_zip, store_city)
VALUES(5, "CVS 2", "115 Boyleston Street", "02125", "Boston");
INSERT INTO pharmacy_store(pharmacy_id, store_name, store_address, store_zip, store_city)
VALUES(5, "MedPlus 1", "1628 5th Avenue", "02310", "Seattle");

select * from person;
select * from pharmacy_inventory;

select * from shipment;

SELECT s.shipment_id, po.order_date, po.pharmacy_id,c1.company_name,c2.company_name,s.shipment_status FROM shipment s JOIN pharmacy_order po ON s.order_id = po.order_id Join company c1 ON po.pharmacy_id = c1.company_id JOIN company c2 ON po.distributor_id = c2.company_id;

INSERT INTO company (company_name, company_type, registered_date)
VALUES 
    ('CVS Pharmacy', 'Pharmacy', '2022-01-01'),
    ('Walgreens', 'Pharmacy', '2022-05-01'),
    ('XYZ Distributor', 'Distributor', '2021-03-15'),
    ('ABC Distributor', 'Distributor', '2021-06-20');

INSERT INTO pharmacy_order (pharmacy_id, manufacturer_id, distributor_id, transporter_id, order_date, order_status)
VALUES 
    (1, 1, 3, 4, '2024-01-01', 'Shipped'),  -- CVS to XYZ Distributor
    (2, 1, 4, NULL, '2024-01-05', 'Pending');  -- Walgreens to ABC Distributor
INSERT INTO shipment (order_id, distributor_id, transporter_id, shipment_status)
VALUES 
    (1, 3, NULL, 'Delivered'),  -- Order 1 shipped by XYZ Distributor
    (2, 4, NULL, 'In Transit'); -- Order 2 shipped by ABC Distributor
