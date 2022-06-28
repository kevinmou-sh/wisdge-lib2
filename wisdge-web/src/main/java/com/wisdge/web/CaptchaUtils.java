package com.wisdge.web;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;

public class CaptchaUtils {
	private static final String CHOOSE_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String CHOOSE_CHAR_LOWCASE = "abcdefghijklmnopqrstuvwxyz";
	private static final String CHOOSE_CHAR_UPCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final SecureRandom random = new SecureRandom();

	public static BufferedImage generateCaptcha(String captcha, int fontSize) {
		Font font = new Font("STIX", Font.PLAIN, fontSize);
		BufferedImage tmpImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		Graphics tmpG = tmpImage.getGraphics();
		tmpG.setFont(font);
		tmpG.drawString(captcha, 0, 100);
		FontMetrics metrics = tmpG.getFontMetrics(font);
		int width = metrics.stringWidth(captcha) + 12;
		int height = metrics.getMaxDescent() + metrics.getMaxAscent();
		int baseline = metrics.getMaxAscent();

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.fillRect(0, 0, width, height);
		g.setFont(font);
		g.setColor(generateRandColor(160, 200));

		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		String pad = "";
		for (int i = 0; i < captcha.length(); i++) {
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			String s = captcha.substring(i, i + 1);
			double angle = generateRandRotate();
			int x = metrics.stringWidth(pad) + 6;

			Graphics2D g2d = (Graphics2D) g;
			// Move the origin to the (x,y)
			g2d.translate(x, baseline);
			// Rotate the angle
			g2d.rotate(Math.PI * (angle / -180));
			// Draw text
			g2d.drawString(s, 0, 0);
			// Restore moving and rotating
			g2d.rotate(-Math.PI * (angle / -180));
			g2d.translate(-x, -baseline);

			pad += s;
		}
		g.dispose();
		tmpG.dispose();
		return image;
	}

	public static String generateRandomAlphabet(int length) {
		return generateRandomAlphabet(length, "mix");
	}

	public static String generateRandomAlphabet(int length, String style) {
		StringBuilder randBuf = new StringBuilder();
		for (int i = 0; i < length; i++) {
			String rand;
			if (style.equals("digit")) {
				rand = String.valueOf(random.nextInt(10));
			} else if (style.equals("char")) {
				rand = String.valueOf(CHOOSE_CHAR.charAt(random.nextInt(CHOOSE_CHAR.length())));
			} else if (style.equals("lowcase")) {
				rand = String.valueOf(CHOOSE_CHAR_LOWCASE.charAt(random.nextInt(CHOOSE_CHAR_LOWCASE.length())));
			} else if (style.equals("upcase")) {
				rand = String.valueOf(CHOOSE_CHAR_UPCASE.charAt(random.nextInt(CHOOSE_CHAR_UPCASE.length())));
			} else {
				if (i % 2 == 0) {
					rand = String.valueOf(random.nextInt(10));
				} else {
					rand = String.valueOf(CHOOSE_CHAR.charAt(random.nextInt(CHOOSE_CHAR.length())));
				}
			}
			randBuf.append(rand);
		}
		return randBuf.toString();
	}

	public static Color generateRandColor(int fc, int bc) {
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);

		return new Color(r, g, b);
	}

	private static double generateRandRotate() {
		return random.nextInt(30) * (random.nextBoolean() ? 1: -1);
	}

}
