public class Order {
    public enum OrderStatus { 
        PLACED (1),
        READY (2) {
            public boolean isReady() {
                return true;
            }
        },
        ENROUTE (3),
        DELIVERED (4);
        
        int sequence;
        
        OrderStatus(int sequence) {
            this.sequence = sequence;
        }
        
        public int getSequence() {
            return sequence;
        }
        
        public boolean isReady() {
            return false;
        }
    }
}
