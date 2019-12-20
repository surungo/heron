package AWT;

public final class Chrono1{
    private long begin, end;
 
    public void start(){
        begin = System.currentTimeMillis();
    }
 
    public void stop(){
        end = System.currentTimeMillis();
    }
 
    public long getTime() {
        return end-begin;
    }
 
    public long getMilliseconds() {
        return end-begin;
    }
 
    public double getSeconds() {
        return (end - begin) / 1000.0;
    }
 
    public double getMinutes() {
        return (end - begin) / 60000.0;
    }
 
    public double getHours() {
        return (end - begin) / 3600000.0;
    }
 
    public static void main(String[] arg) {
        Chrono1 ch = new Chrono1();
 
        ch.start();
        for (int i = 1;i<10000000;i++) {}
        ch.stop();
        System.out.println(ch.getTime());
 
        ch.start();
        for (int i = 10000000;i>0;i--) {}
        ch.stop();
        System.out.println(ch.getTime());
    }
}