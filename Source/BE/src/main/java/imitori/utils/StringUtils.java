package imitori.utils;

import java.util.ArrayList;

public class StringUtils {
    public int StringArrayListSearch(ArrayList<String> input, String token) {
        int res = -1;
        for (int i = 0; i < input.size(); i++) {
            if (token.equals(input.get(i))) {
                res = i;
                break;
            }
        }
        return res;        
    }
    public ArrayList<String> removeDuplicate(ArrayList<String> input) {
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (StringArrayListSearch(res, input.get(i)) == -1) {
                res.add(input.get(i));
            }
        }
        return res;
    }
}