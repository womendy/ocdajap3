INSERT INTO core_entity (core_entity.id, core_entity.created_at, core_entity.name, core_entity.updated_at) VALUES (1,STR_TO_DATE('01/04/2024','%d/%m/%Y'),'1maison',STR_TO_DATE('01/04/2024','%d/%m/%Y'));
INSERT INTO core_entity (core_entity.id, core_entity.created_at, core_entity.name, core_entity.updated_at) VALUES (2,STR_TO_DATE('01/04/2024','%d/%m/%Y'),'1appartement',STR_TO_DATE('01/04/2024','%d/%m/%Y'));



INSERT INTO user (email,id) VALUES ('user2@example.com',1);
INSERT INTO user (email,id) VALUES ('user1@example.com',2);

-- Insertion des locations
INSERT INTO rental (description,surface, price, id, owner_id) VALUES ('Belle maison avec vue sur la mer',80.0, 1000.0, 1, 1);
INSERT INTO rental (description,surface, price, id, owner_id) VALUES ('Appartement lumineux en centre-ville',60.0, 800.0,2 , 2);
-- Insertion des images
INSERT INTO picture (id,full_name, rental_id) VALUES (1,'house1.jpg', 1);
INSERT INTO picture (id,full_name, rental_id) VALUES (2,'apartment1.jpg', 2);
