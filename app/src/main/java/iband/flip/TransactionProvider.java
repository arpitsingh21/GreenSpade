package iband.flip;

/**
 * Created by dell on 26-11-2017.
 */

public class TransactionProvider {

String name,date,mobile,amount,transaction;

    public TransactionProvider(String name, String date, String mobile, String amount, String transaction) {
        this.name = name;
        this.date = date;
        this.mobile = mobile;
        this.amount = amount;
        this.transaction = transaction;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
