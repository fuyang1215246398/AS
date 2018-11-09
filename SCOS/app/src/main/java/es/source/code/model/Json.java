package es.source.code.model;

public  class Json extends Result {
    // 对象jsonString
    private String dataString;
    public Json() {

    }

    public Json(int RESULTCODE, String msg, String dataString) {
        super(RESULTCODE,msg);
        this.dataString = dataString;
    }
    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }
}