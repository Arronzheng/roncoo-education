package com.roncoo.education.util.tools;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

import com.xiaoleilu.hutool.exceptions.UtilException;
import sun.misc.BASE64Encoder;

public final class ImgUtil {

	private ImgUtil() {

	}

	/**
	 * 右下角打水印
	 *
	 * @throws IOException
	 *
	 * @see https://blog.csdn.net/u011627980/article/details/50318271
	 */
	public final static void pressImage(File pressImgFile, File srcImageFile, File destImageFile, Integer x, Integer y) throws IOException {
		Image src = ImageIO.read(srcImageFile);
		int wideth = src.getWidth(null);
		int height = src.getHeight(null);
		BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.drawImage(src, 0, 0, wideth, height, null);
		// 水印文件
		Image pressImg = ImageIO.read(pressImgFile);
		int pressImgWidth = pressImg.getWidth(null);
		int pressImgHeight = pressImg.getHeight(null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1));
		g.drawImage(pressImg, (wideth - pressImgWidth - x), (height - pressImgHeight - y), pressImgWidth, pressImgHeight, null);
		// 水印文件结束
		g.dispose();
		ImageIO.write(image, "JPEG", destImageFile);
	}

	/**
	 * 给图片添加文字水印
	 *
	 * @param pressText
	 *            水印文字
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @param fontName
	 *            水印的字体名称
	 * @param fontStyle
	 *            水印的字体样式，例如Font.BOLD
	 * @param color
	 *            水印的字体颜色
	 * @param fontSize
	 *            水印的字体大小
	 */
	public final static void pressText(String pressText, File srcImageFile, File destImageFile, String fontName, int fontStyle, int fontSize) {
		try {
			Image src = ImageIO.read(srcImageFile);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(Color.black);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1));
			// 在指定坐标绘制水印文字
			g.drawString(pressText, (width - (getLength(pressText) * fontSize)), (height - fontSize));
			g.dispose();
			ImageIO.write(image, "JPEG", destImageFile);// 输出到文件流
		} catch (Exception e) {
			throw new UtilException(e);
		}
	}

	/**
	 * 计算text的长度（一个中文算两个字符）
	 *
	 * @param text
	 *            文本
	 * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
	 */
	private final static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}

	/**
	 * 将图片转换成Base64编码
	 * @param imgFile 待处理图片
	 * @return
	 */
	public static String getImgBase(String imgFile) {
		ByteArrayOutputStream outPut = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		try {
			// 创建URL
			URL url = new URL(imgFile);
			// 创建链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(10 * 1000);

			if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return "获取图片失败";//连接失败/链接失效/图片不存在
			}
			InputStream inStream = conn.getInputStream();
			int len = -1;
			while ((len = inStream.read(data)) != -1) {
				outPut.write(data, 0, len);
			}
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return "data:image/jpeg;base64," + encoder.encode(outPut.toByteArray());
	}
}
