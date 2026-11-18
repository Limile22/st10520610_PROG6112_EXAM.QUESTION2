public class ProductCalculations implements IProductCalculations {
        private double price;

        public ProductCalculations(double price) {
            this.price = price;
        }

        @Override
        public double GetVAT() {
            return price * 0.15;
        }

        @Override
        public double GetCommission() {
            return price * 0.05;
        }

        @Override
        public double GetTotal() {
            return price + GetVAT() + GetCommission();
        }
    }

