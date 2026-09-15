INSERT INTO restaurants (name,cuisine,city,available) VALUES ('Spice Route','Indian','Pune',true),('Green Bowl','Healthy','Pune',true),('Pizza Hub','Italian','Mumbai',true);
INSERT INTO restaurant_menu (restaurant_id,item) SELECT id,'Paneer Tikka' FROM restaurants WHERE name='Spice Route';
INSERT INTO restaurant_menu (restaurant_id,item) SELECT id,'Dal Tadka' FROM restaurants WHERE name='Spice Route';
INSERT INTO restaurant_menu (restaurant_id,item) SELECT id,'Veg Salad' FROM restaurants WHERE name='Green Bowl';
