package esd.scos.model;

public class Result {
    // 状态值
    private  int RESULTCODE;
    // 消息
    private  String msg;
    // 携带对象字符串
    private  String dataString;

    public Result(int RESULTCODE, String msg, String dataString) {
        this.RESULTCODE = RESULTCODE;
        this.msg = msg;
        this.dataString = dataString;
    }

    public int getRESULTCODE() {
        return RESULTCODE;
    }

    public void setRESULTCODE(int RESULTCODE) {
        this.RESULTCODE = RESULTCODE;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }
}
