package question12;

import java.util.*;

/**
 * Задания к главе 12 
 * Вариант А 
 * В каждом из заданий необходимо выполнить следующие действия: 
 * + организацию соединения с базой данных вынести в отдельный класс, 
 *   метод которого возвращает соединение; 
 * + создать БД. Привести  таблицы к одной из нормированных форм; 
 * + создать класс для выполнения запросов на извлечение информации из БД
 *   с использованием компилированных запросов; 
 * + создать класс на модификацию информации.
 * 
 * 6. Заказ. 
 * В БД хранится информация о заказах магазина и товарах в них.
 * Для заказа необходимо хранить:
 * — номер заказа; 
 * — товары в заказе; 
 * — дату поступления. 
 * Для товаров в заказе необходимо хранить: 
 * — товар; 
 * — количество.
 * Для товара необходимо хранить: 
 * — название; 
 * — описание; 
 * — цену. 
 * 
 * + Вывести полную информацию о заданном заказе. 
 * • Вывести номера заказов, сумма которых не превосходит заданную 
 *   и количество различных товаров равно заданному. 
 * + Вывести номера заказов, содержащих заданный товар. 
 * • Вывести номера заказов, не содержащих заданный товар и поступивших в течение текущего дня. 
 * • Сформировать новый заказ, состоящий из товаров, заказанных в текущий день. 
 * • Удалить все заказы, в которых присутствует заданное количество заданного товара.
 * 
 * @author Vasil Talkachou
 */
public class Runner {

    public static void main(String[] args) {

        DBCreator.createTables();
        DBCreator.addProducts();
        DBCreator.addOrders();
        DBCreator.addItems();

        DBGetter getter = new DBGetter();

        int ordersNum = getter.getInt(DBGetter.COMMAND_GET_ORDERS_NUMBER);
        int prodNum = getter.getInt(DBGetter.COMMAND_GET_PRODUCTS_NUMBER);
        System.out.println("Количество заказов:" + ordersNum);
        System.out.println("Количество товаров:" + prodNum);

        for (Order o : getter.getOrders()) {
            System.out.println(o.toString());
        }

        Scanner sc = new Scanner(System.in);
        int sel = 0;
        do {
            System.out.print("\nВведите номер заказа для детальной информации:");
            sel = sc.nextInt();
        } while (sel < 0 || sel > ordersNum);
        for (String s : getter.getOrderInfo(sel)) {
            System.out.println(s);
        }

        System.out.println("\nВывести номера заказов, содержащих заданный товар.");
        sel = 0;
        do {
            System.out.print("Введите код товара:");
            sel = sc.nextInt();
        } while (sel < 0 || sel > prodNum);
        for (int i : getter.getOrdersWithItem(sel)) {
            System.out.println(i);
        }
        
        System.out.println("\nВывести номера заказов, \n не содержащих "
            + "заданный товар \n и поступивших в течение текущего дня.");
        sel = 0;
        do {
            System.out.print("Введите код товара:");
            sel = sc.nextInt();
        } while (sel < 0 || sel > prodNum);
        for (int i : getter.getOrdersExcludeItemToday(sel)) {
            System.out.println(i);
        }
        
        
        
        getter.closeStatement();
        getter.closeConnection();
    }

}
