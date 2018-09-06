package UserInfo.portlet;

import UserInfo.constants.UserInfoPortletKeys;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author mariacaraballo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + UserInfoPortletKeys.UserInfo,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UserInfoPortlet extends MVCPortlet {
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException{
		
		ThemeDisplay td = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY); 
		User user = td.getUser(); 		
		
		//user information to display
		renderRequest.setAttribute("userName", user.getFullName());
		renderRequest.setAttribute("emailAddress", user.getEmailAddress());
		try {
			renderRequest.setAttribute("birthday", user.getBirthday());
		} catch (PortalException e) {
			e.printStackTrace();
		}
		renderRequest.setAttribute("job", user.getJobTitle());
			
		super.doView(renderRequest, renderResponse);
	}
}