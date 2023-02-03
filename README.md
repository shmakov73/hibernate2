The essence of the project is to understand the structure of the database, to map the entity to existing tables,
and add minimal functionality to check that the mapping is done correctly.

1. Create all required entity classes and map them onto movie schema tables.

2. Add a method that can create a new customer (customer table) with all dependent fields.
   Do not forget to make the method transactional (so as not to get into the situation that the buyer's address is recorded in the database,
   but not the buyer).

3. Add a transactional method that describes the event "the buyer went and returned the previously rented movie."
   Choose any buyer and rental event of your choice. The rating of the film does not need to be recalculated.

4. Add a transactional method that describes the event "the buyer went to the store (store) and rented (rental)
   there inventory. At the same time, he made a payment (payment) to the seller (staff). Movie (via inventory)
   choose at your discretion. The only restriction is that the film must be available for rent. That is, either in rental
   there should be no inventory records at all, or the return_date column of the rental table should be filled
   for the last rental of this inventory.

5. Add a transactional method that describes the event "a new movie has been shot and it has become available for rent."
   Film, language, actors, categories, etc., choose at your discretion.

6. The structure of tables cannot be changed. But you need to make suggestions for improvement. We have one problem area
   revealed in paragraph 4 (absence of foreign key in the film_text table on the film_id field of the film table).


In addition to the problem of this database discussed above, you can also:
- remove the original_language_id column of the movie.film table, since it is not used in this database and is filled with null values.
- move the special_features column of the movie.film table into a separate table with a many-to-many relationship.

The source database dump is located in the "resources" folder



Суть проекта – разобраться в структуре БД, сделать маппинг энтити на существующие таблицы, 
и добавить минимальный функционал для проверки что маппинг выполнен правильно.

1. Создать все необходимые энтити классы и замапить их на таблицы схемы movie.

2. Добавить метод, который умеет создавать нового покупателя (таблица customer) со всеми зависимыми полями. 
Не забудь сделать чтоб метод был транзакционным (чтоб не попасть в ситуацию что адрес покупателя записали в БД, 
а самого покупателя – нет).

3. Добавить транзакционный метод, который описывает событие «покупатель пошел и вернул ранее арендованный фильм». 
Покупателя и событие аренды выбери любое на свое усмотрение. Рейтинг фильма пересчитывать не нужно.

4. Добавить транзакционный метод, который описывает событие «покупатель сходил в магазин (store) и арендовал (rental)
там инвентарь (inventory). При этом он сделал оплату (payment) у продавца (staff)». Фильм (через инвентарь)
выбери на свое усмотрение. Единственное ограничение – фильм должен быть доступен для аренды. То есть либо в rental
не должно быть вообще записей по инвентарю, либо должна быть заполнена колонка return_date таблицы rental
для последней аренды этого инвентаря.

5. Добавить транзакционный метод, который описывает событие «сняли новый фильм, и он стал доступен для аренды». 
Фильм, язык, актеров, категории и т д выбери на свое усмотрение.

6. Структуру таблиц менять нельзя. Но внести свои предложения по улучшению – нужно. Одно проблемное место мы
выявили в п.4 (отсутствие foreign key в таблице film_text на поле film_id таблицы film). 

Дамп исходной базы данных находится в папке "resources"


Помимо рассмотренной выше проблемы данной базы данных, так-же можно:
- убрать колонку original_language_id таблицы movie.film, так как она не используется в данной базе и заполнена значениями null.
- вынести колонку special_features таблицы movie.film в отдельную таблицу со связью many-to-many.