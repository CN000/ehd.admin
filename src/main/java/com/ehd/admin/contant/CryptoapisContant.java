package com.ehd.admin.contant;

import io.cryptoapis.client.CryptoApis;
import io.cryptoapis.connections.*;
import io.cryptoapis.utils.constants.CryptoApisConstants;

public class CryptoapisContant {

    public static  final String apiKey = "7206a88e1ea14cce0f4210e834fc1064734ccf91";
    public static  CryptoApis caClient = new CryptoApis(apiKey);

    // Choose API connection and network
    public  final Exchanges exchanges = caClient.connectToExchanges();

    public static final Ethereum eth = caClient.connectToEth(CryptoApisConstants.ETHEREUM_MAINNET);

    public final Bitcoin btc = caClient.connectToBtc(CryptoApisConstants.MAINNET);

    public final Bitcoin_Cash bch = caClient.connectToBch(CryptoApisConstants.MAINNET);

    public final Litecoin ltc = caClient.connectToLtc(CryptoApisConstants.MAINNET);

    public final Dogecoin doge = caClient.connectToDoge(CryptoApisConstants.MAINNET);
}
