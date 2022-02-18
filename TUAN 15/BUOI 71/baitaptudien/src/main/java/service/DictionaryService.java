package service;

import java.util.HashMap;

public class DictionaryService {

    public   static  HashMap<String, String> dictionary;
    static {
        dictionary = new HashMap<>();
        dictionary.put("hello", "xin chào");
        dictionary.put("book", "cuốn sách");
        dictionary.put("how", "như nào");
        dictionary.put("desk", "bàn làm việc");
        dictionary.put("english", "tiếng anh");
    }

}
