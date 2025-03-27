package org.root.cast.reference;

import java.util.HashMap;
import java.util.Map;

public class PassByReference {

    public static void main(String[] args) {
        Map<String, String> data = new HashMap<>();
        data.put("status", "success");
        data.put("message","fetched data successfully");
        System.out.println(data);
        new PassByReference().changeReference(data);
        System.out.println(data);
    }

//    public void getData(Map<String, String> input){
//        input.put("status", "failed"); // This change affects the object that the reference points to
//    }

    public void changeReference(Map<String, String> input){
        input = new HashMap<>();// This change will not affect the original reference outside this method
        input.put("status", "failed");
    }
}
