package com.transcendence.universe.util.bitmap;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.util.Log;

import com.transcendence.universe.service.MyApplicaton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuf on 16/3/7.
 */

public class FileUtils {
    private static String tag = "FileUtils";
    public final static String VIDEO_FORMAT_MP4 = ".mp4";
    private static final String SDCARD_ROOT_PATH = Environment
            .getExternalStorageDirectory().getAbsolutePath();
    private static final String CATCHES_ROOT_PATH = "/Catches/";
    private static final String CATCHES_CHAT_PATH = "/Catches/Chat/";
    public static File CatchesRootPath = new File(SDCARD_ROOT_PATH + CATCHES_ROOT_PATH);
    public static File CatchesChatPath = new File(SDCARD_ROOT_PATH + CATCHES_CHAT_PATH);
    private static final String ImageTemp = "/Catches/ImageTemp/";

    public static final String TKING_PICTURES = SDCARD_ROOT_PATH + "/Catches/pictures/";
    public static final String IMAGE_PATH = SDCARD_ROOT_PATH + ImageTemp;
    //聊天会话图片
    public static final String CHAT_SESSION_CACHE = SDCARD_ROOT_PATH + "/Catches/Chat/SessionCache";
    public static final String VIDEO_THUMBNAIL = SDCARD_ROOT_PATH + "/Catches/VideoThumbnail";
    public static final String CHAT_VOICE = SDCARD_ROOT_PATH + "/Catches/voice";
    public static final String CHAT_VIDEO = SDCARD_ROOT_PATH + "/Catches/video";
	public static final String CHAT_SMALLVOICE = SDCARD_ROOT_PATH+"/Catches/smallvoice";
    //聊天图片
    public static final String CHAT_VAR0 = "im";
    public static final String CHAT_VAR1 = "catches";

    public static final String NOTE_PATH = SDCARD_ROOT_PATH + "/Catches/Note/";
    public static final String ACTIVITY_PATH = SDCARD_ROOT_PATH + "/Catches/Activity/";
    static FileUtils instance;
    static Context context;

    /*
     * 单例模式中获取唯一的CursorUtility实例
     */
    public static FileUtils getInstance() {
        if (null == instance) {
            context = MyApplicaton.getInstance().getContext();
            instance = new FileUtils();
            instance.createFile(SDCARD_ROOT_PATH, "/Catches/pictures/");
            instance.createFile(SDCARD_ROOT_PATH, ImageTemp);
        }
        return instance;
    }

    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static File getAvailableCacheDir() {
        if (isExternalStorageWritable()) {
            return MyApplicaton.instance.getExternalCacheDir();
        } else {
            return MyApplicaton.instance.getCacheDir();
        }
    }


    /**
     * 从assets 文件夹中获取文件并读取数据
     */
    public static String getFromAssets(String fileName, Context context) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void deleteChatFile(String userNumber) {
        File file=new File(CHAT_SESSION_CACHE+"/"+userNumber+"/");
            delete(file);
    }


    /**
     * 递归删除文件及文件夹
     */
    public static void delete(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i]);
            }
            file.delete();
        }
    }

    /**
     * 创建文件
     */
    public File createFile(String path, String fileName) {
        File mVecordFile = creatFile(path, fileName);
        return mVecordFile;
    }

    public File creatFile(String Path, String Name) {
        File file = new File(Path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(Path, Name);
    }

    /**
     * 获取单个文件的MD5值！
     *
     * @param file
     * @return
     */

    public String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    public boolean copyFile(File oldfile, String newPath, String newName) {
        File mTmpFile = createFile(newPath, newName);
        try {
            int bytesum = 0;
            int byteread = 0;
            if (oldfile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldfile.getAbsolutePath()); // 读入原文件
                FileOutputStream fs = new FileOutputStream(mTmpFile.getAbsolutePath());
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // 字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                Log.i(tag, "复制单个文件成功");
                return true;
            }
        } catch (IOException e) {
            Log.i(tag, "复制单个文件操作出错");
            e.printStackTrace();
        }
        return false;
	}





    public long getFileSizes(File f) {//取得文件大小
        long s = 0;
        if (f.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(f);
                s = fis.available();
                return s;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            return 0;
        }
        return f.length();
    }


    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "K";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "M";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "G";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "T";
    }

    public String getTimeFormat(long duration) {
        long seconds = duration / 1000;
        Log.i("shenlong", "seconds=" + seconds);
        long hour = seconds / 3600;
        long minute = seconds % 3600 / 60;
        long second = seconds % 60;

        String hourStr = hour + "";
        String minuteStr = minute+"";
        if (hour == 0) {
            hourStr = "00";
        }
        if (minute == 0) {
            minuteStr = "00";
        }
        return hourStr + ":" + minuteStr + ":" + second;
    }

    public boolean saveToSDCard(String fileName, String content) {

        // judge weather the SDCard exits,and can be read and written
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return false;
        }

        FileOutputStream fileOutputStream = null;
        File file = new File(fileName);
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String read(String fileName) {
        StringBuffer sb = new StringBuffer();
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
            String line; // 用来保存每行读取的内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            line = reader.readLine(); // 读取第一行
            while (line != null) { // 如果 line 为空说明读完了
                sb.append(line); // 将读到的内容添加到 buffer 中
                sb.append("\n"); // 添加换行符
                line = reader.readLine(); // 读取下一行
            }
            reader.close();
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
