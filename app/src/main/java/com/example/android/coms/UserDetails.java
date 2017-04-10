package com.example.android.coms;

/**
 * Created by Knayak on 20/03/2017.
 */

public class UserDetails {
     static String name, username, password, email, address;
     static long user_id, mob_num;

    public static long getUser_id() {
        return user_id;
    }

    public static String getName() {
        return name;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static long getMob_num() {
        return mob_num;
    }

    public static String getEmail() {
        return email;
    }

    public static String getAddress() {
        return address;
    }

}
