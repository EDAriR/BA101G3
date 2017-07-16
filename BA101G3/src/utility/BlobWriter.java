package utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BlobWriter {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 地圖機構
	private static final String SQL_SELECT = "SELECT * FROM map_mechanism ORDER BY map_no";
	private static final String SQL_PIC = "UPDATE map_mechanism SET map_photo=?, map_photo1=?, map_photo2=?, map_photo3=? ,map_photo4=? ,map_photo5=? WHERE map_no=?";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt_select = null;
		PreparedStatement pstmt_update = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt_select = con.prepareStatement(SQL_SELECT, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt_select.executeQuery();
			rs.last();
			Integer map_no_count = new Integer(rs.getString("map_no").substring(3));


			/* 拿到最後一筆編號 跑迴圈(停止條件)
			 * 迴圈數字轉字串 固定格式 ("00000001")
			 * int a = 11;
			   String with3digits = String.format("%03d", a); 011
			*/
			pstmt_update = con.prepareStatement(SQL_PIC);
			// setBytes
			for(int i = 1; i <= map_no_count; i++ ) {
				for(int j = 1; j <= 6; j++) {
					try {
						byte[] pic = PicArrayGenerate.getPictureByteArray("WebContent/pic/map/MAP" + String.format("%05d", i) + "/" + j + ".jpg");
						// 直接用setBytes把byte陣列資料丟進去
						pstmt_update.setBytes(j, pic);
					}catch(FileNotFoundException e) {
						pstmt_update.setBytes(j, null);
						continue;
					}
					
				}
				pstmt_update.setString(7, "MAP" + String.format("%05d", i));
				pstmt_update.executeUpdate();
				
				// 清空裡面參數，重覆使用已取得的PreparedStatement物件
				pstmt_update.clearParameters();
				
			}
		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (pstmt_update != null) {
				try {
					pstmt_update.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
			if (pstmt_select != null) {
				try {
					pstmt_select.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

}
