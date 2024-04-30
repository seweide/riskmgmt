package com.haier.hairy.rmp.util;/**
 * Created by seweide
 * on @date 2019.7.30 14:26.
 */

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author heweiwen
 * @Description //TODO
 * @Date 2019.7.30
 * @Param
 * @return
 **/
@Slf4j
public class ShellUtils {
    /**
     * 运行shell脚本
     *
     * @param shell 需要运行的shell脚本
     */
    public static void execShell(String shell) {
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec(shell);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 运行shell
     *
     * @param shStr 需要执行的shell
     * @return
     * @throws IOException 注:如果sh中含有awk,一定要按new String[]{"/bin/sh","-c",shStr}写,才可以获得流.
     */
    public static List<String> runShell(String shStr) throws Exception {
        List<String> strList = new ArrayList<String>();

        Process process;
        process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", shStr}, null, null);
        InputStreamReader ir = new InputStreamReader(process
                .getInputStream());
        LineNumberReader input = new LineNumberReader(ir);
        String line;
        process.waitFor();
        while ((line = input.readLine()) != null) {
            strList.add(line);
        }
        return strList;
    }

    /**
     * @param shellName
     * @param shellPath
     * @param para
     * @return
     * @throws IOException
     */
    public static String copyDatabase(String shellName, String shellPath, String... para) throws IOException {

        JSONObject result = new JSONObject();

        log.info("request code:para=" + para[0] + " shellPath=" + shellPath);

        if (para.length < 0 || StringUtils.isBlank(shellPath)) {
            result.put("code", "0");
            result.put("msg", "para/shellPath is Not Blank");
            return result.toJSONString();
        }

        //脚本路径
        String scriptPath = shellPath + shellName;
        String[] cmd = new String[]{scriptPath};
        //为了解决参数中包含空格
        cmd = ArrayUtils.addAll(cmd, para);

        int runningStatus = 0;
        String s = null;
        StringBuffer sb = new StringBuffer();
        try {
            //解决脚本没有执行权限
            ProcessBuilder builder = new ProcessBuilder("/bin/chmod", "755", scriptPath);
            Process process = builder.start();
            int rc = process.waitFor();
            log.info("execute chmod result :rc=" + rc);

            //start shell runing
            Process ps = Runtime.getRuntime().exec(cmd);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(ps.getErrorStream()));
            while ((s = stdInput.readLine()) != null) {
                log.info("shell log info ...." + s);
                sb.append(s);
            }
            while ((s = stdError.readLine()) != null) {
                log.error("shell log error...." + s);
                sb.append(s);
            }
            try {
                runningStatus = ps.waitFor();
            } catch (InterruptedException e) {
                runningStatus = 1;
                log.error("lording shell execute writer times，error...", e);
                sb.append(e.getMessage());
            }
            closeStream(stdInput);
            closeStream(stdError);
        } catch (Exception e) {
            log.error("shell execute writer，error...", e);
            sb.append(e.getMessage());
            runningStatus = 1;
        }
        log.info("runningStatus = " + runningStatus);
        if (runningStatus == 0) {
            //成功
            result.put("code", "1");
            result.put("msg", "成功");
            return result.toJSONString();
        } else {
            result.put("code", "0");
            result.put("msg", "Call shell execute writer error..." + sb.toString());
            return result.toJSONString();
        }
    }

    private static void closeStream(BufferedReader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (Exception e) {
            reader = null;
        }
    }


    public static void main(String[] args) {

    }


}
