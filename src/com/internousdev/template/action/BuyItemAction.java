package com.internousdev.template.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.template.dao.BuyItemDAO;
import com.opensymphony.xwork2.ActionSupport;

public class BuyItemAction extends ActionSupport implements SessionAware {

	/**
	 * アイテム情報を取得
	 */
	public BuyItemDAO buyItemDAO = new BuyItemDAO();

	/**
	 * アイテム購入個数
	 */
	public int stock;

	/**
	 * 支払い方法
	 */
	public String pay;

	/**
	 * アイテム情報を格納
	 */
	public Map<String, Object>  buyItemInfoMap = new HashMap<>();

	/**
	 * 処理結果
	 */
	public String result;

	/**
	 * 商品情報取得メソッド
	 *
	 * @author internous
	 */
	public String execute() {
		result = SUCCESS;

		buyItemInfoMap.put("stock", stock);
		int intStock = Integer.parseInt(buyItemInfoMap.get("stock").toString());
		int intPrice = Integer.parseInt(buyItemInfoMap.get("buyItem_price").toString());

		buyItemInfoMap.put("buyItem_price", intStock * intPrice);
		String payment;

		if(pay.equals("1")) {

			payment = "現金払い";
			buyItemInfoMap.put("pay", payment);
		} else {

			payment = "クレジットカード";
			buyItemInfoMap.put("pay", payment);
		}
		return result;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	@Override
	public void setSession(Map<String, Object> buyItemInfoMap) {
		this.buyItemInfoMap = buyItemInfoMap;
	}
}
