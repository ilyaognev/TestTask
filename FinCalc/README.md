##Тестовое задание для соискателя на должность Java-разработчика

###Описание задания

Необходимо написать простейший финансовый калькулятор, работающий в консоли.

###Функционал:

2 валюты: доллар ($12) и евро (52.5eur)

Операции + и -

Знак доллара указывается перед числом, евро - после.

Число может быть целым (в т.ч. отрицательным) или дробным, с, максимум, двумя знаками после точки.

Поддержка конвертации валют (toDollar(), toEuro()), курсы должны быть заданы в некотором внешнем файле конфигурации, удобном для редактирования. Курсы в разные стороны могут отличаться!

Контроль типов: нельзя конвертировать валюту в саму себя, складывать разные валюты

В случае неверного выражения пользователю должно выводиться сообщение об ошибке в понятной форме.

(опционально) Все посчитанные выражения и их значения должны сохраняться в лог-файл

###Архитектура

Писать не на скорость, а с упором на грамотность, гибкость и удобство доработки (представьте, что кому-то другому придется добавлять поддержку нового типа валют, или вместо консоли "прикрутить" графический интерфейс - это не должно привести к значительным правкам существующего кода). Цель задания - проверить Ваше умение разрабатывать архитектуру ПО, выстраивать гибкие объектные модели.


###Пример входного выражения:

toDollar(toEuro($10.00)+5eur)


Результат (если за 1 доллар дают 0.8 евро, а за 1 евро 1.5 доллара):

$19.50

-------------------
###РЕШЕНИЕ ЗАДАЧИ

Для более быстрого решения условимся, что может быть максимум два аргумента для сложения или вычитания.

**Примеры для теста**:

* toEuro($10.00) //assert->8eur
* toDollar(5eur) //assert->$7.5
* toDollar(toEuro($10.00)+5eur) //assert->$19.5
* toDollar(toEuro($10.00)-5eur) //assert->$4.5
* toDollar(5eur+toEuro($10.00)) //assert->$19.5
* toDollar(5eur-toEuro($10.00)) //assert->-$4.5
* toDollar(toEuro($10.00)) //assert->$12
* 5eur-toEuro($10.00) //assert->-3eur

**TODO:**

* добавить ресурсный файл;
* добавить логирование;
* использовать enum для валют.