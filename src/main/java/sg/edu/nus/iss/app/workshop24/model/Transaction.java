package sg.edu.nus.iss.app.workshop24.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Transaction {
  // Establish attributes
  @Size(min=8, max=8, message="Transaction ID must be 8 characters")
  private String transactionId;
  private String date;

  @NotNull(message="Account is mandatory")
  private Account fromAccount;

  @NotNull(message="Account is mandatory")
  private Account toAccount;

  @Min(value=10, message="Minimum transfer amount is $10")
  private Double amount;

  private String comments;

  // Generate getters and setters
  public String getTransactionId() {
    return transactionId;
  }
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public Account getFromAccount() {
    return fromAccount;
  }
  public void setFromAccount(Account fromAccount) {
    this.fromAccount = fromAccount;
  }
  public Account getToAccount() {
    return toAccount;
  }
  public void setToAccount(Account toAccount) {
    this.toAccount = toAccount;
  }
  public Double getAmount() {
    return amount;
  }
  public void setAmount(Double amount) {
    this.amount = amount;
  }
  public String getComments() {
    return comments;
  }
  public void setComments(String comments) {
    this.comments = comments;
  }

  public JsonObject toJSON(){
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    LocalDateTime date = LocalDateTime.now();
    return Json.createObjectBuilder()
                .add("transaction_id",transactionId)
                .add("date", formatter.format(date))
                .add("from_account", fromAccount.getAccountId())
                .add("to_account",toAccount.getAccountId())
                .add("amount",amount)
                .build();
  }

}
