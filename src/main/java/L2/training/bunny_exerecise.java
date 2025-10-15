package L2.training;

public class bunny_exerecise {
    public static void main(String[] args) {
        int s_bunny_money = 500;
        double d_bunny_money = 500/3.29;
        System.out.println("the bunny has:"+d_bunny_money+" dollars");
        double buyable_cakes = d_bunny_money/5;
        double money_left = d_bunny_money%5;
        double new_buyable_cakes = d_bunny_money/4;
        boolean can_buy_40 = new_buyable_cakes>=40;
        System.out.println(can_buy_40);

    }
}
