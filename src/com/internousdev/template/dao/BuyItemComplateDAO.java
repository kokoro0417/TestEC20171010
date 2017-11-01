package com.internousdev.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.template.util.DBConnector;
import com.internousdev.template.util.DateUtil;

public class BuyItemComplateDAO {

	private DBConnector dbConnector = new DBConnector();

	private Connection connection = dbConnector.getConnection();

	private DateUtil dateUtil = new DateUtil();

	private int sqlflag = 0;

	private String sql;

	/**
	 * 商品購入情報登録メソッド
	 *
	 * @param item_transaction_id
	 * @param user_master_id
	 * @param total_price
	 * @param total_count
	 * @param pay
	 * @throws SQLException
	 */
	public void buyItemeInfo(String item_transaction_id, String user_master_id, String total_price, String total_count, String pay) throws SQLException {

		try {
			sql = "select * from user_buy_item_transaction where item_transaction_id = ? ;";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, item_transaction_id);

			ResultSet rs =preparedStatement.executeQuery();

			if(rs.next()){
				sql = "update user_buy_item_transaction set item_transaction_id = ? , total_price = ? , total_count = ? , user_master_id = ? , pay = ? , insert_date = ? where item_transaction_id = ?;";
				sqlflag = 1;

			}else{
				sql = "INSERT INTO user_buy_item_transaction (item_transaction_id, total_price, total_count, user_master_id, pay, insert_date) VALUES(?, ?, ?, ?, ?, ?);";
				sqlflag = 0;

			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, total_price);
			preparedStatement.setString(3, total_count);
			preparedStatement.setString(4, user_master_id);
			preparedStatement.setString(5, pay);
			preparedStatement.setString(6, dateUtil.getDate());
			if(sqlflag == 1 ){
				preparedStatement.setString(7, item_transaction_id);
			}

			preparedStatement.execute();

		} catch(Exception e) {
			e.printStackTrace();

		} finally {
			connection.close();

		}
	}
}
