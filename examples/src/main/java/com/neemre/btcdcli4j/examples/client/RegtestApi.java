package com.neemre.btcdcli4j.examples.client;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.domain.BlockChainInfo;
import com.neemre.btcdcli4j.examples.util.ResourceUtils;
import org.apache.http.impl.client.CloseableHttpClient;

import java.math.BigDecimal;
import java.util.Properties;

/**A list of examples demonstrating the use of <i>bitcoind</i>'s control RPCs (via the JSON-RPC 
 * API).*/
public class RegtestApi {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpProvider = ResourceUtils.getHttpProvider();
		Properties nodeConfig = ResourceUtils.getNodeConfig();
		BtcdClient client = new VerboseBtcdClientImpl(httpProvider, nodeConfig);
		BlockChainInfo blockchainInfo1 = client.getBlockChainInfo();
		String newAddress = client.getNewAddress("xyz");
		BigDecimal balanceBefore = client.getBalance("xyz");
		client.generateToAddress(200, newAddress);
		BigDecimal balanceAfter = client.getBalance("xyz");
		BlockChainInfo blockchainInfo2 = client.getBlockChainInfo();
		System.out.println("Balance before: " + balanceBefore.toString() + " balance after: " + balanceAfter.toString());
		System.out.println("Blocks before: " + blockchainInfo1.getBlocks() + " blocks after: " + blockchainInfo2.getBlocks());
	}
}