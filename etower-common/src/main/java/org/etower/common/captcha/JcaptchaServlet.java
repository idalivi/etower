package org.etower.common.captcha;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.etower.common.web.session.SessionProvider;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * 提供验证码图片的Servlet
 * 
 * @author dawei.li
 * 
 */
@SuppressWarnings("serial")
public class JcaptchaServlet extends HttpServlet {

	public static final String CAPTCHA_IMAGE_FORMAT = "jpeg";

	private ImageCaptchaService captchaService;
	private SessionProvider session;

	@Override
	public void init() throws ServletException {
		WebApplicationContext appCtx = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		captchaService = (ImageCaptchaService) BeanFactoryUtils
				.beanOfTypeIncludingAncestors(appCtx, ImageCaptchaService.class);
		session = (SessionProvider) BeanFactoryUtils
				.beanOfTypeIncludingAncestors(appCtx, SessionProvider.class);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		byte[] captchaChallengeAsJpeg = null;
		// the output stream to render the captcha image as jpeg into
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			// get the session id that will identify the generated captcha.
			// the same id must be used to validate the response, the session id
			// is a good candidate!
			String captchaId = session.getSessionId(request, response);
			BufferedImage challenge = captchaService.getImageChallengeForID(
					captchaId, request.getLocale());
			// 随机产生20条干扰线
			Graphics2D g2d = challenge.createGraphics();
			Stroke bs = new BasicStroke(3f);
			Random random = new Random(); 
			g2d.setColor(Color.BLACK);
			for (int i = 0; i < 2; i++) {
				int x = random.nextInt(110);
				int y = random.nextInt(50);
				int xl = random.nextInt(25);
				int yl = random.nextInt(25);
				g2d.setStroke(bs);
				g2d.drawLine(x, y, x + xl, y + yl);
				g2d.drawArc(xl, yl, x, y, 20, 50);
			}
			
			// Jimi.putImage("image/jpeg", challenge, jpegOutputStream);
			ImageIO.write(challenge, CAPTCHA_IMAGE_FORMAT, jpegOutputStream);
		} catch (IllegalArgumentException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		} catch (CaptchaServiceException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

		// flush it in the response
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/" + CAPTCHA_IMAGE_FORMAT);

		ServletOutputStream responseOutputStream = response.getOutputStream();
		responseOutputStream.write(captchaChallengeAsJpeg);
		responseOutputStream.flush();
		responseOutputStream.close();
	}

}
