package entity;

import java.sql.Date;

public class Transaction {
        private int transactionId;
        private int ingameId;
        private Date transactionDate;
        private int total;
        private int userId;

        public Transaction() {
        }
        public Transaction(int ingameId, Date transactionDate, int total, int userId) {
            this.ingameId = ingameId;
            this.transactionDate = transactionDate;
            this.total = total;
            this.userId = userId;
        }
        public Transaction(int transactionId, int ingameId, Date transactionDate, int total, int userId) {
            this.transactionId = transactionId;
            this.ingameId = ingameId;
            this.transactionDate = transactionDate;
            this.total = total;
            this.userId = userId;
        }

        public int getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(int transactionId) {
            this.transactionId = transactionId;
        }

        public int getIngameId() {
            return ingameId;
        }

        public void setIngameId(int ingameId) {
            this.ingameId = ingameId;
        }

        public Date getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(Date transactionDate) {
            this.transactionDate = transactionDate;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

    @Override
    public String toString() {
        return "Transaction{" + "transactionId=" + transactionId + ", ingameId=" + ingameId + ", transactionDate=" + transactionDate + ", total=" + total + ", userId=" + userId + '}';
    }
    }

