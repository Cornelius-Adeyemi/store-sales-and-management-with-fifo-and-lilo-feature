package com.adebisi.customer;

import com.adebisi.interface_package.IPurchaseProduct;
import com.adebisi.store.Product;
import com.adebisi.store.Store;
import com.adebisi.enum_file.Availability;

import java.util.ArrayList;

public class Customer implements Comparable<Customer>{

    Store store;
    private int moneyOwned;
    private int totalCost=0;
    public final String name;
    private boolean buyChecker =false;

    private ArrayList<IPurchaseProduct> cart = new ArrayList<>();


    public Customer( Store store ,String name, int moneyOwned){
        this.name = name;
        this.moneyOwned = moneyOwned;
        this.store = store;
    }


    public int getMoneyOwned() {
        return moneyOwned;
    }

    public void setMoneyOwned(int moneyOwned) {
        this.moneyOwned -= moneyOwned;
    }



    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void setBuyChecker(boolean buyChecker) {
        this.buyChecker = buyChecker;
    }

//    public void setCart(ArrayList<PurchaseProduct> cart) {
//        this.cart = cart;
//    }



    public int getTotalCost() {
        return totalCost;
    }

    public boolean isBuyChecker() {
        return buyChecker;
    }

    public ArrayList<IPurchaseProduct> getCart() {
        return cart;
    }



   public void clearCart(){
      this.cart.removeAll(this.cart);
   }


   public void addToCart(int productNumber, int quantity ) throws  Exception{

       ArrayList<Product> productList = store.productList;
       Product purchaseProductDetails = productList.get((productNumber-1));

       double price= purchaseProductDetails.price;
       double total = price * quantity;
       if(!(purchaseProductDetails.Out_Of_Stock == Availability.OUT_OF_STOCK)) {
           PurchaseProduct r = null;
           if (total <= moneyOwned && purchaseProductDetails.quanity >= quantity) {
                buyChecker = true;
                totalCost += total;


              purchaseProductDetails.quanity -= quantity;
              purchaseProductDetails.Out_Of_Stock = (purchaseProductDetails.quanity ==0)? Availability.OUT_OF_STOCK :Availability.IN_STOCK;

               r = new PurchaseProduct(purchaseProductDetails.name, quantity, (int) total, (int) price);
               cart.add(r);

           } else {

               if (total >= moneyOwned) {
                   throw new Exception("You don't have enough money to carry out this transaction");
               } else {
                   throw new Exception("We only have " + purchaseProductDetails.quanity + " quanty of" + purchaseProductDetails.name);
               }
           }
       }else{
           throw new Exception(purchaseProductDetails.name+" is out of stock");
       }


   }


    @Override
    public String toString() {
        return "{ Name: " +name +", " + "Balance: " + moneyOwned +  " }";
    }

    public void displayCartDetails(){
       for(IPurchaseProduct x: cart){
           System.out.println(x);
       }
       System.out.println("  ");
        System.out.println("Total cost         \s \t\t\t\t\t\t\s\s\s\s$" + totalCost);
        System.out.println("Customer Change    \s \t\t\t\t\t\t\s\s\s\s$" + moneyOwned);
    }

    public void joinQueue() throws Exception{
        if(buyChecker){
       store.getFifoQueue().add(this);
       store.getByQuantityQueue().add(this);
        }else{

            throw new Exception("You have to have to cart before you can join the queue");
        }

    }

    @Override
    public int compareTo(Customer o) {
        return o.getCart().size() - this.getCart().size();
    }

    private class PurchaseProduct implements IPurchaseProduct {
        public String productName;
        public int quantitPurchase;
        public int unitPrice;

        public int totalPrice;

        PurchaseProduct(String productName,int quantitPurchase, int totalCost, int unitPrice){
            this.productName = productName;
            this.quantitPurchase = quantitPurchase;
            this.totalPrice = totalCost;
            this.unitPrice = unitPrice;
        }


        @Override
        public String toString() {
            return productName + "\t\t\t" + "$"+ unitPrice +"/one" + "\t\t\t" + quantitPurchase+"qty" +"\t\t\t"+ "$"+totalPrice;
        }
    }
}
