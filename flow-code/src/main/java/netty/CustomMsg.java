package netty;

import java.util.Arrays;

public class CustomMsg {
    short type1;
    short type2;
    int len1;
    int len2;
    byte[] data1 ;
    byte[] data2 ;

    public CustomMsg(short type1, short type2, int len1, int len2, byte[] data1, byte[] data2) {
        this.type1 = type1;
        this.type2 = type2;
        this.len1 = len1;
        this.len2 = len2;
        this.data1 = data1;
        this.data2 = data2;
    }

    public void setType1(short type1) {
        this.type1 = type1;
    }

    public void setType2(short type2) {
        this.type2 = type2;
    }

    public void setLen1(int len1) {
        this.len1 = len1;
    }

    public void setLen2(int len2) {
        this.len2 = len2;
    }

    public void setData1(byte[] data1) {
        this.data1 = data1;
    }

    public void setData2(byte[] data2) {
        this.data2 = data2;
    }

    public short getType1() {
        return type1;
    }

    public short getType2() {
        return type2;
    }

    public int getLen1() {
        return len1;
    }

    public int getLen2() {
        return len2;
    }

    public byte[] getData1() {
        return data1;
    }

    public byte[] getData2() {
        return data2;
    }

    @Override
    public String toString() {
        return "CustomMsg{" +
                "type1=" + type1 +
                ", type2=" + type2 +
                ", len1=" + len1 +
                ", len2=" + len2 +
                ", data1=" + Arrays.toString(data1) +
                ", data2=" + Arrays.toString(data2) +
                '}';
    }
}
