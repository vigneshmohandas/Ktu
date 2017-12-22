import java.util.HashMap;

/**
 * Created by asnimansari on 22/12/17.
 */

public class GeneralData {


    public static HashMap<String,String> branchAndCode;
    public static String getBranchCode(String branchShort){

        branchAndCode = new HashMap<>();

        branchAndCode.put("CSE","r");
        branchAndCode.put("MECH","m");
        return branchAndCode.get(branchShort);



    }
}
