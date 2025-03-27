package org.root.fluent.v2;

public class RechargeRequestTest {
    public static void main(String[] args) {
        RechargeRequest rechargeWithPromoCode = new RechargeRequest
                .RechargeBuilder()
                .tapOnMetroIcon()
                .addCardNumber( 123 )
                .addAmount( 256 )
                .applyPromoCode("Monthly52")
                .payAmount()
                .build();
System.out.println(rechargeWithPromoCode);
        RechargeRequest rechargeWithOutPromoCode = new RechargeRequest
                .RechargeBuilder()
                .tapOnMetroIcon()
                .addCardNumber( 123 )
                .addAmount( 256 )
                .proceedToPay()
                .payAmount()
                .build();
        System.out.println(rechargeWithOutPromoCode);
    }
}
