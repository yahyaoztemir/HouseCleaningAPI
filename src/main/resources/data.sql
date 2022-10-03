
insert into car(brand,model,number_plate) values('arac1marka','arac1model','arac1plaka');
insert into car(brand,model,number_plate) values('arac2marka','arac2model','arac2plaka');
insert into car(brand,model,number_plate) values('arac3marka','arac3model','arac3plaka');
insert into car(brand,model,number_plate) values('arac4marka','arac4model','arac4plaka');
insert into car(brand,model,number_plate) values('arac5marka','arac5model','arac5plaka');

insert into cleaner_professional(name,rating,surname,car_id) values('tem1ad',2,'tem1soyad',1);
insert into cleaner_professional(name,rating,surname,car_id) values('tem2ad',2,'tem2soyad',1);
insert into cleaner_professional(name,rating,surname,car_id) values('tem3ad',2,'tem3soyad',1);
insert into cleaner_professional(name,rating,surname,car_id) values('tem4ad',2,'tem4soyad',1);
insert into cleaner_professional(name,rating,surname,car_id) values('tem5ad',2,'tem5soyad',1);
insert into cleaner_professional(name,rating,surname,car_id) values('tem6ad',2,'tem6soyad',2);
insert into cleaner_professional(name,rating,surname,car_id) values('tem7ad',2,'tem7soyad',2);
insert into cleaner_professional(name,rating,surname,car_id) values('tem8ad',2,'tem8soyad',2);
insert into cleaner_professional(name,rating,surname,car_id) values('tem9ad',2,'tem9soyad',2);
insert into cleaner_professional(name,rating,surname,car_id) values('tem10ad',2,'tem10soyad',2);
insert into cleaner_professional(name,rating,surname,car_id) values('tem11ad',2,'tem11soyad',3);
insert into cleaner_professional(name,rating,surname,car_id) values('tem12ad',2,'tem12soyad',3);
insert into cleaner_professional(name,rating,surname,car_id) values('tem13ad',2,'tem13soyad',3);
insert into cleaner_professional(name,rating,surname,car_id) values('tem14ad',2,'tem14soyad',3);
insert into cleaner_professional(name,rating,surname,car_id) values('tem15ad',2,'tem15soyad',3);
insert into cleaner_professional(name,rating,surname,car_id) values('tem16ad',2,'tem16soyad',4);
insert into cleaner_professional(name,rating,surname,car_id) values('tem17ad',2,'tem17soyad',4);
insert into cleaner_professional(name,rating,surname,car_id) values('tem18ad',2,'tem18soyad',4);
insert into cleaner_professional(name,rating,surname,car_id) values('tem19ad',2,'tem19soyad',4);
insert into cleaner_professional(name,rating,surname,car_id) values('tem20ad',2,'tem20soyad',4);
insert into cleaner_professional(name,rating,surname,car_id) values('tem21ad',2,'tem21soyad',5);
insert into cleaner_professional(name,rating,surname,car_id) values('tem22ad',2,'tem22soyad',5);
insert into cleaner_professional(name,rating,surname,car_id) values('tem23ad',2,'tem23soyad',5);
insert into cleaner_professional(name,rating,surname,car_id) values('tem24ad',2,'tem24soyad',5);
insert into cleaner_professional(name,rating,surname,car_id) values('tem25ad',2,'tem25soyad',5);

insert into reservation(date,duration,end_time,start_time)
values('2022-10-1',2,'18:00','16:00');
insert into reservation(date,duration,end_time,start_time)
values('2022-10-2',4,'18:00','14:00');
insert into reservation(date,duration,end_time,start_time)
values('2022-10-3',2,'14:00','12:00');
insert into reservation(date,duration,end_time,start_time)
values('2022-10-4',2,'13:00','11:00');
insert into reservation(date,duration,end_time,start_time)
values('2022-10-5',2,'22:00','20:00');

insert into cleanerprofessionalsofreservations(reservation_id,cleaner_professional_id)
values(1,1);
insert into cleanerprofessionalsofreservations(reservation_id,cleaner_professional_id)
values(1,2);
insert into cleanerprofessionalsofreservations(reservation_id,cleaner_professional_id)
values(1,3);

insert into cleanerprofessionalsofreservations(reservation_id,cleaner_professional_id)
values(2,6);
insert into cleanerprofessionalsofreservations(reservation_id,cleaner_professional_id)
values(2,7);

insert into cleanerprofessionalsofreservations(reservation_id,cleaner_professional_id)
values(3,13);
insert into cleanerprofessionalsofreservations(reservation_id,cleaner_professional_id)
values(3,14);
insert into cleanerprofessionalsofreservations(reservation_id,cleaner_professional_id)
values(3,15);

insert into cleanerprofessionalsofreservations(reservation_id,cleaner_professional_id)
values(4,16);
insert into cleanerprofessionalsofreservations(reservation_id,cleaner_professional_id)
values(4,18);
insert into cleanerprofessionalsofreservations(reservation_id,cleaner_professional_id)
values(4,19);

insert into cleanerprofessionalsofreservations(reservation_id,cleaner_professional_id)
values(5,22);