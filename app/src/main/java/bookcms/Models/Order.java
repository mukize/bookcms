package bookcms.Models;

import java.sql.Date;

public class Order {

  Long id;
  String firstName;
  String lastName;
  String email;
  String address;
  String suburb;
  String postalCode;
  String billingAddress;
  String billingSuburb;
  String billingPostalCode;
  String status;
  Date createdAt;

}
