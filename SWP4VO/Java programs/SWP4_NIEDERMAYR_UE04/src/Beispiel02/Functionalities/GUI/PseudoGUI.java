package Beispiel02.Functionalities.GUI;

import Beispiel02.Functionalities.Dao.Implementation.PersonDaoJdbc;
import Beispiel02.Functionalities.Domain.Person;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class PseudoGUI {

    private PersonDaoJdbc jdbc = new PersonDaoJdbc();

    public void startGUI() throws Exception {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        boolean stop = false;

        while (!stop) {
            //throws the choices at the console
            printChoices();

            //wait for input
            String choice = reader.readLine();

            switch (choice.toUpperCase()) {
                case "ADD":
                    if (addPerson()) {
                        System.out.println("Insertion was successful!");
                    } else {
                        System.out.println("I'm sorry, there was an error.");
                    }
                    break;
                case "DELETE":
                    if (deletePerson()) {
                        System.out.println("Deletion was successful!");
                    } else {
                        System.out.println("The person either does not exist or there was an error.");
                    }
                    break;
                case "SHOWALL":
                    if (showAll()) {
                        System.out.println("Here are all the persons!");
                        System.out.println("----------");
                    } else {
                        System.out.println("There seem to be no persons in the list - or there was an error.");
                    }
                    break;
                case "SHOWONE":
                    if (showOne()) {
                        System.out.println("There you are!");
                        System.out.println("----------");
                    } else {
                        System.out.println("The person either does not exist or there was an error.");
                    }
                    break;
                case "STOP":
                    stop = true;
                    break;
                default: //user wants to fuck me over
                    System.out.println("No choice was chosen. Please retry.");
                    break;
            }

        }

        reader.close();
    }

    //a Person is created here and given to PersonDaoJdbc to work with
    private boolean addPerson() throws Exception {
        String firstName;
        String lastName;
        String city;
        int postalCode;
        String address;
        String phoneNumber;

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.println("----- ADDING A PERSON -----");
        System.out.println("Enter the First Name.");
        firstName = reader.readLine();
        System.out.println("Enter the Last Name.");
        lastName = reader.readLine();
        System.out.println("Enter the City.");
        city = reader.readLine();
        System.out.println("Enter the PostalCode.");
        postalCode = Integer.valueOf(reader.readLine());
        System.out.println("Enter the Address.");
        address = reader.readLine();
        System.out.println("Lastly, enter the Phone Number.");
        phoneNumber = reader.readLine();

        //new person is created
        Person p = new Person(firstName, lastName, city, postalCode, address, phoneNumber);

        //give the person to the needed PersonDaoJdbc
        //create returns true or false, depending if the SQL statement was successful
        return (jdbc.create(p));
    }

    private boolean deletePerson() throws Exception {
        String id;

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.println("----- DELETING A PERSON -----");
        System.out.println("Please enter the ID of the Person you'd like to delete.");
        id = reader.readLine();

        //program will not check if the person exists!
        //calls the needed function from PersonDaoJdbc
        return (jdbc.delete(Integer.valueOf(id)));
    }

    private boolean showOne() throws Exception {
        String id;

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.println("----- SHOWING A PERSON -----");
        System.out.println("Please enter the ID of the Person you'd like to see.");
        id = reader.readLine();

        Person p = jdbc.readForIdentity(Integer.valueOf(id));

        if (p != null) {
            //print it pretty on the console
            printPerson(p);

            return true;
        } else {
            return false;
        }
    }

    private boolean showAll() throws Exception {
        System.out.println("----- SHOWING ALL PERSONS -----");

        List<Person> personList = jdbc.readAll();

        //the list is not empty
        if (personList != null) {
            for (Person p : personList) {
                printPerson(p);
            }

            return true;
        } else {
            return false;
        }
    }

    //print the variation of choices onto the console
    private void printChoices() {
        System.out.println("Good day. What would you like to do?");
        System.out.println("Type 'Add' a Person to the database.");
        System.out.println("Type 'Delete' to start the deletion process.");
        System.out.println("Type 'ShowAll' to show all Persons in the database.");
        System.out.println("Type 'ShowOne' to show one specific Person.");
        System.out.println("Type 'STOP' to exit the GUI.");
    }

    private void printPerson(Person p) {
        System.out.println("~~~~~~~~~~");
        System.out.println(p.getId() + ": " + p.getFirstName() + " " + p.getLastName());
        System.out.println(p.getAddress() + ", " + p.getCity() + ", " + p.getPostalCode());
        System.out.println(p.getPhoneNumber());
        System.out.println("~~~~~~~~~~");
    }

}
