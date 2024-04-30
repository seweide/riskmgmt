import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.haier.hairy.rmp.util.HryGatewayUtils;
import com.haier.hairy.rmp.util.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Xie Hao
 * @Create: 2019-07-23 10:49
 **/
public class GatewayTest {
    // 公钥
    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDApKb3nMtDP+yIern1NZgRK8H7NgEI/oGZ6Om/Ls7uuYDJgC8Qz0vxYKaNclFP0mYB6+MaJDC/UmD9Q/7csGh5Xp5tqR4fRRpgkWxkKquXQ0z7LxLmfVJ7Mt2bxm9pdv4v3D2eE0oy6DSq1A3R3KEHnSVnQ9jDVfE0erVBx7SCRwIDAQAB";
    // 商户密钥(邮件申请)
    private static final String secret = "ODVhMTU2ZGQtZTg0My00NzUxLWEwNDUtOTZiYTZjYzc1N2Zh";
    // 商户ID
    private static final String partnerId = "8f302e14a1c6e";
    // Gateway地址
    private static final String url = "http://10.100.14.1:9005/gateway/wanlian/process";

    public static void main(String[] args) throws Exception {


        // 调用服务名
        String service = "wanlianFacade.riskWarning";
        // 业务参数
        Map<String, String> bizData = new HashMap<>(3);
        bizData.put("channel", "wlzs");
        bizData.put("api", "wlzs.risk.warning");
        bizData.put("corpName", "六安喜洋洋电器贸易有限公司");
        String bizdata = JSON.toJSONString(bizData);

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("bizdata", bizdata);
        paramMap.put("service", service);
        paramMap.put("partnerId", partnerId);
        // RSA 加密

        //String params = RSAUtil.encrypt(urlQueryString, "UTF-8", publicKey);
        long start = System.currentTimeMillis();
        String params = HryGatewayUtils.encryptByPublicKey(paramMap, publicKey);
        System.out.println("rsa time" + (System.currentTimeMillis()-start));
        System.out.println("params = " + params);


        // 签名
        start = System.currentTimeMillis();
        String sign = HryGatewayUtils.genSign(paramMap, secret);
        System.out.println("sign time" + (System.currentTimeMillis()-start));
        System.out.println("sign = " + sign);

        Map<String, String> map = new HashMap<>();
        map.put("params", params);
        map.put("sign", sign);
        // Http Post请求
        start = System.currentTimeMillis();
        String response = HttpUtils.doPost(url, map, 2000, 10000);
        System.out.println("req time" + (System.currentTimeMillis()-start));
        // 输出响应结果
        //String response = HttpUtil.post(url, map, 2000);
        System.out.println("response = " + response);
    }
}
