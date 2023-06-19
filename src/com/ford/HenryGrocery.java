import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HenryGroceryShop {

    static Map<String, Double> itemsCost = new HashMap<>();
    static Map<String, Integer> stocks = new HashMap<>();
    static double price;
    static double offerprice;

    public static void main(String[] args) {
        stocks = new HashMap<>();
        getItemsCost();
        Scanner scn= new Scanner(System.in);
        System.out.println("Enter the number of products :");
        int prodCounts = scn.nextInt();
        String prodName;
        Integer quantity;
        for(int loop=0;loop<prodCounts;loop++){
            System.out.println("Enter ProductName :");
            prodName = scn.next();
            System.out.println("Enter Quantity:");
            quantity = scn.nextInt();
            stocks.put(prodName,quantity);
        }
        System.out.println("Enter the Purchase Date[yyyy-MM-DD]:");
        String purchaseDate = scn.next();
        LocalDate purchaseDateFin = LocalDate.parse(purchaseDate, DateTimeFormatter.ofPattern("yyyy[-MM[-dd]]"));
        getTotalPrice(stocks);
        getOfferAmount(stocks,purchaseDateFin);
        double finalPrice = price - offerprice;
        BigDecimal fin = new BigDecimal(finalPrice).setScale(2, RoundingMode.HALF_UP);
        double offerprice = fin.doubleValue();
        System.out.println("Bill Amount :" + offerprice);

    }

    static void getTotalPrice(Map totalprice) {
        price = 0.0;
        BigDecimal bd;
        for (Object key : totalprice.keySet()) {
            price += (Integer) totalprice.get(key) * itemsCost.get(key);
            bd = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
            price = bd.doubleValue();

        }

    }

    static void getOfferAmount(Map stockscalc,LocalDate dateCalc) {
        offerprice = 0.0;
        BigDecimal of;
        LocalDate startDatesoup = LocalDate.now().minusDays(1);
        LocalDate endDatesoup = startDatesoup.plusDays(7);
        LocalDate startDateapple = LocalDate.now().plusDays(3);
        LocalDate endDateapple = startDateapple.withDayOfMonth(startDateapple.getMonth().length(startDateapple.isLeapYear()));
        boolean isSoupoffer = (dateCalc.isAfter(startDatesoup) && dateCalc.isBefore(endDatesoup));
        boolean isAppleoffer = (dateCalc.isAfter(startDateapple) && dateCalc.isBefore(endDateapple));
        for (Object keyval : stockscalc.keySet()) {
            if (keyval.equals("soup")) {
                if((Integer) stockscalc.get("soup") > 1 && isSoupoffer) {
                    double breadoffer = (int)((Integer) stockscalc.get("soup") *50.0/100.0) * 0.40;
                    of = new BigDecimal(breadoffer).setScale(2, RoundingMode.HALF_UP);
                    offerprice += of.doubleValue();
                }

            }
            if (keyval.equals("apple") && isAppleoffer) {
                double appleoffer = (Integer) stockscalc.get("apple") * 0.01;
                of = new BigDecimal(appleoffer).setScale(2, RoundingMode.HALF_UP);
                offerprice += of.doubleValue();
            }
        }

    }


    private static Map getItemsCost() {
        itemsCost = new HashMap<>();
        itemsCost.put("soup", 0.65);
        itemsCost.put("bread", 0.80);
        itemsCost.put("milk", 1.30);
        itemsCost.put("apple", 0.10);
        return itemsCost;
    }
}
