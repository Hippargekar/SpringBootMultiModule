package org.root.fluent.v2;

public class RechargeRequest {
    private String metroIcon;
    private int cardNumber;
    private double amount;
    private String promoCode;

    //private RechargeRequest(RechargeBuilder builder){
    private RechargeRequest(String metroIcon, int cardNumber, double amount, String promoCode) {
        this.metroIcon = metroIcon;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.promoCode = promoCode;
    }
    public interface MetroIcon {
        SmartCardNumber tapOnMetroIcon();
    }
    public interface SmartCardNumber {
        SmartCardAmount addCardNumber(int number);
    }
    public interface SmartCardAmount {
        ProceedToPay addAmount(double amount);
    }
    public interface ProceedToPay {
        Pay applyPromoCode(String promoCode);//is optional, you can skip it if you want.
        Pay proceedToPay();
    }
    public interface Pay {
        SmartCardBuild payAmount();
    }
    public interface SmartCardBuild {
        RechargeRequest build();
    }

    @Override
    public String toString() {
        return "RechargeRequest{" +
                "metroIcon='" + metroIcon + '\'' +
                ", cardNumber=" + cardNumber +
                ", amount=" + amount +
                ", promoCode='" + promoCode + '\'' +
                '}';
    }

    public static final class RechargeBuilder implements
            MetroIcon, SmartCardNumber, SmartCardAmount, ProceedToPay, Pay, SmartCardBuild {
        private String metroIcon;
        private int cardNumber;
        private double amount;
        private String promoCode;

        public RechargeBuilder() {
        }

        @Override
        public SmartCardNumber tapOnMetroIcon() {
            this.metroIcon = "Metro Icon";
            return this;
        }

        @Override
        public SmartCardBuild payAmount() {
            return this;
        }

        @Override
        public Pay applyPromoCode(String promoCode) {
            this.promoCode = promoCode;
            return this;
        }

        @Override
        public Pay proceedToPay() {
            return this;
        }

        @Override
        public ProceedToPay addAmount(double amount) {
            this.amount = amount;
            return this;
        }

        @Override
        public SmartCardAmount addCardNumber(int number) {
            this.cardNumber = number;
            return this;
        }

        @Override
        public RechargeRequest build() {
//			if (mb.length() > 0) {
//				throw new IllegalStateException(mb.toString());
//			}//return new RechargeRequest( this );
            return new RechargeRequest(metroIcon, cardNumber, amount, promoCode );
        }
    }
}
