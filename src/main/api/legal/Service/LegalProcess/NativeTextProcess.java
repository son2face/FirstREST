package legal.Service.LegalProcess;

import java.util.Arrays;

/**
 * Created by Son on 4/8/2017.
 */
public class NativeTextProcess  extends  TextProcess{
    public NativeTextProcess(Object data){
        super(data);
        String[] line_arr = ((String)data).split("\\r\\n|\\n|\\r");
        this.lines = Arrays.asList(line_arr);
    }
}
