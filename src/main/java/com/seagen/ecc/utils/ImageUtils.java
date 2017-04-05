package com.seagen.ecc.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 图片处理工具
 * 
 * @author kuangjianbo
 * 
 */
public class ImageUtils {
	public static void main(String[] args) throws IOException {
		ImageUtils.scale("C:\\Users\\th\\Desktop\\20150730143930_692.png",
				"C:\\Users\\th\\Desktop\\20150730143904_499_1.jpg", 600, 600,
				true);
		// ImageUtils.convert("C:\\Users\\th\\Desktop\\20150730143904_499.png",
		// "C:\\Users\\th\\Desktop\\20150730143904_499_1.jpg");
	}

	/**
	 * 图像缩放.<br>
	 * 例如:scale("a.jpg","b.jpg",150),将图片的宽和高缩放的原来的150%
	 * 
	 * @param source
	 *            图像源地址
	 * @param dest
	 *            图像目的地址
	 * @param scale
	 *            缩放比例 scale%
	 * @throws IOException
	 * 
	 */
	public static void scale(String source, String dest, int scale)
			throws IOException {
		BufferedImage bufimg = ImageIO.read(new File(source));
		int width = bufimg.getWidth();
		int height = bufimg.getHeight();
		width *= (scale / 100.0);
		height *= (scale / 100.0);
		Image img = bufimg
				.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		BufferedImage bufimg1 = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = bufimg1.getGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		String fileType = dest.substring(dest.lastIndexOf('.') + 1);
		ImageIO.write(bufimg1, fileType, new File(dest));
	}

	/**
	 * 图像缩放.<br>
	 * 
	 * @param source
	 *            图像源地址
	 * @param dest
	 *            图像目的地址
	 * @param width
	 *            目的宽度
	 * @param height
	 *            目的高度
	 * @param filler
	 *            是否补白
	 * @throws IOException
	 * 
	 */

	public static void scale(String source, String dest, int width, int height,
			boolean filler) throws IOException {
		BufferedImage bufimg = ImageIO.read(new File(source));
		int width1 = bufimg.getWidth();
		int height1 = bufimg.getHeight();
		if (filler) {// 如果补白
			float w = (float) width / width1;
			float h = (float) height / height1;
			if (w > h) {
				height1 *= h;
				width1 *= h;
			} else {
				height1 *= w;
				width1 *= w;
			}
		} else {
			width1 = width;
			height1 = height;
		}
		Image img = bufimg.getScaledInstance(width1, height1,
				Image.SCALE_DEFAULT);
		BufferedImage bufimg1 = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = bufimg1.getGraphics();
		g.drawImage(img, 0, 0, null);
		g.setColor(new Color(255, 255, 255));
		if (width - width1 > 1) {
			g.fillRect(width1, 0, width - width1, height);
		} else if (height - height1 > 1) {
			g.fillRect(0, height1, width, height - height1);
		}
		g.dispose();
		String fileType = dest.substring(dest.lastIndexOf('.') + 1);
		ImageIO.write(bufimg1, fileType, new File(dest));
	}

	/**
	 * 图像类型转换 gif<->jpg<->png
	 * 
	 * @param source
	 *            源文件地址
	 * @param dest
	 *            目标文件地址
	 * @throws IOException
	 */
	public static void convert(String source, String dest) throws IOException {
		String fileType = dest.substring(dest.lastIndexOf('.') + 1);
		File f = new File(source);
		f.canRead();
		BufferedImage bufimg = ImageIO.read(f);
		ImageIO.write(bufimg, fileType, new File(dest));
	}

	/**
	 * 彩色转为黑白
	 * 
	 * @param source
	 *            源文件地址
	 * @param dest
	 *            目标文件地址
	 * @throws IOException
	 */
	public static void gray(String source, String dest) throws IOException {
		String fileType = dest.substring(dest.lastIndexOf('.') + 1);
		BufferedImage bufimg = ImageIO.read(new File(source));
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		ColorConvertOp op = new ColorConvertOp(cs, null);
		bufimg = op.filter(bufimg, null);
		ImageIO.write(bufimg, fileType, new File(dest));
	}

	public static void img2File(Image img, File file) throws IOException {
		BufferedImage bufimg1 = new BufferedImage(300, 400,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = bufimg1.getGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		String name = file.getName();
		String fileType = name.substring(name.lastIndexOf('.') + 1);
		ImageIO.write(bufimg1, fileType, file);
	}
}
