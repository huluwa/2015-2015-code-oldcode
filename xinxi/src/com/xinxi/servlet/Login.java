package com.xinxi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioFormat.Encoding;

import com.xinxi.db.SqlUtil;
import com.xinxi.entity.Customer;
import com.xinxi.util.Coder;

public class Login extends HttpServlet {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * Constructor of the object.
	 */
	public Login() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://10.120.148.55:8080/xinxi/servlet/Login?username=234&pwd=234
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");

		if (username == null || username.equals("") || pwd == null
				|| pwd.equals("")) {
			out.println("-1");
			return;
		}
		// ��ѯ���ݿ�
		SqlUtil util = new SqlUtil();
		Customer customer = util.queryYH(username);
		if (customer == null) {
			out.println("3");///û���˺�
			return;
		}
		// ƥ������
		boolean flag = ComparePasswords(customer.getPwdbyte(), pwd);
		System.out.println("����ƥ��:" + flag);
		if (!flag) {
			out.println("2");// ������û�����ƥ��
			return;
		}
		String now = sdf.format(new Date());
		if (now.compareTo(customer.getEndtime()) > 0) {
			out.println("1");//�˻�����
			return;
		} else {
			out.println("0");//�ɹ����
			return;
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	int saltLength = 4;

	public boolean ComparePasswords(byte[] storedPassword, String password) {

		// byte[] hashedPassword = Coder.SHA1BYTE(password);

		byte[] plainText;
		try {
			/**
			 * ����
			 */
			plainText = password.getBytes("UTF-16LE");
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.update(plainText);
			byte[] hashedPassword = messageDigest.digest();
//			System.out.println("==========");
//			for (int i = 0; i < hashedPassword.length; i++) {
//				System.out.printf("%x", hashedPassword[i]);
//			}
//			System.out.println("==========");
			if (storedPassword == null
					|| hashedPassword == null
					|| hashedPassword.length != storedPassword.length
							- saltLength)
				return false;

			byte[] saltValue = new byte[saltLength];
			int saltOffset = storedPassword.length - saltLength;
			for (int i = 0; i < saltLength; i++)
				saltValue[i] = storedPassword[saltOffset + i];

			byte[] saltedPassword = CreateSaltedPassword(saltValue,
					hashedPassword);

			return CompareByteArray(storedPassword, saltedPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// �Ѵ�����0��ʼ��2�����ָ��Ƶ�����Ϊ3��λ����
	// Դ�ļ���Դ�ļ�����������Դ�ļ���������ʼcopy����Ŀ���ļ���Ŀ���ļ�������(����Ŀ���ļ����Ǹ�����)��copy����
	// System.arraycopy(ids, 0, ids, 3, 2);
	// System.out.println(Arrays.toString(ids)); // [1, 2, 3, 1, 2]
	// �����ݵ�����1��ʼ��3�����ݸ��Ƶ�Ŀ�������Ϊ0��λ����

	// int[] ids2 = new int[6];

	// System.arraycopy(ids, 1, ids2, 0, 3);
	// System.out.println(Arrays.toString(ids2)); // [2, 3, 1, 0, 0, 0]

	byte[] CreateSaltedPassword(byte[] saltValue, byte[] unsaltedPassword)
			throws NoSuchAlgorithmException {
		byte[] rawSalted = new byte[unsaltedPassword.length + saltValue.length];
		System.arraycopy(unsaltedPassword, 0, rawSalted, 0,
				unsaltedPassword.length);
		System.arraycopy(saltValue, 0, rawSalted, unsaltedPassword.length,
				saltValue.length);

		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		messageDigest.update(rawSalted);
		byte[] saltedPassword = messageDigest.digest();
		byte[] dbPassword = new byte[saltedPassword.length + saltValue.length];

		System.arraycopy(saltedPassword, 0, dbPassword, 0,
				saltedPassword.length);
		System.arraycopy(saltValue, 0, dbPassword, saltedPassword.length,
				saltValue.length);

		return dbPassword;
	}

	// compare the contents of two byte arrays
	private boolean CompareByteArray(byte[] array1, byte[] array2) {
		if (array1.length != array2.length)
			return false;
		for (int i = 0; i < array1.length; i++) {
			//System.out.println(i + "==" + array1[i] + ":" + array2[i]);
			if (array1[i] != array2[i])
				return false;
		}
		return true;
	}
}
