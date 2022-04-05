package org.example;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {

        List<User> users = new ArrayList<>();       //List of users

        User user1 = new User("Andrey");
        user1.setBirthDate(new GregorianCalendar(1983, Calendar.DECEMBER, 11).getTime());
        user1.addAddress(new Address("Sumy", "Kovpak str", 41, 40031));
        user1.addAddress(new Address("Sumy", "Lushpy", 5, 40035));

        User user2 = new User("Taras");
        user2.setBirthDate(new GregorianCalendar(1987, Calendar.JULY, 11).getTime());
        user2.addAddress(new Address("Rivne", "Stolyichna ave", 8, 30512));

        User user3 = new User("Petro");
        user3.setBirthDate(new GregorianCalendar(1995, Calendar.JULY, 25).getTime());
        user3.addAddress(new Address("Lviv", "Hmelnytskogo", 11, 70535));

        users.add(user1);
        users.add(user2);
        users.add(user3);

        Address address = user1.getAddresses().get(0);

        //print list
        System.out.println("list of users:");
        for (User us:users) {
            System.out.println(us);
        }
        System.out.println("===========");

//---------------------------------------------------------------------- object Address
        System.out.println("address serializing/deserializing...");
        byte[] array1 = SerializeService.serialize(address);
        Address addrDes = (Address)SerializeService.deserialize(array1);

        System.out.println("Is objects the same? : " + addrDes.equals(address));
        System.out.println();

//---------------------------------------------------------------------- object User
        System.out.println("user1 serializing/deserializing...");
        byte[] array2 = SerializeService.serialize(user1);
        User user1Des = (User)SerializeService.deserialize(array2);

        System.out.println("Is objects the same? : " + user1Des.equals(user1));
        System.out.println();


        //---------------------------------------------------------------------- object List (WRONG)
        System.out.println("USERS serializing/deserializing...");
        byte[] array3 = SerializeService.serialize(users);
        List usersDes = (List)SerializeService.deserialize(array3);

        System.out.println("Is objects the same? : " + usersDes.equals(users));
        System.out.println();

        System.out.println("compare users");
        for (int i = 0; i < usersDes.size(); i++){
            System.out.println(usersDes.get(i));
            System.out.println(users.get(i));
            System.out.println();
        }

        //-----------------------------------------------------map serialize WRONG too
//
// Map<Integer, String>  map = new HashMap<>();
//        map.put(1, "one");
//        map.put(2, "two");
//        map.put(3, "three");
//
//        System.out.println(map);
//
//        System.out.println("map serializing/deserializing...");
//        byte[] arrayM = SerializeService.serialize(map);
//        Map mapDes = (Map)SerializeService.deserialize(arrayM);
//
//        System.out.println("Is objects the same? : " + map.equals(mapDes));
//        System.out.println(mapDes);

    }
}
