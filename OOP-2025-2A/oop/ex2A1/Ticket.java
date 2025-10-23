package oop.ex2A1;

final public class Ticket {
  static final public int MAX_NUM = 10;
  static final private Ticket[] valid_tickets = new Ticket[MAX_NUM];
  final private int number;
  // The constructor cannot be used from outside of this class.
  private Ticket(int number) {
    this.number = number;
  }

  public boolean isValid() {
    return number >= 0 && number < MAX_NUM && valid_tickets[number] == this;
  }

  public void release() {
    if(isValid()) valid_tickets[number] = null;
    // otherwise this was already invalidated
  }

  static public Ticket getNewTicket(int number) {
    if(number < 0 || number >= MAX_NUM) {
      System.out.println("Invalid ticket number : " + number);
      return null;
    }
    if(valid_tickets[number] != null) {
      System.out.println("The ticket number is already owned : " + number);
      return null;
    }
    return (valid_tickets[number] = new Ticket(number));
  }

  @Override
  public String toString() {
    if(isValid()) return "Valid ticket : " + number;
    else return "Invalid ticket";
  }
}