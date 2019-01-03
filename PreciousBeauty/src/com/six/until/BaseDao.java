package com.six.until;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


public class BaseDao<T> {

	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://127.0.0.1:3306/shoppingmall?useUnicode=true&characterEncoding=utf-8";
	private static String USER = "root";
	private static String PASSWORD = "root";

	// 鑾峰彇杩炴帴
	protected Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/*
	 * slelct count(id) from xxx
	 * @param sql  sql璇彞
	 * @param args  鍙傛暟
	 * @param clazz  瀵硅薄.class
	 * @return
	 */
	public int queryCount(String sql, List args, Class clazz) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			// 濡傛灉sql璇彞鏈夊弬鏁扮敤args闆嗗悎寰幆缁欏叾璧嬪��
			if (args != null && args.size() > 0) {
				for (int i = 0; i < args.size(); i++) {
					pstmt.setObject(i + 1, args.get(i));// 鍙傛暟浣嶇疆鍙蜂粠1寮�濮�
				}
			}
			rs = pstmt.executeQuery();
			if (rs.next())
				return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return 0;

	}

	// 鏌ヨ鏂规硶
	/**
	 * 
	 * @param sql
	 * @param args
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> query(String sql, List args, Class clazz) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 瀛樺偍杩斿洖鐨勯泦鍚堝璞�
		List<T> list = new ArrayList<T>();
		try {
			// 浣跨敤鍙嶅皠鑾峰彇绫荤殑鐩稿叧淇℃伅(瀹炰綋绫荤殑灞炴��--鏁版嵁搴撻噷鐩稿簲琛ㄧ殑瀛楁)
			Field[] fields = clazz.getDeclaredFields();
			Method method = null;

			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			// 濡傛灉sql璇彞鏈夊弬鏁扮敤args闆嗗悎寰幆缁欏叾璧嬪��
			if (args != null && args.size() > 0) {
				for (int i = 0; i < args.size(); i++) {
					pstmt.setObject(i + 1, args.get(i));// 鍙傛暟浣嶇疆鍙蜂粠1寮�濮�
				}
			}
			rs = pstmt.executeQuery();
			// 鑾峰彇缁撴灉闆嗙殑鍏冩暟鎹�
			ResultSetMetaData rsmd = rs.getMetaData();
			// 鑾峰彇涓�鍏辨湁澶氬皯鍒�
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				// 鍒涘缓涓�涓柊瀵硅薄
				T obj = (T) clazz.newInstance();
				// 鍙栧嚭鐨勭粨鏋滈泦涓垪鍙蜂粠1寮�濮�
				for (int i = 1; i <= columnCount; i++) {
					String cname = rsmd.getColumnName(i);// 鑾峰彇姣忎竴鍒楃殑鍚嶇О
					int ctype = rsmd.getColumnType(i);// 鑾峰彇姣忎竴鍒楃殑鏁版嵁绫诲瀷
					for (Field f : fields) {
						if (cname.equalsIgnoreCase(f.getName()))// 濡傛灉鍒楀悕鍜屽瓧娈靛悕鐩稿悓
						{
							// 寮�濮嬪皝瑁呮暟鎹�
							String methodName = "set"
									+ f.getName().substring(0, 1).toUpperCase()
									+ f.getName().substring(1);

							// 鏍规嵁鍒楃殑type鍊肩殑绫诲瀷杩涜澶勭悊
							switch (ctype) {
							case Types.INTEGER:
								method = clazz.getMethod(methodName,
										Integer.class);
								method.invoke(obj, rs.getInt(i));
								break;
							case Types.VARCHAR:
								method = clazz.getMethod(methodName,
										String.class);
								method.invoke(obj, rs.getString(i));
								break;
							case Types.CHAR:
								method = clazz.getMethod(methodName,
										String.class);
								method.invoke(obj, rs.getString(i));
								break;

							// 鍓嶅彴鐨刯ava鏁版嵁绫诲瀷涓簀ava.util.Date绫诲瀷
							case Types.TIMESTAMP:
								method = clazz.getMethod(methodName,
										Timestamp.class);
								method.invoke(obj, rs.getString(i));
								break;
							case Types.DECIMAL:// 鎴栬�呮敼涓篶ase
								// 3,鍥犱负鏁版嵁搴撲腑鐨刴oney绫诲瀷鐨刢type鍊间负3锛屾敞锛歫ava.math.bigdecimal瀵瑰簲鏁版嵁搴撻噷鐨刴oney绫诲瀷
								method = clazz.getMethod(methodName,
										BigDecimal.class);
								method.invoke(obj, rs.getBigDecimal(i));
								break;
							}
						}
					}
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return list;
	}

	/**
	 * 鎵ц澧炲垹鏀规柟娉�
	 * @param sql
	 * @param list
	 * @return
	 */
	public int saveOrDeleteOrUpdate(String sql, List list) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			conn = getConn();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			// 寰幆缁檚ql鍙傛暟璧嬪��
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					pstmt.setObject(i + 1, list.get(i));
				}
			}
			count = pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();// 鎹曡幏浜嬪姟寮傚父锛屼簨鍔″洖婊�
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("鏁版嵁搴撴搷浣滃紓甯革紒");
		} finally {
			closeAll(null, pstmt, conn);
		}
		return count;
	}
	 protected ResultSet executeQuery(String sql, Object... params) throws SQLException {
	        PreparedStatement ps = null;
	        Connection conn = null;
	        ResultSet rs = null;
	        try {
	            //pstmt = conn.prepareStatement(sql);
	            ps = conn.prepareStatement(sql);
	            //conn.commit();
	            for (int i = 0; i < params.length; i++) {
	                ps.setObject(i + 1, params[i]);
	            }
	            rs = ps.executeQuery();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw e;
	        }
	        return rs;
	    }
	// 鍏抽棴杩炴帴
	protected void closeAll(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
