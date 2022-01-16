package com.practice.addressbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.practice.addressbook.exception.ContactNotFoundException;
import com.practice.addressbook.exception.DuplicateContactException;
import com.practice.addressbook.pojo.AddressBook;
import com.practice.addressbook.pojo.Contact;
import com.practice.addressbook.service.AddressBookService;

public class Application {

    HashMap<String, AddressBook> addressBookHashMap = new HashMap<>();

    AddressBookService service = new AddressBookService();

    public static void main(String[] args) {
        Application app = new Application();
        app.displayMenu();
    }

    public  void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n#############################");
        System.out.println("Please select 1an action");
        System.out.println("1. Add a person");
        System.out.println("2. Edit");
        System.out.println("3. Delete a person");
        System.out.println("4. Search a person");
        System.out.println("5. Create new Addressbook");
        System.out.println("6. Search by city and state");
        System.out.println("7. Quit");
        System.out.print(">>");
        int choice = scanner.nextInt();
        switch(choice) {
            case 1:
                try {
                    System.out.println("Please enter addressbook to be manupulated");
                    scanner.nextLine();
                    String addressBookName = scanner.nextLine();
                    if(!addressBookHashMap.containsKey(addressBookName)) {
                        System.out.println("Addressbook Not present");
                    }else {
                        service.addContact(addressBookHashMap.get(addressBookName));
                    }
                }catch (DuplicateContactException e) {
                    System.out.println(e.getMessage());
                }
                displayMenu();
                break;
            case 2:
                try {
                    System.out.println("Please enter addressbook to be manupulated");
                    scanner.nextLine();
                    String addressBookName = scanner.nextLine();
                    if(!addressBookHashMap.containsKey(addressBookName)) {
                        System.out.println("Addressbook Not present");
                    }else {
                        service.editContact(addressBookHashMap.get(addressBookName));
                    }
                }catch (ContactNotFoundException e) {
                    System.out.println(e.getMessage());
                }

                displayMenu();
                break;
            case 3:
                try {
                    System.out.println("Please enter addressbook to be manupulated");
                    scanner.nextLine();
                    String addressBookName = scanner.nextLine();
                    if(!addressBookHashMap.containsKey(addressBookName)) {
                        System.out.println("Addressbook Not present");
                    }
                    service.deleteContact(addressBookHashMap.get(addressBookName));
                }catch (ContactNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                displayMenu();
                break;
            case 4:
                try {
                    System.out.println("Please enter addressbook to be manupulated");
                    scanner.nextLine();
                    String addressBookName = scanner.nextLine();
                    if(!addressBookHashMap.containsKey(addressBookName)) {
                        System.out.println("Addressbook Not present");
                    }
                    service.displayContact(addressBookHashMap.get(addressBookName));
                }catch (ContactNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                displayMenu();
                break;
            case 5:
                try {
                    System.out.println("Please enter addressbook name");
                    scanner.nextLine();
                    String addressBookName = scanner.nextLine();
                    if(addressBookHashMap.containsKey(addressBookName)) {
                        System.out.println("Addressbook already present. Please choose different name");
                    }else {
                        addressBookHashMap.put(addressBookName,new AddressBook(addressBookName, new ArrayList<>()));
                    }
                }catch (ContactNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                displayMenu();
                break;

            case 6:
                try {
                    System.out.println("Please enter City and State to search");
                    scanner.nextLine();
                    String city = scanner.nextLine();
                    String state = scanner.nextLine();
                    List<Contact> res = new ArrayList<>();
                    addressBookHashMap.forEach((s, addressBook) -> addressBook.getContacts().forEach(
                            contact -> {
                                if(contact.getCity().equalsIgnoreCase(city) && contact.getState().equalsIgnoreCase(state)) {
                                    res.add(contact);
                                }
                            }
                    ));
                    System.out.println(res);
                }catch (ContactNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                displayMenu();
                break;
            default:
                break;
        }
        scanner.close();
    }




}
