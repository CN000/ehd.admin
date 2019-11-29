package com.ehd.admin.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ehd.admin.address.domain.Address;
import com.ehd.admin.address.mapper.AddressMapper;
import com.ehd.admin.contant.Contants;
import com.ehd.admin.contant.CryptoapisContant;
import com.ehd.admin.recharge.domain.Recharge;
import com.ehd.admin.recharge.mapper.RechargeMapper;
import com.ehd.admin.user.mapper.FrontUserMapper;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import io.cryptoapis.blockchains.ethereum_based.services.AddressService;
import io.cryptoapis.blockchains.ethereum_based.services.TokenService;
import io.cryptoapis.blockchains.ethereum_based.services.TransactionService;
import io.cryptoapis.common_models.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JobHandler(value = "RechargeHandler")
@Component
public class RechargeHandler extends IJobHandler {
    @Autowired
    private RechargeMapper rechargeMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, recharge handler  beigin.");
        List<Recharge> rechargeList = rechargeMapper.getRechargeByStatus(1);
        System.out.println("执行开始");
        Map<String,String> addrMap = new HashMap<String, String>();
        if(!rechargeList.isEmpty()){
            Map<String,String> map = new HashMap<String,String>();
            for (Recharge recharge:rechargeList){
                //根据hash查询交易明细
                TransactionService transactionService =CryptoapisContant.eth.getTransactionService();
                JSONObject payload = JSONObject.parseObject(transactionService.getTx(recharge.getHash()).getResponse()).getJSONObject("payload");
                JSONArray token_transfers = payload.getJSONArray("token_transfers");
                //获取to地址
                JSONObject transfer = (JSONObject) token_transfers.get(0);
                String to = transfer.getString("to");
                System.out.println("to is "+to);
                if(map.get(to)==null){
                    System.out.println("进来了");
                    AddressService addressService = CryptoapisContant.eth.getAddressService();
                    TokenService tokenService = CryptoapisContant.eth.getTokenService();
                    //获取钱ehd余额和eth余额
                    JSONObject tokeInfo = JSONObject.parseObject(tokenService.getTokenBalance(to,Contants.EHDTOKEN).getResponse()).getJSONObject("payload");
                    JSONObject ethInfo = JSONObject.parseObject(addressService.getAddressInfo(to).getResponse()).getJSONObject("payload");
                    BigDecimal ethBalance = ethInfo.getBigDecimal("balance");
                    System.out.println("ethBalance is"+ethBalance);
                    BigDecimal tokenBalance = tokeInfo.getBigDecimal("token");
                    System.out.println("tokenBalance is"+tokenBalance);

                    Address address = addressMapper.getPrivateKeyByAddress(to);
                    //如果账户eth少于0.0003，向账户打币
                    if(tokenBalance.compareTo(new BigDecimal(1))>0){
                        if(ethBalance.compareTo(new BigDecimal(0.0003))<0){
                            JSONObject nonJson = JSONObject.parseObject(addressService.getNonce(to).getResponse()).getJSONObject("payload");
                            System.out.println("nonJson:"+nonJson);
                            BigInteger nonce = nonJson.getBigInteger("nonce");
                            System.out.println("nonce:"+nonce);
                            nonce=nonce.add(BigInteger.valueOf(1));
                            ApiResponse transRes = transactionService.createTxPvt("0x3792FA22A567e9161fc855A396581E5e9d04da7E",to,new BigInteger("100000000"),new BigInteger("30000"),new BigDecimal(0.0003),null,"0c566b5024f92cf4d0c73019752a2b723a7fe2d1d226e4968a5ab85349727d1b",nonce);
                            System.out.println("eth 打币结果"+transRes.getResponse());
                            map.put(to,"2");
                        }else{
                            //如果账户大于等于0.003。转账
                            JSONObject nonJson = JSONObject.parseObject(addressService.getNonce("0x40F8d92986e82E5E733A1A4914f950c8e1C93874").getResponse()).getJSONObject("payload");
                            int nonce = nonJson.getInteger("nonce");
                            nonce=nonce+10;
                            ApiResponse transRes = tokenService.transferPvt(to,"0x40F8d92986e82E5E733A1A4914f950c8e1C93874",Contants.EHDTOKEN,new BigInteger("100000000"),new BigInteger("30000"),tokenBalance,address.getPrivateKey(),nonce);
                            //rechargeMapper.updateStatus(recharge.getId());
                            System.out.println("ehd 转账结果"+transRes.getResponse());
                        }
                    }

                }
            }
        }
        return SUCCESS;
    }
}
