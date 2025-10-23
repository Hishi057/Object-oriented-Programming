import oop.ex2A1.Ticket;

class Main2A1 {
  public static void main(String[] args) {
    System.out.println("Max Reservation number is " + Ticket.MAX_NUM);
    Ticket t1 = Ticket.getNewTicket(3);
    Ticket t2 = Ticket.getNewTicket(5);
    for(int i = -1; i <= Ticket.MAX_NUM; ++i) {
      Ticket t = Ticket.getNewTicket(i);
      if(t != null) {
        System.out.println("Get " + t);
      }
      // Oh, we lose the ticket...
    }
    System.out.println(t1.toString());
    t1.release();
    System.out.println(t1.toString()); // now t1 is not valid
    for(int i = 0; i < Ticket.MAX_NUM; ++i) {
      Ticket t = Ticket.getNewTicket(i);
      if(t != null) {
        System.out.println("Get " + t);
      }
    }
  }
}
