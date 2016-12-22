package asynnet.com.asynnet;

/**
 * Created by miccall on 2016/11/6.
 */
public class Bean {
    /**
     * code : 200
     * message : success
     * date : {"id":"9","wendu":"21.0","shidu":"46.0","yanwu":"23","time":"11:48:17\n"}
     */

    private int code;
    private String message;
    /**
     * id : 9
     * wendu : 21.0
     * shidu : 46.0
     * yanwu : 23
     * time : 11:48:17

     */

    private DateBean date;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", date=" + date.toString() +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DateBean getDate() {
        return date;
    }

    public void setDate(DateBean date) {
        this.date = date;
    }

    public static class DateBean {
        @Override
        public String toString() {
            return "DateBean{" +
                    "id='" + id + '\'' +
                    ", wendu='" + wendu + '\'' +
                    ", shidu='" + shidu + '\'' +
                    ", yanwu='" + yanwu + '\'' +
                    ", time='" + time + '\'' +
                    '}';
        }

        private String id;
        private String wendu;
        private String shidu;
        private String yanwu;
        private String time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getShidu() {
            return shidu;
        }

        public void setShidu(String shidu) {
            this.shidu = shidu;
        }

        public String getYanwu() {
            return yanwu;
        }

        public void setYanwu(String yanwu) {
            this.yanwu = yanwu;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
