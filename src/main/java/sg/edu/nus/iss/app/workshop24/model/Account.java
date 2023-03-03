package sg.edu.nus.iss.app.workshop24.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Account {
  // Establish Attributes
  @NotNull (message="Account ID cannot be left empty")
  @Size(min=10, max=10, message="Account ID must have 10 characters")
  private String accountId;

  @NotNull(message="Account name cannot be left empty")
  private String name;

  @Min(value=0, message="Balance must be positive")
  private Double balance;

  // Generate getters and setters
  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }
  
}
