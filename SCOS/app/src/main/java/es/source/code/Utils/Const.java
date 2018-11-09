package es.source.code.Utils;

import es.source.code.*;


public class Const {

    // IntentKey
    public static class IntentKey {
        public static final String FROM = "from";
        public static final String LOGIN_STATUS = "login_status";
        public static final String FOOD_POSITION = "food_position";
        public static final String NOTIFICATION_ID = "notification_id";
    }

    // IntentAction
    public static class IntentAction {
        // 开机action
        public static final String BOOT = "android.intent.action.BOOT_COMPLETED";
        // 关闭notification
        public static final String CLOSE_NOTIFICATION = "scos.intent.action.CLOSE_NOTIFICATION";
    }

    // URL
    public static class URL {
        // 基本路径
        public static final String BASE = "http://192.168.191.1:8080/SCOSServer/";
        // 登录
        public static final String LOGIN = "Login";
        // 菜单列表
        public static final String FOOD = "Food";
    }

    // XML Element ID
    public static class ELEMENT_ID {
        // 结果
        public static final String RESULT = "Result";

        // 请求结果码
        public static final String RESULT_CODE = "RESULTCODE";
        // 消息
        public static final String MESSAGE = "message";
        // 数据XML String值
        public static final String DATA_STRING = "dataString";

        // 食品
        public static final String FOOD = "food";
        // 食品名称
        public static final String FOOD_NAME = "foodName";
        // 价格
        public static final String PRICE = "price";
        // 库存
        public static final String STORE = "store";
        // 是否订单
        public static final String ORDER = "order";
        // 资源ID
        public static final String IMGID = "imgId";
    }
}
