# learn_selenium_java
Вспоминаю селениум с синтаксисом java.
Повторение по курсу Баранцева, но за 2016 год (Selenium 3).
Плюс внешние источники и документация.

1)MyFirstTest.java : 
На странице http://localhost/litecart/admin/?app=countries&doc=countries
а) проверить, что страны расположены в алфавитном порядке
б) для тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны и там проверить, что зоны расположены в алфавитном порядке

2)SecondTest.java : 
На странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones
зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке

3)mainForExOne.java :
На странице https://shop.atributika.ru/. Сайт выбран для тестирования, так как у него при регистрации нет капчи (ох и нагрузил я его)
Проверяем регистрацию аккаунты и последующую авторизацию.
Написаны 2 класса, в которых происходят проверки.
Научился работать с селектами (вып.списки), поработал со скрытыми элементами.

4) add_elements_in_basket :
Добавление товаров в корзину, проверка, что товары добавлены.
Удаление товаров из корзины.
Использую moveToElement(element).perform() для наведения на товар, ожидаю, что у товара появится скрытая кнопка быстрого просмотра товара.

5) windows_and_action_with_them :
* 1. Авторизоваться админом на странице со списком стран.
* 2. Перейти в раздел создания новой страны.
* 3. возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой -- они ведут на внешние страницы
* и открываются в новом окне, именно это и нужно проверить.
Структура изменяется, так как постепенно пытаюсь писать проект как PageObject.