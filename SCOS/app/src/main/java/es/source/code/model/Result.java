package es.source.code.model;

import java.util.List;

public class Result {
    // 结果码
    private int RESULTCODE;
    // 携带信息
    private String msg;
    public Result(){
    }
    public Result(int RESULTCODE, String msg) {
        this.RESULTCODE = RESULTCODE;
        this.msg = msg;
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
}




