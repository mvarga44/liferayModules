package AddUser.portlet;

import AddUser.constants.AddUserPortletKeys;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

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
		"javax.portlet.name=" + AddUserPortletKeys.AddUser,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AddUserPortlet extends MVCPortlet {
	public void addUser(ActionRequest request, ActionResponse response) throws PortalException, SystemException{
		
		String screenName = ParamUtil.getString(request, "screenName"); 
		String firstName = ParamUtil.getString(request, "firstName");
		String lastName = ParamUtil.getString(request, "lastName"); 
		String email = ParamUtil.getString(request, "email"); 
		String tempPassword = ParamUtil.getString(request, "tempPassword");
	
		long userId = CounterLocalServiceUtil.increment(User.class.getName()); 
		User createdUser = UserLocalServiceUtil.createUser(userId); 
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		createdUser.setCompanyId(themeDisplay.getCompanyId());
		createdUser.setPassword(tempPassword);
		createdUser.setScreenName(screenName);
		createdUser.setEmailAddress(email);
		createdUser.setFacebookId(0);
		createdUser.setOpenId(null);
		createdUser.setGreeting("New User");
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		createdUser.setJobTitle("");
		createdUser.setCreateDate(new Date());
		createdUser.setContactId(0);
		createdUser.setPasswordReset(true);
		createdUser.setPasswordEncrypted(true);
		createdUser.setPasswordModifiedDate(new Date());
		createdUser.setModifiedDate(new Date());
		createdUser.setLanguageId(themeDisplay.getLanguageId());
		createdUser.setTimeZoneId(themeDisplay.getTimeZone().getDisplayName());
		UserLocalServiceUtil.addUser(createdUser); 	
		
	}
}