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
 * • Вывести номера заказов, содержащих заданный товар. 
 * • Вывести номера заказов, не содержащих заданный товар и поступивших в течение текущего дня. 
 * • Сформировать новый заказ, состоящий из товаров, заказанных в текущий день. 
 * • Удалить все заказы, в которых присутствует заданное количество заданного товара.
 * 
 * @author Vasil Talkachou
 */
public class Question12 {

    public static void main(String[] args) {
        DBCreator.createTables();
        DBCreator.addProducts();
        DBCreator.addOrders();
        DBCreator.addItems();
        
        DBGetter getter = new DBGetter();
        
        int ordersNum = getter.getOrdersNumber();
        System.out.print("Number of orders:");
        System.out.println(ordersNum);
        
        ArrayList<Order> list = getter.getOrders();
        for(Order o : list)
            System.out.println(o.toString());
        
        Scanner sc = new Scanner(System.in);
        int sel = 0;
        do {
            System.out.print("Select the order:");
            sel = sc.nextInt();
        } while(sel < 0 || sel > ordersNum);
        Order selOrder = getter.getOrder(sel);
        ArrayList<String> info = getter.getOrderInfo(sel);
        for (String s : info) {
            System.out.println(s);
        }
        
        
        getter.closeStatement();
        getter.closeConnection();
    }
    
}
