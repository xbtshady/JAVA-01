import java.io.*;
import java.lang.reflect.Method;

public class Hello extends ClassLoader {

    public static void main(String[] args) throws Exception {
        try {
            Object o = new Hello().findClass("Hello").newInstance();
            Method m = o.getClass().getMethod("hello");
            m.invoke(o);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
      byte[] data = getBytes("Hello.xlass");
      for(int i = 0; i< data.length; i++){
          data[i] = (byte)(255 - data[i]);
      }
      return super.defineClass(name,data,0, data.length);
    }

    /**
     * 获取指定文件的byte数组
     * @param filePath
     * @return
     */
    private byte[] getBytes(String filePath){

        byte[] buffer = null;
        try {
            File directory  = new File("");
            File file = new File(directory.getCanonicalPath() + "/" + filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}