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
 * + Вывести номера заказов, сумма которых не превосходит заданную 
 *   и количество различных товаров равно заданному. 
 * + Вывести номера заказов, содержащих заданный товар. 
 * + Вывести номера заказов, НЕ содержащих заданный товар и поступивших в течение текущего дня. 
 * + Сформировать новый заказ, состоящий из товаров, заказанных в текущий день. 
 * + Удалить все заказы, в которых присутствует заданное количество заданного товара.
 * 
 * @author Vasil Talkachou
 */
public class Runner {

    public static void main(String[] args) {
        
        DBCreator.dropTables();
        DBCreator.createTables();
        DBCreator.addProducts();
        DBCreator.addOrders();
        DBCreator.addItems();

        DBGetter getter = new DBGetter();

        int ordersNum = getter.getInt(DBGetter.COMMAND_GET_ORDERS_NUMBER);
        int prodNum = getter.getInt(DBGetter.COMMAND_GET_PRODUCTS_NUMBER);

        printList(getter.getOrders());
        System.out.println("\nКоличество заказов:" + ordersNum);
        System.out.println("Количество товаров:" + prodNum);

        Scanner sc = new Scanner(System.in);
        System.out.print("\nВведите номер заказа для детальной информации:");
        int sel = sc.nextInt();
        printList(getter.getOrderInfo(sel));
        
        System.out.println("\nВывести номера заказов, сумма которых не превосходит заданную \n"
                         + " и количество различных товаров равно заданному. ");
        System.out.print("Введите максимальную сумму:");
        int total = sc.nextInt();
        System.out.print("Введите количество товаров:");
        int qty = sc.nextInt();
        printList(getter.getOrdersTotalQty(ordersNum, total, qty));

        System.out.println("\nВывести номера заказов, содержащих заданный товар.");
        System.out.print("Введите код товара:");
        sel = sc.nextInt();
        printList(getter.getOrdersWithItem(sel));
        
        System.out.println("\nВывести номера заказов, \n не содержащих "
                         + "заданный товар \n и поступивших в течение текущего дня.");
        System.out.print("Введите код товара:");
        sel = sc.nextInt();
        printList(getter.getOrdersExcludeItemToday(sel));
        
        System.out.println("\nСформировать новый заказ, состоящий из товаров,\n"
                         + "заказанных в текущий день. ");
        DBModifier mod = new DBModifier();
        int newOrder = ordersNum + 1 ;
        mod.insertOrder(new Order());
        for (int i : getter.getProductsToday()) {
            mod.insertItem(new Item(newOrder, i, 1));
        }
        printList(getter.getOrderInfo(newOrder));
        
        System.out.println("\nУдалить все заказы, в которых присутствует "
                         + "заданное количество заданного товара. ");
        System.out.print("Введите код товара:");
        int itemId = sc.nextInt();
        System.out.print("Введите количество товаров:");
        qty = sc.nextInt();
        ArrayList<Integer> delOrders = getter.getOrdersByItemsQty(itemId, qty);
        for(int id : delOrders) {
            System.out.println("  Удаляется заказ номер:" + id);
            mod.deleteOrder(id);
        }
        
        mod.closeStatement();
        mod.closeConnection();
        getter.closeStatement();
        getter.closeConnection();
    }
    
    public static void printList(ArrayList list) {
        if(list.isEmpty()) {
            System.out.println("<пусто>");
        } else {
            for(Object s : list) {
                System.out.println("   " + s.toString());
            }
        }
        
    }

}
