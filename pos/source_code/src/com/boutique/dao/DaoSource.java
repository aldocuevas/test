package com.boutique.dao;

import java.sql.*;
 
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.boutique.util.EncryptedProperties;

public class DaoSource {
	private static final String _IDBOUTIQUE = "1";
	private static final String _DATABASE = "4";
	private static final String _PASSWORD = "3";
	private static final String _USERNAME = "6";
	private static final String _PORT = "2";
	private static final String _IP = "5";
	private static final String _IDTERMINAL = "7";
	public static String ip;
	public static String port;
	public static String username;
	public static String password;
	public static String database;
	public static int idBoutique;
	public static int idTerminal;

	public DaoSource() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private static Connection con = null;

	public static boolean guardarDatosBoutique(int idBoutique, int idTerminal) {
		EncryptedProperties p = new EncryptedProperties();
		p.setProperty(_IP, ip);
		p.setProperty(_PORT, port);
		p.setProperty(_USERNAME, username);
		p.setProperty(_PASSWORD, password);
		p.setProperty(_DATABASE, database);
	
		p.setProperty(_IDBOUTIQUE, String.valueOf(idBoutique));
		p.setProperty(_IDTERMINAL, String.valueOf(idTerminal));

		try {

			p.store(new FileOutputStream("ApplicationResources.properties"),
					null);
			DaoSource.idBoutique = idBoutique;
			DaoSource.idTerminal = idTerminal;
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (IOException e) {
			return false;
		}
		return true;

	}

	public static boolean guardarDatosConexion() {
		// Write properties file.
		EncryptedProperties properties = new EncryptedProperties();
		properties.setProperty(_IP, ip);
		properties.setProperty(_PORT, port);
		properties.setProperty(_USERNAME, username);
		properties.setProperty(_PASSWORD, password);
		properties.setProperty(_DATABASE, database);
		try {
			properties.store(new FileOutputStream(
					"ApplicationResources.properties"), null);
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public static java.util.Date getFechaActual() {
		Connection con = getConnection();
		try {
			Statement st = con.createStatement();
			java.util.Date fecha = new java.util.Date();
			ResultSet rs = st.executeQuery("SELECT NOW() as fecha;");
			if (rs.next()) {
				fecha = rs.getTimestamp("fecha");
			}
			rs.close();
			return fecha;
		} catch (SQLException ex) {
			return null;
		}
	}

	public static Connection getConnection() {
		try {
			if (con == null || con.isClosed()) {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection(
				// "jdbc:mysql://p1.getmyip.com/boutiqueinventarios?user=aldo&password=nomelose");
				// "jdbc:mysql://192.168.1.1/boutiqueinventarios?user=aldo&password=nomelose");

						"jdbc:mysql://" + ip + ":" + port + "/" + database
								+ "?user=" + username + "&password=" + password);
				// "jdbc:mysql://localhost:6666/boutiqueinventarios2?user=aldo&password=nomelose");
				// JOptionPane.showMessageDialog(null,"La conexion ha sido exitosa","Conexion",JOptionPane.INFORMATION_MESSAGE);
			}
			return con;
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(
					null,
					"ERROR EN LA CONEXION AL SERVIDOR: "
							+ ((e.getMessage().toString().length() > 200) ? e
									.getMessage().substring(0, 200) : e
									.getMessage()), "ERROR DE CONEXION",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	}

	public static void transfer(InputStream input, OutputStream output)
			throws Exception {
		byte[] buf = new byte[1024];
		int len;
		while ((len = input.read(buf)) >= 0) {
			output.write(buf, 0, len);
		}
		input.close();
		output.close();
	}

	public static boolean respaldarBD(String path) throws Exception {
		// ip="localhost";
		// username="root";
		// password="relajate";
		// database="boutiqueinventarios";
		String command = "mysqldump -h" + ip + " --result-file=" + path
				+ " -R -P" + port + " -u" + username + " -p" + password + " "
				+ database;

		// "mysqldump -h" + ip + "  -u" + username + " -p" + password + "  -r" +
		// path + " " + database;

		java.lang.Process p = Runtime.getRuntime().exec(command);
		p.exitValue();
		// InputStream input = p.getInputStream();
		// FileOutputStream output = new FileOutputStream(path);
		// transfer(input, output);
		return true;

	}

	public static boolean testConnection(String ip, String port,
			String database, String username, String password)
			throws SQLException {
		try {

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (ClassNotFoundException ex) {
			} catch (IllegalAccessException ex) {
			} catch (InstantiationException ex) {
			}
			Connection con2 = DriverManager.getConnection(
			// "jdbc:mysql://p1.getmyip.com/boutiqueinventarios?user=aldo&password=nomelose");
			// "jdbc:mysql://192.168.1.1/boutiqueinventarios?user=aldo&password=nomelose");

					"jdbc:mysql://" + ip + ":" + port + "/" + database
							+ "?user=" + username + "&password=" + password);
			// "jdbc:mysql://localhost:6666/boutiqueinventarios?user=nixy&password=nixybt");
			// JOptionPane.showMessageDialog(null,"La conexion ha sido exitosa","Conexion",JOptionPane.INFORMATION_MESSAGE);

			if (con2 != null) {
				con2.close();
				con2 = null;
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	public static Connection getConnectionLocal() {
		return getConnection();
	}

	/**
	 * getConnectionExcel
	 * 
	 * @return Connection
	 */
	public static Connection getConnectionExcel() {
		return null;
	}

	public static void iniciar() throws Exception {
		EncryptedProperties properties = new EncryptedProperties();
		try {

			properties.load(new FileInputStream(
					"ApplicationResources.properties"));
			DaoSource.ip = properties.getProperty(_IP);
			DaoSource.port = properties.getProperty(_PORT);
			DaoSource.username = properties.getProperty(_USERNAME);
			DaoSource.password = properties.getProperty(_PASSWORD);
			DaoSource.database = properties.getProperty(_DATABASE);
			try {
				idBoutique = Integer.parseInt(properties
						.getProperty(_IDBOUTIQUE));
				idTerminal = Integer.parseInt(properties.getProperty(_IDTERMINAL));
			} catch (Exception ex) {
				idBoutique = 0;
				idTerminal = 0;
			}
		}

		catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static boolean registrarRespaldo() {
		boolean value = false;
		String sql = "INSERT INTO registro_respaldos (fechaRespaldo) VALUES (CURDATE());";
		Connection con = DaoSource.getConnectionLocal();

		try {
			con.createStatement().executeUpdate(sql);
			value = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return value;

	}

	public static boolean respaldoRealizado() {
		boolean value = false;
		String sql = "SELECT * FROM registro_respaldos WHERE fechaRespaldo=CURDATE()";
		Connection con = DaoSource.getConnectionLocal();
		ResultSet rs = null;
		try {
			rs = con.createStatement().executeQuery(sql);
			if (rs.next()) {
				value = true;
			}
			rs.close();
		} catch (SQLException ex) {
		}
		return value;
	}

	private void jbInit() throws Exception {
	}
}
