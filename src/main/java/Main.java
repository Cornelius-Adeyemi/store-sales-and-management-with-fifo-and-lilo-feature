import com.adebisi.service_class.CashierService;
import com.adebisi.service_class.CustomerService;
import com.adebisi.service_class.ManagerService;
import com.adebisi.staff.Cashier;
import com.adebisi.customer.Customer;
import com.adebisi.staff.Manager;
import com.adebisi.store.Store;
import com.adebisi.enum_file.Category;
import com.adebisi.store.display.DisplayProducts;
import com.adebisi.store.display.DisplayCashier;

public class Main {
    public static void main(String[] args) {
       try {
           Store store = new Store(50000);
           store.employManager("Adebisi");
           ManagerService myManager = new ManagerService(store.currentManager);


           Cashier susan = myManager.hireCashier("Susan");
           CashierService susanS = new CashierService(susan);
           Cashier kola = myManager.hireCashier("kola");
           Cashier tola = myManager.hireCashier("Tola");
           Cashier bola = myManager.hireCashier("Bola");

           DisplayCashier displayCashier = new DisplayCashier(store);

           DisplayProducts displayProducts = new DisplayProducts(store);
           displayCashier.display();
           System.out.println("************");
              //  myManager.sackCashier("cashier04" );
            displayCashier.display();

           System.out.println("************");

           store.addproduct("mango", 20, 50, Category.FOODSTUFF);
           store.addproduct("orange", 20, 50, Category.FOODSTUFF);
           store.addproduct("beans", 20, 50, Category.FOODSTUFF);
           store.addproduct("plantain", 20, 50, Category.FOODSTUFF);
           store.addproduct("peanut", 20, 50, Category.FOODSTUFF);
           String path = "/Users/decagon/IdeaProjects/task_one copy/src/main/java/com/adebisi/external_file/productSheet.xlsx";
//        ReadAndUpdateFile readAndUpdateFile = new ReadAndUpdateFile(store);
//        readAndUpdateFile.readFile(path,store);

           displayProducts.displayproducts();


           CustomerService c =  new CustomerService( new Customer(store, "Adebis", 6000000));

           c.addToCart(1, 4);  // remain 50
           c.addToCart(2, 6);  // remain 40
           c.addToCart(3, 8);
           c.joinQueue();

           CustomerService c1 =  new CustomerService(new Customer(store, "Shola", 200000));
           c1.addToCart(1, 12);
           c1.addToCart(2, 10);
           c1.joinQueue();

           CustomerService c3 =  new CustomerService(new Customer(store, "kola", 60000));
           c3.addToCart(1, 4);
           c3.addToCart(2, 6);
           c3.addToCart(3, 8);
           c3.addToCart(4, 4);
           c3.addToCart(5, 6);
           c3.joinQueue();

           CustomerService c4 =  new CustomerService(new Customer(store, "matthew", 25000));
           c4.addToCart(1, 4);
           c4.joinQueue();



           susan.attendToCustomer(true);




       }
       catch(Exception e){
           System.err.println(e.getMessage());
       }
    }

}